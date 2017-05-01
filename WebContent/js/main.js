$(function() {
	// For starting case
	$.get("TwitterServlet", {}).done(successFun).fail(ajaxFailure);

	// When we switched tab
	$('.nav a').click(function() {
		$(".active").find(".activechild").removeClass("activechild");
		$(".nav").find(".active").removeClass("active");
		// $(this).parent().addClass("active");
		// $(this).addClass("activechild");
		$.get("TwitterServlet", {
			"genre" : $(this).parent().get(0).id
		}).done(successFun).fail(ajaxFailure);
	});
	
	/*$('#trends a').click(function() {
		//alert($(this).parent().get(0).id);
		$(".active").find(".activechild").removeClass("activechild");
		$(".nav").find(".active").removeClass("active");
		// $(this).parent().addClass("active");
		// $(this).addClass("activechild");
		$.get("TwitterServlet", {
			"trend" : $(this).parent().get(0).id
		}).done(successFunction).fail(ajaxFailure);
	});
*/
});

var successFun = function(retData) {
	alert(retData);
	$('#content').empty();
	var genre = retData[0].genre; //FIrst JSon Object genre
	$('#' + genre).addClass("active");
	$('#' + genre + "child").addClass("activechild");
	$.each(retData, function(index, value) {
		var img = $('<img>', {
			"src" : value.imageURL,
			"alt" : "Image" + index,
			"width" : "300px",
			"height" : "200px",
			"margin": "20px",
			"background-color":"blue"
		});
		var text = $('<p>', {
			"text" : value.text,
			"css" : {
				"width" : "300px",
				"float" : "left",
				"color" : "red",
				"overflow" : "hidden",
				"font-weight":"bold",
				"font-size":"10px"
			}
		});
		var div = $('<div>', {
			"css" : {
				"text-align" : "center",
				"width" : "300px",
				"float" : "left",
				"height" : "250px",
			"background-color" : "yellow",
				"border" : "1px solid white",
				"overflow": "hidden",
				"margin" : "20px"
			}
		});
		/*var div2 = $('<div>', {
			"css" : {
				"text-align" : "center",
				"width" : "300px",
				"float" : "left",
				"height" : "100px",
				"background-color" : "blue",
				"border" : "1px solid white",
				"overflow": "hidden",
				"bottom":"0"
			}
		});*/
		/*var div2=$('<div>').addClass("test");*/
		// div.append(img);
		// div.append(text);
		var a = $("<a />", {
			href : value.link,
			text : value.text,
			css : {
				"color" : "white",
				"background-color" : "blue",
				"overflow" : "hidden"
			}
		});
		var aimage = $("<a />", {
			href : value.link,
			
			css : {
				"color" : "white",
				"overflow" : "hidden"
			}
		});
		div.append(aimage);
		aimage.append(img);
		div.append(a);
		//div2.append(a);
		$('#content').append(div);
	});
	/*
	 * console.log(retData); $('#content').text(retData);
	 */
};
var successfunction=function(retdata){
	//console.log("RetData");
	alert("Success");
	//$('#content').empty();
	
}

var ajaxFailure = function(xhr, status, exception) {
	alert("Failed");
};
