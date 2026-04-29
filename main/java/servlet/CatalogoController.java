package servlet;

import model.AccesoBD;
import model.Libro;
import model.Ejemplar;

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
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet("/catalogo")
public class CatalogoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filtro = request.getParameter("filtro");
        if (filtro == null) filtro = "";

        String materia = request.getParameter("materia");
        if (materia == null) materia = "";

        List<Libro> libros = new ArrayList<>();
        List<String> materias = new ArrayList<>();

        try {
            AccesoBD acceso = new AccesoBD();
            Connection con = acceso.getConexion();

            ResultSet rsMat = con.prepareStatement("SELECT DISTINCT materia FROM libro ORDER BY materia").executeQuery();
            while (rsMat.next()) {
                materias.add(rsMat.getString("materia"));
            }
            rsMat.close();

            String sql;
            PreparedStatement ps;

            if (!materia.isEmpty()) {
                sql = "SELECT * FROM libro WHERE (titulo LIKE ? OR autor LIKE ?) AND materia = ?";
                ps = con.prepareStatement(sql);
                String busqueda = "%" + filtro + "%";
                ps.setString(1, busqueda);
                ps.setString(2, busqueda);
                ps.setString(3, materia);
            } else {
                sql = "SELECT * FROM libro WHERE titulo LIKE ? OR autor LIKE ? OR materia LIKE ?";
                ps = con.prepareStatement(sql);
                String busqueda = "%" + filtro + "%";
                ps.setString(1, busqueda);
                ps.setString(2, busqueda);
                ps.setString(3, busqueda);
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

        Map<String, Integer> orden = Map.of("disp", 0, "prest", 1, "bloq", 2);
        libros.sort(Comparator.comparingInt(l -> orden.getOrDefault(l.getEstado(), 3)));

        request.setAttribute("libros", libros);
        request.setAttribute("materias", materias);
        request.setAttribute("filtro", filtro);
        request.setAttribute("materia", materia);
        request.setAttribute("view", "catalogo.jsp");
        request.setAttribute("estilo", "estilos/catalogo.css");
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
