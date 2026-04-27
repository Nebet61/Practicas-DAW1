package model;

public class Ejemplar extends Miembro {

    private String codigo;
    private String estado; // disp, prest, bloq

    public Ejemplar(String nombreMiembro, String codigo, String estado) {
        super(nombreMiembro);
        this.codigo = codigo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
