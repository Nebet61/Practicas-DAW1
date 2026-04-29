package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoBD {

    private String user = "root";
    private String pass = "ROOT";
    private String server = "localhost";
    private String db = "biblioteca";
    private int port = 3306;

    private Connection conexion;

    public AccesoBD() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://" + server + ":" + port + "/" + db + "?serverTimezone=Europe/Madrid";
        this.conexion = DriverManager.getConnection(url, user, pass);
        System.out.println("Conexión establecida con " + db);
    }

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada con " + db);
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
