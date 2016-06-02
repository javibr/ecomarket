<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='java.util.*' %>
<%@ page import='java.text.SimpleDateFormat' %>
<%@ page import='java.text.DateFormat' %>
<%@ page import="Modelo.*" %>

<html>
	<head>
		<title>Compra</title>
		<%@ include file='head.jsp' %>
		<link href="https://api.mapbox.com/mapbox.js/v2.4.0/mapbox.css" rel="stylesheet" />
		<script src="https://api.mapbox.com/mapbox.js/v2.4.0/mapbox.js"></script>
		<script src="js/comprar.js"></script>
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div class="container">
		<form action="confirmar" method="POST" class="sub-margin">
			Lugares de recogida: 
			<select class="form-control" name="lugares" label="Lugar de recogida">
				<%	List<Recogida> recogidas = (List<Recogida>)request.getAttribute("recogidas"); 
				int i = 1;
				for(Recogida recogida: recogidas) { %>
				<option value="<%= i %>"><%= recogida.getLugar() %></option>				
				<% i++;
				} %>
			</select>
			<% 

			SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Hoy
			c.add(Calendar.DATE, 1); // Mañana
			String output = formateador.format(c.getTime());

			%>
			<div class="margen"></div>
			Fecha de recogida: 
			<select class="form-control" name="fecha">
				<option value="1"><%= output %></option>
				<%
				c.add(Calendar.DATE, 1);
				output = formateador.format(c.getTime());
				%>
				<option value="2"><%= output %></option>
				<%
				c.add(Calendar.DATE, 1);
				output = formateador.format(c.getTime());
				%>
				<option value="3"><%= output %></option>
			</select>
			<div class="margen"></div>
			<input class="form-control botones-confirmar" type="submit" value="Confirmar">
		</form>
			<div class="map-container">
				<div id="map"></div>
			</div>
		</div>
	</body>
</html>