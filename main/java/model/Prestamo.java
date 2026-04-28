package model;

import java.util.Date;

public class Prestamo {

    private int idPrestamo;
    private String estado;
    private Date fecha;
    private Date fechaDevolucion;
    private String codigo;
    private Ejemplar ejemplar;
    private Multa multa;

    public Prestamo(int idPrestamo, String estado, Date fecha, Date fechaDevolucion, String codigo, Ejemplar ejemplar) {
        this.idPrestamo = idPrestamo;
        this.estado = estado;
        this.fecha = fecha;
        this.fechaDevolucion = fechaDevolucion;
        this.codigo = codigo;
        this.ejemplar = ejemplar;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
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
