<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<footer class="footer-biblioteca">
    <div class="container-fluid">
        <div class="d-flex flex-column flex-md-row justify-content-between align-items-center gap-2">
            <span class="footer-brand">Biblioteca Chamorro</span>
            <div class="d-flex gap-3">
                <a href="#">Aviso legal</a>
                <a href="#">Contacto</a>
                <a href="#">Accesibilidad</a>
            </div>
        </div>
        <hr class="footer-divider">
        <div class="text-center">
            &copy; <%= new java.util.Calendar.Builder().build().get(java.util.Calendar.YEAR) %> Biblioteca Chamorro &mdash; Todos los derechos reservados
        </div>
    </div>
</footer>
