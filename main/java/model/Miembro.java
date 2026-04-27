package model;

public abstract class Miembro {

    private String nombreMiembro;

    public Miembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }

    public String getNombreMiembro() {
        return nombreMiembro;
    }

    public void setNombreMiembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }
}
