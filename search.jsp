<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='java.util.*' %>
<%@ page import="Modelo.*" %>
<html>
	<head>
		<title>Search</title>
		<%@ include file='head.jsp' %>
		<script src="js/jquery.easy-autocomplete.min.js"></script> 
		<link rel="stylesheet" href="css/easy-autocomplete.min.css">
		<script src="js/search.js"></script>	
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div class="container">
	        <div class="col-sm-12">
	            <form method = "POST" class="form-horizontal sub-margin" action = "search">
	                <div class="form-group has-feedback">
	            		<input id="provider-json" type="text" class="form-control" placeholder="Producto" name="Producto">
	              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
	            	</div>
	            </form>
	        </div>
		</div>

		<div id="mi-carousel" class="carousel slide" data-ride="carousel" data-interval="3000">
		  <!-- Indicators -->
		  <ol class="carousel-indicators">
		    <li data-target="#mi-carousel" data-slide-to="0" class="active"></li>
		    <li data-target="#mi-carousel" data-slide-to="1"></li>
		    <li data-target="#mi-carousel" data-slide-to="2"></li>
		  </ol>
		 
		  <!-- Wrapper for slides -->
		  <div class="carousel-inner">
		    <div class="item active" align="center">
		      <img src="imagenes/Carrousel-1.jpg" alt="Frutas">
		      <div class="carousel-caption">
		      	<h3>Saludables</h3>
		      </div>
		    </div>
		    <div class="item" align="center">
		      <img src="imagenes/Carrousel-2.jpg" alt="Pan">
		      <div class="carousel-caption">
		      	<h3>Comprometidos</h3>
		      </div>
		    </div>
		    <div class="item" align="center">
		      <img src="imagenes/Carrousel-3.jpg" alt="Pescado">
		      <div class="carousel-caption">
		      	<h3>Jóvenes</h3>
		      </div>
		    </div>
		  </div>
		 
		  <!-- Controls -->
		  <a class="left carousel-control" href="#mi-carousel" role="button" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left"></span>
		  </a>
		  <a class="right carousel-control" href="#mi-carousel" role="button" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right"></span>
		  </a>
		</div> <!-- Carousel -->
	</body>
</html>