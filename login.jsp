<%@ page language='java' contentType='text/html;charset=utf-8' isErrorPage="true"%>
<html>
	<head>
		<title>Login</title>
		<%@ include file='head.jsp' %>
		<script type="text/javascript" src="js/login.js"></script>
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
								<form id="login-form" action="login" method="post">
									<div class="form-group">
										<input type="email" name="email" id="username"class="form-control" placeholder="Email" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password"class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-12">
												<input type="submit" name="login-submit" id="login-submit" class="form-control btn btn-login" value="Log In">
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