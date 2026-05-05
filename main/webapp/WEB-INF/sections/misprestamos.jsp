<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.PrestamoDetalle, java.util.List" %>
<%
    List<PrestamoDetalle> prestamos = (List<PrestamoDetalle>) request.getAttribute("prestamos");
%>

<div class="prestamos-cabecera">
    <h2>Mis préstamos</h2>
</div>

<div class="lista-prestamos">
    <% if (prestamos.isEmpty()) { %>
        <p class="sin-prestamos">No tienes préstamos activos en este momento.</p>
    <% } else {
        for (PrestamoDetalle p : prestamos) {
            boolean vencido = p.getEstado().equals("vencido");
    %>
        <div class="tarjeta-prestamo <%=vencido ? "prestamo-vencido" : ""%>">
            <div class="prestamo-libro">
                <h5><%=p.getTitulo()%></h5>
                <span class="prestamo-autor"><%=p.getAutor()%></span>
                <span class="prestamo-materia"><%=p.getMateria()%></span>
            </div>
            <div class="prestamo-datos">
                <span class="prestamo-codigo"><%=p.getCodigo()%></span>
                <span class="prestamo-fecha">Desde: <%=p.getFecha()%></span>
                <span class="prestamo-fecha">Devolver antes de: <%=p.getFechaDevolucion()%></span>
                <span class="<%=vencido ? "estado-vencido" : "estado-activo"%>">
                    <%=vencido ? "Por devolver" : "Activo"%>
                </span>
            </div>
        </div>
    <% } } %>
</div>
