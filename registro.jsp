<%@ page language='java' contentType='text/html;charset=utf-8' isErrorPage="true"%>
<html>
	<head>
		<title>Registro</title>
		<%@ include file='head.jsp' %>
		<script type="text/javascript" src="js/registro.js"></script>
		<link href="css/jquery.cssemoticons.css" media="screen" rel="stylesheet" type="text/css" />
		<script src="js/jquery.js" type="text/javascript"></script>
		<script src="js/jquery.cssemoticons.js" type="text/javascript"></script>
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div class="container login-margin">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login sub-margin">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<p id="vacio"></p>
								<form id="reg-form" action="registro" method="post">
									<div class="form-group">
										<input type="email" name="email" id="username"class="form-control" placeholder="Email" value="">
									</div>
									<div class="form-group">
										<input type="text" name="name" id="name"class="form-control" placeholder="Nombre" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password"class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<input type="password" name="password2" id="password2"class="form-control" placeholder="Repite password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-12">
												<input type="submit" name="reg-submit" id="reg-submit" class="form-control btn btn-login" value="Sign in">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>