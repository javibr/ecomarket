<%@ page language='java' contentType='text/html;charset=utf-8'%>
<html>
	<head>
		<title>Error</title>
		<link rel="stylesheet" href="css/error.css" type="text/css"/>
		<%@ include file='head.jsp' %>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<div class="container">
			<div class="sub-margin"></div>
			<h2>¡Error: <%= session.getAttribute("error") %>! Pulsa en la imagen para volver atrás</h2>
			<a href="javascript:history.back()"><img src="imagenes/error.png"></a>
		</div>
	</body>
</html>