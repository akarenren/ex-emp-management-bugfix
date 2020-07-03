$(function() {
	$("#searchName").on("keyup", function() {
		$.ajax({
			url: "/employee/autoComplete",
			dataType: "json",
			data: {
				inputText: $("#searchName").val()
			},
			async: true
		}).done(function(data) {

			$("#searchName").autocomplete({
	            source : data,
	            autoFocus: true,
	            delay: 500,
	            minLength: 1
	        });

		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("XMLHttpRequest = " + XMLHttpRequest);
			conosle.log("textStatus = " + textStatus);
			console.log("errorThrown = " + errorThrown);
		})	
	})
})