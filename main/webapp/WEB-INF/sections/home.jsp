<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Usuario" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>

<section class="hero">
    <% if (user != null) { %>
        <h1>Bienvenido, <strong><%= user.getNombre().split(" ")[0] %></strong></h1>
        <p>Explora nuestro cat&aacute;logo, consulta tus pr&eacute;stamos activos o reserva tu pr&oacute;ximo libro.</p>
        <a href="<%=request.getContextPath()%>/catalogo" class="btn-catalogo">Ver cat&aacute;logo</a>
        <a href="<%=request.getContextPath()%>/prestamos" class="btn-login-home">Mis pr&eacute;stamos</a>
    <% } else { %>
        <h1>Bienvenido a <strong>Biblioteca Chamorro</strong></h1>
        <p>Accede a nuestro cat&aacute;logo de libros, gestiona tus pr&eacute;stamos y descubre nuevas lecturas.</p>
        <a href="<%=request.getContextPath()%>/catalogo" class="btn-catalogo">Ver cat&aacute;logo</a>
        <a href="<%=request.getContextPath()%>/login" class="btn-login-home">Iniciar sesi&oacute;n</a>
    <% } %>
</section>

<section class="stats-section">
    <div class="container">
        <div class="d-flex justify-content-center align-items-center gap-0 flex-wrap">
            <div class="stat-card">
                <div class="stat-number">30</div>
                <div class="stat-label">T&iacute;tulos</div>
            </div>
            <div class="stat-divider d-none d-md-block mx-2"></div>
            <div class="stat-card">
                <div class="stat-number">67</div>
                <div class="stat-label">Ejemplares</div>
            </div>
            <div class="stat-divider d-none d-md-block mx-2"></div>
            <div class="stat-card">
                <div class="stat-number">15</div>
                <div class="stat-label">Usuarios registrados</div>
            </div>
            <div class="stat-divider d-none d-md-block mx-2"></div>
            <div class="stat-card">
                <div class="stat-number">6</div>
                <div class="stat-label">Bibliotecarios</div>
            </div>
        </div>
    </div>
</section>

<section class="info-section">
    <div class="container" style="max-width: 640px;">
        <div class="info-item">
            <div class="info-icon">+</div>
            <div>
                <h5>Acceso con DNI</h5>
                <p>Ident&iacute;ficate con tu DNI y contrase&ntilde;a para gestionar tus pr&eacute;stamos de forma segura.</p>
            </div>
        </div>
        <div class="info-item">
            <div class="info-icon">+</div>
            <div>
                <h5>Hasta 3 libros simult&aacute;neos</h5>
                <p>Puedes tener en pr&eacute;stamo hasta 3 ejemplares al mismo tiempo.</p>
            </div>
        </div>
        <div class="info-item">
            <div class="info-icon">+</div>
            <div>
                <h5>Lista de espera</h5>
                <p>Si el libro que buscas no est&aacute; disponible, &uacute;nete a la lista de espera y te avisamos cuando est&eacute; listo.</p>
            </div>
        </div>
    </div>
</section>
