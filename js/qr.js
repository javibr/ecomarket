$(document).ready(function() {
	$('#carro-qr').qrcode({
		render: 'canvas',
    	size: 150,
    	color: '#26A65B',
    	text: $('#compra').text()		
	});
});

