package model;

import java.util.ArrayList;
import java.util.List;

public class Libro {

    private int idLibro;
    private String ISBN;
    private String titulo;
    private String autor;
    private String materia;
    private List<Ejemplar> ejemplares;

    public Libro(int idLibro, String ISBN, String titulo, String autor, String materia) {
        this.idLibro = idLibro;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.materia = materia;
        this.ejemplares = new ArrayList<>();
    }

    public String getEstado() {
        for (Ejemplar e : ejemplares) {
            if (e.getEstado().equals("disp")) return "disp";
        }
        for (Ejemplar e : ejemplares) {
            if (e.getEstado().equals("prest")) return "prest";
        }
        return "bloq";
    }

    public int getNumEjemplares() {
        return ejemplares.size();
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
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
