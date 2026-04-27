package model;

public class Bibliotecario extends Miembro {

    private String email;
    private boolean disponible;

    public Bibliotecario(String nombreMiembro, String email, boolean disponible) {
        super(nombreMiembro);
        this.email = email;
        this.disponible = disponible;
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
