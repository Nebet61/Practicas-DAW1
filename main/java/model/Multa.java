package model;

import java.util.Date;

public class Multa {

    private int idMulta;
    private String descripcion;
    private Date fecha;
    private boolean pagada;
    private double importe;

    public Multa(int idMulta, String descripcion, Date fecha, boolean pagada, double importe) {
        this.idMulta = idMulta;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.pagada = pagada;
        this.importe = importe;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
