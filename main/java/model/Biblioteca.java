package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private int idBiblioteca;
    private String nombre;
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<Bibliotecario> bibliotecarios;

    public Biblioteca(int idBiblioteca, String nombre) {
        this.idBiblioteca = idBiblioteca;
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
        this.bibliotecarios = new ArrayList<>();
    }

    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void addLibro(Libro libro) {
        libros.add(libro);
    }

    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public void addBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarios.add(bibliotecario);
    }
}
