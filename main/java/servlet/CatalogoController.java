package servlet;

import model.AccesoBD;
import model.Libro;
import model.Ejemplar;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/catalogo")
public class CatalogoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String materia = request.getParameter("materia");
        if (materia == null) materia = "";

        List<Libro> libros = new ArrayList<>();
        List<String> materias = new ArrayList<>();
        int numPrestamos = 0;

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");

        try {
            AccesoBD acceso = new AccesoBD();
            Connection con = acceso.getConexion();

            PreparedStatement psVencidos = con.prepareStatement(
                "UPDATE prestamo SET estado = 'vencido' WHERE estado = 'activo' AND fecha_devolucion < CURDATE()"
            );
            psVencidos.executeUpdate();

            if (user != null) {
                PreparedStatement psCount = con.prepareStatement(
                    "SELECT COUNT(*) FROM prestamo p JOIN historico h ON p.id_historico = h.id_historico WHERE h.id_usuario = ? AND p.estado = 'activo'"
                );
                psCount.setInt(1, user.getIdUsuario());
                ResultSet rsCount = psCount.executeQuery();
                if (rsCount.next()) numPrestamos = rsCount.getInt(1);
                rsCount.close();
                psCount.close();
            }

            PreparedStatement psMat = con.prepareStatement("SELECT DISTINCT materia FROM libro ORDER BY materia");
            ResultSet rsMat = psMat.executeQuery();
            while (rsMat.next()) {
                materias.add(rsMat.getString("materia"));
            }
            rsMat.close();
            psMat.close();

            String sql;
            PreparedStatement ps;

            if (!materia.isEmpty()) {
                sql = "SELECT * FROM libro WHERE materia = ? ORDER BY (SELECT COUNT(*) FROM ejemplar WHERE id_libro = libro.id_libro AND estado = 'disp') DESC, titulo";
                ps = con.prepareStatement(sql);
                ps.setString(1, materia);
            } else {
                sql = "SELECT * FROM libro ORDER BY (SELECT COUNT(*) FROM ejemplar WHERE id_libro = libro.id_libro AND estado = 'disp') DESC, titulo";
                ps = con.prepareStatement(sql);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro(
                    rs.getInt("id_libro"),
                    rs.getString("ISBN"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("materia")
                );

                PreparedStatement psEj = con.prepareStatement("SELECT * FROM ejemplar WHERE id_libro = ?");
                psEj.setInt(1, libro.getIdLibro());
                ResultSet rsEj = psEj.executeQuery();
                while (rsEj.next()) {
                    libro.addEjemplar(new Ejemplar(
                        rsEj.getInt("id_ejemplar"),
                        rsEj.getString("codigo"),
                        rsEj.getString("estado")
                    ));
                }
                rsEj.close();
                psEj.close();
                libros.add(libro);
            }

            rs.close();
            ps.close();
            acceso.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("libros", libros);
        request.setAttribute("materias", materias);
        request.setAttribute("materia", materia);
        request.setAttribute("numPrestamos", numPrestamos);
        request.setAttribute("view", "catalogo.jsp");
        request.setAttribute("estilo", "estilos/catalogo.css");
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

}
