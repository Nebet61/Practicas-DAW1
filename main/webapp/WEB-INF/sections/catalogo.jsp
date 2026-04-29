<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Libro, model.Usuario, java.util.List" %>
<%
    List<Libro> libros = (List<Libro>) request.getAttribute("libros");
    List<String> materias = (List<String>) request.getAttribute("materias");
    String filtro = (String) request.getAttribute("filtro");
    String materia = (String) request.getAttribute("materia");
    if (filtro == null) filtro = "";
    if (materia == null) materia = "";

    Usuario userCat = (Usuario) session.getAttribute("usuario");
    int numPrestamos = request.getAttribute("numPrestamos") != null ? (Integer) request.getAttribute("numPrestamos") : 0;
    boolean limitePrestamos = numPrestamos >= 3;

    String prestamoOk = (String) session.getAttribute("prestamoOk");
    String prestamoError = (String) session.getAttribute("prestamoError");
    if (prestamoOk != null) session.removeAttribute("prestamoOk");
    if (prestamoError != null) session.removeAttribute("prestamoError");
%>

<div class="catalogo-cabecera">
    <h2>Catálogo</h2>
    <form class="form-busqueda" action="<%=request.getContextPath()%>/catalogo" method="get">
        <input type="text" name="filtro" class="input-busqueda" placeholder="Buscar por título o autor..." value="<%=filtro%>">
        <select name="materia" class="select-materia">
            <option value="">Todas las materias</option>
            <% if (materias != null) {
                for (String m : materias) { %>
                <option value="<%=m%>" <%=m.equals(materia) ? "selected" : ""%>><%=m%></option>
            <% } } %>
        </select>
        <button type="submit" class="btn-buscar">Buscar</button>
        <% if (!filtro.isEmpty() || !materia.isEmpty()) { %>
            <a href="<%=request.getContextPath()%>/catalogo" class="btn-limpiar">Limpiar</a>
        <% } %>
    </form>
</div>

<% if (prestamoOk != null) { %>
    <div class="aviso-prestamo aviso-ok"><%=prestamoOk%></div>
<% } %>
<% if (prestamoError != null) { %>
    <div class="aviso-prestamo aviso-error"><%=prestamoError%></div>
<% } %>

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
                <% if (userCat != null && estado.equals("disp")) { %>
                    <% if (limitePrestamos) { %>
                        <span class="limite-prestamos">Límite de préstamos alcanzado</span>
                    <% } else { %>
                        <button type="button" class="btn-prestamo"
                            onclick="abrirConfirmacion(<%=libro.getIdLibro()%>, '<%=libro.getTitulo().replace("'", "\\'")%>')">
                            Pedir préstamo
                        </button>
                    <% } %>
                <% } %>
            </div>
        </div>
    <%  } } %>
</div>

<div class="modal fade" id="modalConfirmar" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style="border: none; border-radius: 6px;">
            <div class="modal-header" style="background-color: #1B2A4A; border-radius: 6px 6px 0 0;">
                <h5 class="modal-title" style="color: #F5F0E8; font-weight: 400;">Confirmar préstamo</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body" style="padding: 24px 30px; background-color: #F5F0E8;">
                <p style="color: #1B2A4A; font-size: 14px; margin-bottom: 6px;">¿Quieres pedir prestado el libro:</p>
                <p id="tituloConfirmar" style="color: #1B2A4A; font-size: 15px; font-weight: 600; margin-bottom: 0;"></p>
            </div>
            <div class="modal-footer" style="background-color: #F5F0E8; border-top: 1px solid #ddd; padding: 12px 30px;">
                <form id="formConfirmar" action="<%=request.getContextPath()%>/prestamo" method="post">
                    <input type="hidden" id="inputIdLibro" name="idLibro" value="">
                    <button type="button" class="btn-cancelar-modal" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn-modal">Confirmar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
function abrirConfirmacion(idLibro, titulo) {
    document.getElementById("inputIdLibro").value = idLibro;
    document.getElementById("tituloConfirmar").textContent = titulo;
    new bootstrap.Modal(document.getElementById("modalConfirmar")).show();
}
</script>
