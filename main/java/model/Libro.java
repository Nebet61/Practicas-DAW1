package model;

import java.util.ArrayList;
import java.util.List;

public class Libro extends Miembro {

    private String ISBN;
    private String titulo;
    private String autor;
    private String materia;
    private List<Ejemplar> ejemplares;

    public Libro(String nombreMiembro, String ISBN, String titulo, String autor, String materia) {
        super(nombreMiembro);
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.materia = materia;
        this.ejemplares = new ArrayList<>();
    }

    // Atributo derivado: estado calculado a partir de los ejemplares
    public String getEstado() {
        for (Ejemplar e : ejemplares) {
            if (e.getEstado().equals("disp")) return "disp";
        }
        for (Ejemplar e : ejemplares) {
            if (e.getEstado().equals("prest")) return "prest";
        }
        return "bloq";
    }

    // Atributo derivado: número de ejemplares
    public int getNumEjemplares() {
        return ejemplares.size();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void addEjemplar(Ejemplar ejemplar) {
        ejemplares.add(ejemplar);
    }
}
