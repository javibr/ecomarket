<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='java.util.*' %>
<%@ page import="Modelo.*" %>

<html>
	<head>
		<title>Compra</title>
		<%@ include file='head.jsp' %>
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div class="container">
			<div class="sub-margin"></div>
			<h2>Su compra no se ha podido realizar, su carro contiene productos agotados o fuera de precio.</h2>
			<form action="carro" method="post">
				<% List<Integer> sinStock = (List<Integer>)request.getAttribute("sinStock");
					int i = 1;
					for(Integer stock:sinStock) { %>
						<input type="hidden" name="<%= i %>" value="<%= stock %>">
						<% i++;
					} %>
				<input type="hidden" name="tam" value="<%= i %>">
				<button href="search" type="submit" class="form-control botones-confirmar">Eliminar los productos agotados del carro </button>
			</form>
			<a href="carro" class="volver"><button type="button" class="form-control botones-confirmar">Volver al carro sin hacer nada </button></a>
		</div>
	</body>
</html>
