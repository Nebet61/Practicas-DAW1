<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Usuario" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>

<nav class="navbar navbar-expand-md cabecera">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/home">Biblioteca Chamorro</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navMenu">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/catalogo">Catálogo</a>
                </li>
                <% if (user != null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/misprestamos">Mis préstamos</a>
                </li>
                <% } %>
            </ul>

            <div class="d-flex align-items-center gap-3">
                <% if (user != null) { %>
                    <span class="tag-usuario"><%= user.getNombre() %></span>
                    <a href="<%=request.getContextPath()%>/logout" class="boton-salir">Cerrar sesión</a>
                <% } else { %>
                    <a class="nav-link enlace-dorado" href="#" data-bs-toggle="modal" data-bs-target="#modalLogin">Iniciar sesión</a>
                <% } %>
            </div>
        </div>
    </div>
</nav>

<% if (session.getAttribute("loginError") != null) { %>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        new bootstrap.Modal(document.getElementById("modalLogin")).show();
    });
</script>
<% } %>

<div class="modal fade" id="modalLogin" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style="border: none; border-radius: 6px;">
            <div class="modal-header" style="background-color: #1B2A4A; border-radius: 6px 6px 0 0;">
                <h5 class="modal-title" style="color: #F5F0E8; font-weight: 400;">Iniciar sesión</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body" style="padding: 30px; background-color: #F5F0E8;">
                <% String error = (String) session.getAttribute("loginError");
                   if (error != null) { %>
                    <div class="alert alert-danger py-2" style="font-size: 14px;"><%= error %></div>
                <%     session.removeAttribute("loginError");
                   } %>
                <form action="<%=request.getContextPath()%>/login" method="post">
                    <div class="mb-3">
                        <label style="font-size: 14px; color: #1B2A4A; font-weight: 600;">DNI</label>
                        <input type="text" name="dni" class="form-control mt-1" placeholder="12345678A" required>
                    </div>
                    <div class="mb-4">
                        <label style="font-size: 14px; color: #1B2A4A; font-weight: 600;">Contraseña</label>
                        <input type="password" name="password" class="form-control mt-1" required>
                    </div>
                    <button type="submit" class="btn-modal">Entrar</button>
                </form>
            </div>
        </div>
    </div>
</div>
