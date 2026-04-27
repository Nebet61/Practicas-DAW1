package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Historico extends Miembro {

    private Date fechaAlta;
    private List<Prestamo> prestamos;

    public Historico(String nombreMiembro, Date fechaAlta) {
        super(nombreMiembro);
        this.fechaAlta = fechaAlta;
        this.prestamos = new ArrayList<>();
    }

    // Esto es un atributo derivado y hace número de préstamos activos
    public int getPrestamosActivos() {
        int count = 0;
        for (Prestamo p : prestamos) {
            if (p.getEstado().equals("activo")) count++;
        }
        return count;
    }

    // Vale esto es un atributo derivado que suma total de multas
    public double getMultado() {
        double total = 0;
        for (Prestamo p : prestamos) {
            if (p.getMulta() != null) {
                total += p.getMulta().getImporte();
            }
        }
        return total;
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
