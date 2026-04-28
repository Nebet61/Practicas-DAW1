package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Historico {

    private int idHistorico;
    private Date fechaAlta;
    private List<Prestamo> prestamos;

    public Historico(int idHistorico, Date fechaAlta) {
        this.idHistorico = idHistorico;
        this.fechaAlta = fechaAlta;
        this.prestamos = new ArrayList<>();
    }

    public int getPrestamosActivos() {
        int count = 0;
        for (Prestamo p : prestamos) {
            if (p.getEstado().equals("activo")) count++;
        }
        return count;
    }

    public double getMultado() {
        double total = 0;
        for (Prestamo p : prestamos) {
            if (p.getMulta() != null && !p.getMulta().isPagada()) {
                total += p.getMulta().getImporte();
            }
        }
        return total;
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void addPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }
}
