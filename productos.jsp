<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='java.util.*' %>
<%@ page import="Modelo.*" %>
<html>
	<head>
		<title>Producto</title>
		<%@ include file='head.jsp' %>
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div class="container"></div>
		<div class="sub-margin" align="center">
			<% List<Producto> productos = (List<Producto>)session.getAttribute("busqueda");
			if(productos!=null){
				for(Producto producto:productos) { %>
					<div class="col-md-3 col-sm-6 producto">
						<a href = "presentacion?id=<%= producto.getId() %>"><img class="img-responsive imagenes-vista" src="imagenes/<%= producto.getId()%>.png"></a>
						<div class="margen"></div>
						<p><%= producto.getDenominacion()%> <%= producto.getMarca() %></p>
					</div>
					<div class="margen"></div>
			<% }
			} %>
		</div>
	</body>
</html>