 <%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='java.util.*' %>
<%@ page import="Modelo.*" %>
<html>
	<head>
		<title>Carro</title>
		<%@ include file='head.jsp' %>
		<script src='js/jquery.qrcode-0.12.0.js'/></script>
		<script src='js/jquery.qrcode-0.12.0.min.js'/></script>
		<script src="js/qr.js"></script>
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<!-- Por si queremos quitar los decimales -->
		<%! public static String fmt(float d) {
			    if(d == (int) d)
			        return String.format("%d",(int)d);
			    else
			        return String.format("%s",d);
			}
		%>
		<div class="container">
			<div id="carro-qr" align="center">
				<% List<Carro> carros = (List<Carro>)request.getAttribute("carros");
					if(!carros.isEmpty()) { %>
				<table class="table table-striped">
					<thead>
			        <tr>
			          <th>Producto</th>
			          <th>Unidades</th>
			          <th>Precio €</th>
			        </tr>
			      </thead>
			      <tbody>
			      	<%	float total = 0; 
			      		String compras = "";
			      	for(Carro carro: carros){ 
			      		compras += carro.getProducto() + " "; %>
			        <tr>
			          <td><%= carro.getProducto() %></td>
			          <td><%= carro.getUnidades() %></td>
			          <td><%= carro.getPrecio()*carro.getUnidades() %></td>
			          <% total += carro.getPrecio()*carro.getUnidades(); %>
			        </tr>
			        <% } %>
			        <tr id="total">
			          <td>Total:</td>
			          <td></td>
			          <td id="totalval"><%= total %></td>
			        </tr>
			      </tbody>
				</table>
				<div class="col-sm-6 centrar"><a href="vaciar"><div class="col-sm-offset-3 col-sm-6 iconos-carro">Vaciar carro <i class="fa fa-trash"></i></div></a></div>
				<div class="col-sm-6 centrar"><a href="comprar"><div class="col-sm-offset-3 col-sm-6 iconos-carro">Comprar <i class="fa fa-credit-card"></i></div></a></div>
				
				<div id="compra" class="hidden"><%= compras %></div>
				<%} else {%>
					<h2 id="compra">Aún no has añadido elementos al carro...</h2>
				<% } %>
			</div>
		</div>
	</body>
</html>