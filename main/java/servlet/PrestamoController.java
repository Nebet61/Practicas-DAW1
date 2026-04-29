package servlet;

import model.AccesoBD;
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
import java.time.LocalDate;

@WebServlet("/prestamo")
public class PrestamoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/catalogo");
            return;
        }

        int idLibro;
        try {
            idLibro = Integer.parseInt(request.getParameter("idLibro"));
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/catalogo");
            return;
        }

        try {
            AccesoBD acceso = new AccesoBD();
            Connection con = acceso.getConexion();

            PreparedStatement psHist = con.prepareStatement(
                "SELECT id_historico FROM historico WHERE id_usuario = ?"
            );
            psHist.setInt(1, user.getIdUsuario());
            ResultSet rsHist = psHist.executeQuery();
            if (!rsHist.next()) {
                rsHist.close();
                psHist.close();
                acceso.desconectar();
                request.getSession().setAttribute("prestamoError", "No tienes ficha de usuario en la biblioteca.");
                response.sendRedirect(request.getContextPath() + "/catalogo");
                return;
            }
            int idHistorico = rsHist.getInt("id_historico");
            rsHist.close();
            psHist.close();

            PreparedStatement psCount = con.prepareStatement(
                "SELECT COUNT(*) FROM prestamo WHERE id_historico = ? AND estado = 'activo'"
            );
            psCount.setInt(1, idHistorico);
            ResultSet rsCount = psCount.executeQuery();
            int activos = 0;
            if (rsCount.next()) activos = rsCount.getInt(1);
            rsCount.close();
            psCount.close();

            if (activos >= 3) {
                acceso.desconectar();
                request.getSession().setAttribute("prestamoError", "Has alcanzado el límite de 3 préstamos activos.");
                response.sendRedirect(request.getContextPath() + "/catalogo");
                return;
            }

            PreparedStatement psEj = con.prepareStatement(
                "SELECT id_ejemplar FROM ejemplar WHERE id_libro = ? AND estado = 'disp' LIMIT 1"
            );
            psEj.setInt(1, idLibro);
            ResultSet rsEj = psEj.executeQuery();
            if (!rsEj.next()) {
                rsEj.close();
                psEj.close();
                acceso.desconectar();
                request.getSession().setAttribute("prestamoError", "No hay ejemplares disponibles.");
                response.sendRedirect(request.getContextPath() + "/catalogo");
                return;
            }
            int idEjemplar = rsEj.getInt("id_ejemplar");
            rsEj.close();
            psEj.close();

            con.setAutoCommit(false);

            PreparedStatement psUpd = con.prepareStatement(
                "UPDATE ejemplar SET estado = 'prest' WHERE id_ejemplar = ?"
            );
            psUpd.setInt(1, idEjemplar);
            psUpd.executeUpdate();
            psUpd.close();

            String codigo = "P-" + LocalDate.now().getYear() + "-" + System.currentTimeMillis() % 100000;

            PreparedStatement psIns = con.prepareStatement(
                "INSERT INTO prestamo (estado, fecha, fecha_devolucion, codigo, id_ejemplar, id_historico) VALUES ('activo', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 MONTH), ?, ?, ?)"
            );
            psIns.setString(1, codigo);
            psIns.setInt(2, idEjemplar);
            psIns.setInt(3, idHistorico);
            psIns.executeUpdate();
            psIns.close();

            con.commit();
            acceso.desconectar();

            request.getSession().setAttribute("prestamoOk", "Préstamo realizado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("prestamoError", "Error al procesar el préstamo.");
        }

        response.sendRedirect(request.getContextPath() + "/catalogo");
    }
}
