<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='java.util.*' %>
<%@ page import="Modelo.*" %>
<html>
	<head>
		<title>Presentacion</title>
		<%@ include file='head.jsp' %>
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div class="container"></div>
		<div class="sub-margin">
			<% List<Producto> productos = (List<Producto>)request.getAttribute("productos");
			if(productos!=null){
				for(Producto producto:productos) { %>
					<div class="col-md-4 producto" align="center">
						<img class="img-responsive imagenes-vista" src="imagenes/<%= producto.getId()%>.png">
						<div class="margen"></div>
						
							<p><%= producto.getDenominacion()%> <%= producto.getMarca() %></p>
							<p><%= producto.getEnvase()%></p>
							<p><%= producto.getCantidad()%></p>
							<p><%= producto.getPrecio()%> €/u</p>
							<% if(session.getAttribute("cliente") != null) { %>
								<div class="margen"></div>
								<form action="anyadir" method="GET">
									<% 
										if(producto.getHayStock()) {
									%>
									<input type="hidden" name="id" value="<%= producto.getIdPresentacion() %>">
								 	<input class="clouds-flat-button" name="unidades" id="cantidad" type="number" value="1" min="1" max="<%= producto.getStock() %>">
									<div class="margen"></div>
	      							<input class="emerald-flat-button" type="submit" value="Comprar">

	      							<% } else { %>
	      									<p class="agotado">Producto agotado en estos momentos</p>
	      							<%	} %>
								</form>
							<% } %>
					</div>
					<div class="margen"></div>
			<% }
			} %>
		</div>
		</div>
	</body>
</html>