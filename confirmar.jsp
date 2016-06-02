<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='java.util.*' %>
<%@ page import='java.text.SimpleDateFormat' %>
<%@ page import='java.text.DateFormat' %>
<%@ page import="Modelo.*" %>

<html>
	<head>
		<title>Compra</title>
		<%@ include file='head.jsp' %>
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<%! public static String fmt(float d) {
			    if(d == (int) d)
			        return String.format("%d",(int)d);
			    else
			        return String.format("%s",d);
			}
		%>
		<div class="container">
			<div class="sub-margin"></div>
			<h2>Su compra ha sido realizada con éxito, ¡Gracias por elegirnos!</h2>
			<!-- Trigger the modal with a button -->
			<form action="search"><button href="search" type="submit" class="form-control botones-confirmar">Volver a inicio </button></form>
			<button type="button" class="form-control botones-confirmar" data-toggle="modal" data-target="#myModal">Ver resumen de compra </button>

			<!-- Modal -->
			<div id="myModal" class="modal fade" role="dialog">
			  <div class="modal-dialog">

			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">EcoMarket, <%= ((Cliente)session.getAttribute("cliente")).getNombre() %></h4>
			      </div>
			      <div class="modal-body">
					<table class="table table-striped">
						<thead>
				        <tr>
				          <th>Producto</th>
				          <th>Unidades</th>
				          <th>Precio €</th>
				        </tr>
				      </thead>
				      <%	List<Resumen> resumenes = (List<Resumen>)request.getAttribute("resumen"); 
			      		Resumen total = new Resumen();
						for(Resumen resumen:resumenes) { %>
							<% total = resumen; %>
				      <tbody>
				        <tr>
				          <td><%= resumen.getNombreProducto() %></td>
				          <td><%= resumen.getUnidades() %></td>
				          <td><%= resumen.getPrecio()*resumen.getUnidades() %></td>
				        </tr>
				        <% } %>
				        <tr id="total">
				          <td>Total:</td>
				          <td></td>
				          <td id="totalval"><%= total.getImporte() %></td>
				        </tr>
				      </tbody>
					</table>

			        <% 

					SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
					Calendar c = Calendar.getInstance();
					c.setTime(total.getFechaRecogida()); // Fecha de recogida
					String output = formateador.format(c.getTime());

					%>
					<table class="table table-striped">
						<thead>
				        <tr>
				          <th>Lugar de recogida</th>
				          <th>Fecha de recogida</th>
				        </tr>
				      </thead>
				      <tbody>
				        <tr>
				          <td><%= total.getLugar() %></td>
				          <td><%= output %></td>
				        </tr>
				      </tbody>
					</table>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn botones-confirmar" data-dismiss="modal">Cerrar</button>
			      </div>
			    </div>
			    
			  </div>
			</div>
		</div>
	</body>
</html>
