<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Libro, model.Usuario, java.util.List" %>
<%
    List<Libro> libros = (List<Libro>) request.getAttribute("libros");
    List<String> materias = (List<String>) request.getAttribute("materias");
    String materia = (String) request.getAttribute("materia");
    if (materia == null) materia = "";

    Usuario userCat = (Usuario) session.getAttribute("usuario");
    int numPrestamos = (Integer) request.getAttribute("numPrestamos");
    boolean limitePrestamos = numPrestamos >= 3;

    String prestamoOk = (String) session.getAttribute("prestamoOk");
    String prestamoError = (String) session.getAttribute("prestamoError");
    if (prestamoOk != null) session.removeAttribute("prestamoOk");
    if (prestamoError != null) session.removeAttribute("prestamoError");
%>

<div class="catalogo-cabecera">
    <h2>Catálogo</h2>
    <form class="form-busqueda" action="<%=request.getContextPath()%>/catalogo" method="get">
        <select name="materia" class="select-materia">
            <option value="">Todas las materias</option>
            <% for (String m : materias) { %>
                <option value="<%=m%>" <%=m.equals(materia) ? "selected" : ""%>><%=m%></option>
            <% } %>
        </select>
        <button type="submit" class="btn-buscar">Filtrar</button>
        <% if (!materia.isEmpty()) { %>
            <a href="<%=request.getContextPath()%>/catalogo" class="btn-limpiar">Ver todos</a>
        <% } %>
    </form>
</div>

<% if (prestamoOk != null) { %>
    <div class="mensaje-ok"><%=prestamoOk%></div>
<% } %>
<% if (prestamoError != null) { %>
    <div class="mensaje-error"><%=prestamoError%></div>
<% } %>

<div class="lista-libros">
    <% if (libros == null || libros.isEmpty()) { %>
        <p class="sin-resultados">No se han encontrado libros.</p>
    <% } else {
        for (Libro libro : libros) {
            String estado = libro.getEstado();
            String etiqueta = estado.equals("disp") ? "Disponible" : estado.equals("prest") ? "Prestado" : "No disponible";
            String claseEstado = estado.equals("disp") ? "estado-disp" : estado.equals("prest") ? "estado-prest" : "estado-bloq";
    %>
        <div class="tarjeta-libro">
            <div class="libro-info">
                <h5><%=libro.getTitulo()%></h5>
                <span class="libro-autor"><%=libro.getAutor()%></span>
                <span class="libro-materia"><%=libro.getMateria()%></span>
            </div>
            <div class="libro-estado">
                <span class="<%=claseEstado%>"><%=etiqueta%></span>
                <% if (userCat != null && estado.equals("disp")) { %>
                    <% if (limitePrestamos) { %>
                        <span class="limite-prestamos">Límite de préstamos alcanzado</span>
                    <% } else { %>
                        <form action="<%=request.getContextPath()%>/prestamo" method="post">
                            <input type="hidden" name="idLibro" value="<%=libro.getIdLibro()%>">
                            <button type="submit" class="btn-prestamo">Pedir préstamo</button>
                        </form>
                    <% } %>
                <% } %>
            </div>
        </div>
    <% } } %>
</div>
