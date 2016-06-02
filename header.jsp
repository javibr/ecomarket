<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import="Modelo.*" %>

<header>
    <% Cliente cliente = (Cliente)session.getAttribute("cliente"); %>
    <nav class="navbar navbar-custom" role="navigation">
        <div class="header-content">
        	<!-- Solo en moviles -->
            <div class="navbar-header">
            	<div class="iconos-responsive">
                    <a href="https://twitter.com/_EcoMarket_" target="_blank" class="navbar-toggle menu"><i class="fa fa-twitter" title="Twitter"></i></a>
	                <a href="carro" class="navbar-toggle menu"><i class="fa fa-shopping-cart" title='Carro'></i></a>
	                <a href="search" class="navbar-toggle menu"><i class="fa fa-search" title='Buscar'></i></a>            		
            		<% if (cliente == null) { %>
                        <a href="login" class="navbar-toggle menu"><i class="fa fa-sign-in" title="Entrar"></i></a>
                        <a href="registro" class="navbar-toggle menu"><i class="fa fa-user-plus" title="Registro"></i></a>
                    <% } else { %>
                        <a href="cerrar" class="navbar-toggle menu"><i class="fa fa-times" title="Salir"></i></a>
                    <% } %>
            	</div>
                <a class="page-scroll logo" href="search">
                    <img id="top-logo" class="brand" src="imagenes/logo.png" alt="EcoMarket">
                </a>
            </div>
            <!-- Solo ordenadores -->
            <div class="iconos collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <% if (cliente == null) { %>
                        <li>
                            <a href="registro">Sign in <i class="fa fa-user-plus"></i></a>
                        </li>
                        <li>
                            <a href="login">Log in <i class="fa fa-sign-in"></i></a>
                        </li>
                    <% } else { %>
                        <li>
                            <a href="cerrar">Log out <%= cliente.getNombre() %> <i class="fa fa-times"></i></a>
                        </li>
                    <% } %>
                    <li>
                    	<a href="search">Buscar <i class="fa fa-search"></i></a>
                    </li>
                    <li>
                    	<a href="carro">Carro <i class="fa fa-shopping-cart"></i></a>
                    </li>
                    <li>
                        <a href="https://twitter.com/_EcoMarket_" target="_blank">Twitter <i class="fa fa-twitter"></i></a>
                    </li>	
                </ul>
            </div>
        </div>
    </nav>
</header>