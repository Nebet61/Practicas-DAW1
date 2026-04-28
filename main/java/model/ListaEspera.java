package model;

import java.util.Date;

public class ListaEspera {

    private Libro libro;
    private Date fechaSolicitud;
    private String estado; // pendiente, notificado

    public ListaEspera(Libro libro, Date fechaSolicitud, String estado) {
        this.libro = libro;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
