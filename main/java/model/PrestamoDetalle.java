package model;

public class PrestamoDetalle {

    private String titulo;
    private String autor;
    private String materia;
    private String fecha;
    private String fechaDevolucion;
    private String estado;
    private String codigo;

    public PrestamoDetalle(String titulo, String autor, String materia, String fecha, String fechaDevolucion, String estado, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.materia = materia;
        this.fecha = fecha;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getMateria() {
        return materia;
    }

    public String getFecha() {
        return fecha;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public String getCodigo() {
        return codigo;
    }
}
