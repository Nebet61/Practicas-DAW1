<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Libro, java.util.List" %>
<%
    List<Libro> libros = (List<Libro>) request.getAttribute("libros");
    String filtro = (String) request.getAttribute("filtro");
    if (filtro == null) filtro = "";
%>

<div class="catalogo-cabecera">
    <h2>Catálogo</h2>
    <form class="form-busqueda" action="<%=request.getContextPath()%>/catalogo" method="get">
        <input type="text" name="filtro" class="input-busqueda" placeholder="Buscar por título, autor o materia..." value="<%=filtro%>">
        <button type="submit" class="btn-buscar">Buscar</button>
        <% if (!filtro.isEmpty()) { %>
            <a href="<%=request.getContextPath()%>/catalogo" class="btn-limpiar">Limpiar</a>
        <% } %>
    </form>
</div>

<div class="lista-libros">
    <% if (libros == null || libros.isEmpty()) { %>
        <p class="sin-resultados">No se han encontrado libros.</p>
    <% } else {
        for (Libro libro : libros) {
            String estado = libro.getEstado();
            String etiqueta = estado.equals("disp") ? "Disponible" : estado.equals("prest") ? "Prestado" : "No disponible";
            String claseEstado = estado.equals("disp") ? "estado-disp" : estado.equals("prest") ? "estado-prest" : "estado-bloq";
            int disponibles = 0;
            for (model.Ejemplar e : libro.getEjemplares()) {
                if (e.getEstado().equals("disp")) disponibles++;
            }
    %>
        <div class="tarjeta-libro">
            <div class="libro-info">
                <h5><%=libro.getTitulo()%></h5>
                <span class="libro-autor"><%=libro.getAutor()%></span>
                <span class="libro-materia"><%=libro.getMateria()%></span>
            </div>
            <div class="libro-estado">
                <span class="<%=claseEstado%>"><%=etiqueta%></span>
                <span class="libro-ejemplares"><%=disponibles%> / <%=libro.getNumEjemplares()%> disponibles</span>
            </div>
        </div>
    <%  } } %>
</div>
