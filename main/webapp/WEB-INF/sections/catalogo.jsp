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
        <button type="submit" class="btn-buscar" id="btn-filtrar">Filtrar</button>
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
    <% if (libros.isEmpty()) { %>
        <p class="sin-resultados">No se han encontrado libros.</p>
    <% } else {
        for (Libro libro : libros) {
            String estado = libro.getEstado();
            String etiqueta = "";
            String claseEstado = "";
            if (estado.equals("disp")) {
                etiqueta = "Disponible";
                claseEstado = "estado-disp";
            } else if (estado.equals("prest")) {
                etiqueta = "Prestado";
                claseEstado = "estado-prest";
            } else {
                etiqueta = "No disponible";
                claseEstado = "estado-bloq";
            }
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
                <% if (userCat != null && estado.equals("disp") && limitePrestamos) { %>
                    <span class="limite-prestamos">Límite de préstamos alcanzado</span>
                <% } else if (userCat != null && estado.equals("disp")) { %>
                    <button type="button" class="btn btn-sm btn-dark"
                        data-bs-toggle="modal"
                        data-bs-target="#modalPrestamo"
                        data-id="<%=libro.getIdLibro()%>"
                        data-titulo="<%=libro.getTitulo()%>">
                        Pedir préstamo
                    </button>
                <% } %>
            </div>
        </div>
    <% } } %>
</div>

<div class="modal fade" id="modalPrestamo" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title ">Confirmar préstamo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Estas apunto de pedir el Libro: "<strong><span id="modalTitulo"></span></strong>"
            </div>
            <div class="modal-footer">
                <form id="formPrestamo" action="<%=request.getContextPath()%>/prestamo" method="post">
                    <input type="hidden" id="inputIdLibro" name="idLibro" value="">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-dark" onclick="abrirConfirmacion()">Siguiente</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalConfirmacion" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">¿Estás seguro?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Vas a pedir prestado el libro: "<strong><span id="modalTitulo2"></span></strong>"
                <br><small>Tendrás 30 días para devolverlo. Si tienes algún problema contacta con la biblioteca.</small>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-dark" onclick="confirmarPrestamo()">Confirmar</button>
            </div>
        </div>
    </div>
</div>

<script>
document.getElementById('modalPrestamo').addEventListener('show.bs.modal', function(e) {
    document.getElementById('inputIdLibro').value = e.relatedTarget.getAttribute('data-id');
    document.getElementById('modalTitulo').textContent = e.relatedTarget.getAttribute('data-titulo');
});

function abrirConfirmacion() {
    document.getElementById('modalTitulo2').textContent = document.getElementById('modalTitulo').textContent;
    bootstrap.Modal.getInstance(document.getElementById('modalPrestamo')).hide();
    new bootstrap.Modal(document.getElementById('modalConfirmacion')).show();
}

function confirmarPrestamo() {
    document.getElementById('formPrestamo').submit();
}

setInterval(function() {
    var btn = document.getElementById('btn-filtrar');
    btn.classList.add('animate__animated', 'animate__tada');
    setTimeout(function() {
        btn.classList.remove('animate__animated', 'animate__tada');
    }, 1000);
}, 4000);
</script>
