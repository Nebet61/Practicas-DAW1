<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Usuario" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>
<nav class="navbar navbar-expand-md navbar-biblioteca">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/home">Biblioteca Chamorro</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navMenu">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/home">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cat&aacute;logo</a>
                </li>
                <% if (user != null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="#">Mis pr&eacute;stamos</a>
                </li>
                <% } %>
            </ul>

            <div class="d-flex align-items-center gap-3">
                <% if (user != null) { %>
                    <span class="user-badge"><%= user.getNombre() %></span>
                    <a href="<%=request.getContextPath()%>/logout" class="btn-cerrar">Cerrar sesi&oacute;n</a>
                <% } else { %>
                    <a class="nav-link nav-link-accent" href="<%=request.getContextPath()%>/login">Iniciar sesi&oacute;n</a>
                <% } %>
            </div>
        </div>
    </div>
</nav>
