package servlet;

import model.AccesoBD;
import model.PrestamoDetalle;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/misprestamos")
public class MisPrestamosController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        List<PrestamoDetalle> prestamos = new ArrayList<>();

        try {
            AccesoBD acceso = new AccesoBD();
            Connection con = acceso.getConexion();

            PreparedStatement psVencidos = con.prepareStatement(
                "UPDATE prestamo SET estado = 'vencido' WHERE estado = 'activo' AND fecha_devolucion < CURDATE()"
            );
            psVencidos.executeUpdate();

            PreparedStatement ps = con.prepareStatement(
                "SELECT p.codigo, p.estado, p.fecha, p.fecha_devolucion, l.titulo, l.autor, l.materia " +
                "FROM prestamo p " +
                "JOIN historico h ON p.id_historico = h.id_historico " +
                "JOIN ejemplar e ON p.id_ejemplar = e.id_ejemplar " +
                "JOIN libro l ON e.id_libro = l.id_libro " +
                "WHERE h.id_usuario = ? AND p.estado IN ('activo', 'vencido') " +
                "ORDER BY p.fecha DESC"
            );
            ps.setInt(1, user.getIdUsuario());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prestamos.add(new PrestamoDetalle(
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("materia"),
                    rs.getString("fecha"),
                    rs.getString("fecha_devolucion"),
                    rs.getString("estado"),
                    rs.getString("codigo")
                ));
            }

            rs.close();
            ps.close();
            acceso.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("prestamos", prestamos);
        request.setAttribute("view", "misprestamos.jsp");
        request.setAttribute("estilo", "estilos/misprestamos.css");
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
