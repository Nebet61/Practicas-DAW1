package servlet;

import model.AccesoBD;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String password = request.getParameter("password");

        try {
            AccesoBD acceso = new AccesoBD();
            Connection con = acceso.getConexion();
            String sql = "SELECT * FROM usuario WHERE DNI = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario user = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("DNI"),
                    rs.getString("password"),
                    rs.getString("cuenta_banco")
                );
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);
            } else {
                request.getSession().setAttribute("loginError", "DNI o contraseña incorrectos.");
            }

            rs.close();
            ps.close();
            acceso.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("loginError", "DNI o contraseña incorrectos.");
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }
}
