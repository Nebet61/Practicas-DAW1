package model;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String DNI;
    private String password;
    private String cuentaBanco;

    public Usuario(int idUsuario, String nombre, String DNI, String password, String cuentaBanco) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.DNI = DNI;
        this.password = password;
        this.cuentaBanco = cuentaBanco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

}
