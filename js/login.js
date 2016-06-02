$(document).ready(function() {
	$('#login-submit').click(function(event){
		event.preventDefault();
		var error = false;
		if (!$('#username').val()) {
			$("#vacio").text("Campo de email vacío :-(");
			error = true;
		}
		if (!$('#password').val()) {
			$("#vacio").text("Campo de password vacío :-(");
			error = true;
		}

		if (!$('#username').val() && !$('#password').val()){
			$("#vacio").text("Ambos campos vacíos :S");
		}

		var regex = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;

	    // Se utiliza la funcion test() nativa de JavaScript
	    if (regex.test($('#username').val().trim())) {
	    } else {
	        $("#vacio").text("Revisa el correo ;-)");
	        error = true;
   		}

		if (!error) {
			$('#login-form').submit();
		}
		$('#vacio').emoticonize();
	});
	
});

