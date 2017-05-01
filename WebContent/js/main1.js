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
	
	$('#trends a').click(function() {
		//alert($(this).parent().get(0).id);
		$(".active").find(".activechild").removeClass("activechild");
		$(".nav").find(".active").removeClass("active");
		// $(this).parent().addClass("active");
		// $(this).addClass("activechild");
		$.get("TwitterServlet", {
			"genre" : $(this).parent().get(0).id
		}).done(successFunction).fail(ajaxFailure);
	});

});

var successFun = function(retData) {
	$('#content').empty();
	var genre = retData[0].genre; //FIrst JSon Object genre
	$('#' + genre).addClass("active");
	$('#' + genre + "child").addClass("activechild");
	$.each(retData, function(index, value) {
		var img = $('<img>', {
			"src" : value.imageURL,
			"alt" : "Image" + index,
			/*"width" : "300px",
			"height" : "200px",
			"margin": "20px",
			"background-color":"blue"*/
		});
		img.addClass("imgage1");
		var text = $('<p>', {
			"text" : value.text,
		/*	"css" : {
				"width" : "300px",
				"float" : "left",
				"color" : "red",
				"overflow" : "hidden",
				"font-weight":"bold",
				"font-size":"10px"
			}*/
		});
		text.addClass("text");
		var div = $('<div>', {
			/*"css" : {
				"text-align" : "center",
				"width" : "300px",
				"float" : "left",
				"height" : "250px",
			"background-color" : "blue",
				"border" : "3px solid white",
				"overflow": "hidden",
				"margin" : "20px"
			}*/
		});
		div.addClass("content1");
		
		/*var div2=$('<div>').addClass("test");*/
		// div.append(img);
		// div.append(text);
		var a = $("<a />", {
			href : value.link,
			text : value.text,
			/*css : {
				"color" : "white",
				"background-color" : "blue",
				"overflow" : "hidden"
			}*/
		});
		a.addClass("aLink");
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
		$('#content').append(div);
	});
};
var successFunction=function(retData){
	$('#content').empty();
	var genre = retData[0].genre; //FIrst JSon Object genre
	$('#' + genre).addClass("active");
	$('#' + genre + "child").addClass("activechild");
	$.each(retData, function(index, value) {
		var img = $('<img>', {
			"src" : value.imageURL,
			"alt" : "Image" + index,
			
			/*"width" : "300px",
			"height" : "200px",
			"margin": "20px 20px 20px 2px",
			"background-color":"blue",
			"float":"left"*/
		});
		img.addClass("imgTrend");
		var text = $('<p>', {
			"text" : value.text,
		/*	"css" : {
				"padding" : "10px",
				"width" : "500px",
				"float" : "right",
				"color" : "red",
				"font-weight":"bold",
				"font-size":"20px"
			}*/
		});
		text.addClass("textTrend")
		var div = $('<div>', {
			/*"css" : {
				"text-align" : "center",
				"width" : "800px",
				"height" : "200px",
				"background-color" : "orange",
				"border" : "3px solid white",
				"overflow": "hidden",
				"margin" : "20px"
			}*/
		});
		div.addClass("content2");
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
		/*var a = $("<a />", {
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
		});*/
		/*div.append(aimage);
		aimage.append(img);
		div.append(a);
		div.append(text);
		$('#content').append(div);*/
		div.append(img);
		div.append(text);
		$('#content').append(div);
		
	});
}

var ajaxFailure = function(xhr, status, exception) {
	alert("Failed");
};
