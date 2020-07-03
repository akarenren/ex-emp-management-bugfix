$(function() {
	console.log("#");
	$("#zipCodeButton").on("click", function() {
		$.ajax({
			url: "http://zipcoda.net/api",
			dataType: "jsonp",
			data: {
				zipcode: $("#zipCode").val()
			},
			async: true
			
		}).done(function(data) {
			$("#address").val(data.items[0].address);
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("XMLHttpRequest = " + XMLHttpRequest);
			conosle.log("textStatus = " + textStatus);
			console.log("errorThrown = " + errorThrown);
		})	
		
		
	})
})