package model;

public class Bibliotecario {

    private int idBibliotecario;
    private String nombre;
    private String email;
    private boolean disponible;

    public Bibliotecario(int idBibliotecario, String nombre, String email, boolean disponible) {
        this.idBibliotecario = idBibliotecario;
        this.nombre = nombre;
        this.email = email;
        this.disponible = disponible;
    }

    public int getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
