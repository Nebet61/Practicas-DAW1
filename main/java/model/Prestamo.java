package model;

import java.util.Date;

public class Prestamo extends Miembro {

    private String estado;
    private Date fecha;
    private Date fechaDevolucion;
    private String codigo;
    private Ejemplar ejemplar;
    private Multa multa; // puede no tener multa (0..1)

    public Prestamo(String nombreMiembro, String estado, Date fecha, Date fechaDevolucion, String codigo, Ejemplar ejemplar) {
        super(nombreMiembro);
        this.estado = estado;
        this.fecha = fecha;
        this.fechaDevolucion = fechaDevolucion;
        this.codigo = codigo;
        this.ejemplar = ejemplar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }
}
