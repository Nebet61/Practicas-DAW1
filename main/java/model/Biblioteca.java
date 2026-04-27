package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca extends Miembro {

    private List<Usuario> usuarios;
    private List<Libro> libros;

    public Biblioteca(String nombreMiembro) {
        super(nombreMiembro);
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
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
}
