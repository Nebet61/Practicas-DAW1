package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Miembro {

    private String DNI;
    private String cuentaBanco;
    private List<Libro> listaEspera;

    public Usuario(String nombreMiembro, String DNI, String cuentaBanco) {
        super(nombreMiembro);
        this.DNI = DNI;
        this.cuentaBanco = cuentaBanco;
        this.listaEspera = new ArrayList<>();
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public List<Libro> getListaEspera() {
        return listaEspera;
    }

    public void addLibroEspera(Libro libro) {
        listaEspera.add(libro);
    }
}
