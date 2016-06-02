
$(document).ready(function() {
	$('#reg-submit').click(function(event){
		event.preventDefault();
		var error = false;
		if (!$('#username').val()) {
			$("#vacio").text("Campo de email vacío :-(");
			error = true;
		}
		if (error == false && (!$('#name').val())) {
			$("#vacio").text("Campo de nombre vacío :-(");
			error = true;
		}
		if (error == false && (!$('#password').val() || !$('#password2').val())) {
			$("#vacio").text("Campo de password vacío :-(");
			error = true;
		}

		if (error == false && ($('#password').val() != $('#password2').val())) {
			$("#vacio").text("Las dos contraseñas no coinciden :-(");
			error = true;
		}

		if (error == false && ($('#password').val().length < 4 )){
			$("#vacio").text("La contraseña tiene que tener al menos 4 caracteres :S");
			error = true;
		}

		if (error == false && (!$('#password').val())) {
			$("#vacio").text("Campo de password vacío :-(");
			error = true;
		}

		if (error == false && (!$('#username').val() && !$('#password').val())){
			$("#vacio").text("Ambos campos vacíos :S");
		}

		

		var regex = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;

	    // Se utiliza la funcion test() nativa de JavaScript
	    if ((regex.test($('#username').val().trim()))) {
	    } else {
	        $("#vacio").text("Revisa el correo ;-)");
	        error = true;
   		}

		if (!error) {
			$('#reg-form').submit();
		}
		$('#vacio').emoticonize();
	});
	
});

