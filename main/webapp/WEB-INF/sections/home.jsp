<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Usuario" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>

<section class="portada">
    <% if (user != null) { %>
        <h1>Hola, bienvenido/a <strong><%= user.getNombre() %></strong></h1>
        <p>Explora nuestro catálogo, consulta tus préstamos activos o reserva tu próximo libro.</p>
    <% } else { %>
        <h1>Te damos la bienvenida a <strong>Biblioteca Chamorro</strong></h1>
        <p>Accede a nuestro catálogo de libros, gestiona tus préstamos y descubre nuevas lecturas.</p>
    <% } %>
    <a href="<%=request.getContextPath()%>/catalogo" class="btn-catalogo">Ver catálogo</a>
</section>

<section class="servicios d-flex">

<div style="overflow: hidden; height: 480px; width: 940px; position: relative; border-radius: 20px;">
	<iframe style="position: absolute; top: -60px; width: 100%; height: calc(100% + 60px); border: none;"
		src="https://www.google.com/maps/d/embed?mid=1dxGL5x9348WFfipF_tqxWAvcQiV4SVc&ehbc=2E312F"></iframe>
</div>


    <div class="container" style="max-width: 640px;">
        <div class="accordion accordion-flush" id="acordeonServicios">
		<h1 class="mb-5">¿Como funciona?</h1>
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#servicio1">
                        Acceso con DNI
                    </button>
                </h2>
                <div id="servicio1" class="accordion-collapse collapse" data-bs-parent="#acordeonServicios">
                    <div class="accordion-body">
                        Identifícate con tu DNI y contraseña para gestionar tus préstamos de forma segura.
                    </div>
                </div>
            </div>

            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#servicio2">
                        Hasta 3 libros simultáneos
                    </button>
                </h2>
                <div id="servicio2" class="accordion-collapse collapse" data-bs-parent="#acordeonServicios">
                    <div class="accordion-body">
                        Puedes tener en préstamo hasta 3 ejemplares al mismo tiempo.
                    </div>
                </div>
            </div>

            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#servicio3">
                        Lista de espera
                    </button>
                </h2>
                <div id="servicio3" class="accordion-collapse collapse" data-bs-parent="#acordeonServicios">
                    <div class="accordion-body">
                        Si el libro que buscas no está disponible, únete a la lista de espera y te avisamos cuando esté listo.
                    </div>
                </div>
            </div>

        </div>
    </div>
    
    
</section>

