package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String DNI;
    private String cuentaBanco;
    private Historico historico;
    private List<ListaEspera> listaEspera;

    public Usuario(int idUsuario, String nombre, String DNI, String cuentaBanco) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.DNI = DNI;
        this.cuentaBanco = cuentaBanco;
        this.listaEspera = new ArrayList<>();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public List<ListaEspera> getListaEspera() {
        return listaEspera;
    }

    public void addListaEspera(ListaEspera listaEspera) {
        this.listaEspera.add(listaEspera);
    }
}
