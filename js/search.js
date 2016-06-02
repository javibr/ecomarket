$(document).ready(function () {

	$('#mi-carousel').carousel();

	$.ajax({
	  dataType: "json",
	  url: "json/productos.json",
	  success: function(data) {
	  	//console.log(data);
	  	$("#provider-json").easyAutocomplete({
	  		getValue: "name",
	  		data: data,
	  		list: {
	  			match: {
	  				enabled: true
	  			}
	  		}
	  	});
	  },
	  error: function(error) {
	  	console.log("Ha ocurrido un error: " + error);
	  }
	});
});