<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Usuario" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>

<section class="portada">
    <% if (user != null) { %>
        <h1>Bienvenido, <strong><%= user.getNombre().split(" ")[0] %></strong></h1>
        <p>Explora nuestro catálogo, consulta tus préstamos activos o reserva tu próximo libro.</p>
        <a href="<%=request.getContextPath()%>/catalogo" class="btn-catalogo">Ver catálogo</a>
        <a href="<%=request.getContextPath()%>/prestamos" class="btn-secundario">Mis préstamos</a>
    <% } else { %>
        <h1>Bienvenido a <strong>Biblioteca Chamorro</strong></h1>
        <p>Accede a nuestro catálogo de libros, gestiona tus préstamos y descubre nuevas lecturas.</p>
        <a href="<%=request.getContextPath()%>/catalogo" class="btn-catalogo">Ver catálogo</a>
        <a href="<%=request.getContextPath()%>/login" class="btn-secundario">Iniciar sesión</a>
    <% } %>
</section>

<section class="datos">
    <div class="container">
        <div class="d-flex justify-content-center align-items-center flex-wrap">
            <div class="dato">
                <div class="dato-numero">30</div>
                <div class="dato-texto">Títulos</div>
            </div>
            <div class="separador-vertical d-none d-md-block mx-2"></div>
            <div class="dato">
                <div class="dato-numero">67</div>
                <div class="dato-texto">Ejemplares</div>
            </div>
            <div class="separador-vertical d-none d-md-block mx-2"></div>
            <div class="dato">
                <div class="dato-numero">15</div>
                <div class="dato-texto">Usuarios registrados</div>
            </div>
            <div class="separador-vertical d-none d-md-block mx-2"></div>
            <div class="dato">
                <div class="dato-numero">6</div>
                <div class="dato-texto">Bibliotecarios</div>
            </div>
        </div>
    </div>
</section>

<section class="servicios">
    <div class="container" style="max-width: 640px;">
        <div class="servicio">
            <div class="servicio-icono">+</div>
            <div>
                <h5>Acceso con DNI</h5>
                <p>Identifícate con tu DNI y contraseña para gestionar tus préstamos de forma segura.</p>
            </div>
        </div>
        <div class="servicio">
            <div class="servicio-icono">+</div>
            <div>
                <h5>Hasta 3 libros simultáneos</h5>
                <p>Puedes tener en préstamo hasta 3 ejemplares al mismo tiempo.</p>
            </div>
        </div>
        <div class="servicio">
            <div class="servicio-icono">+</div>
            <div>
                <h5>Lista de espera</h5>
                <p>Si el libro que buscas no está disponible, únete a la lista de espera y te avisamos cuando esté listo.</p>
            </div>
        </div>
    </div>
</section>
