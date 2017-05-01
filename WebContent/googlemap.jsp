<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>

	<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyAVUg13cHoKaJF04IfS67n5TVCBXK8v65s'></script>
	<div style='overflow: hidden; height: 400px; width: 100%;'>
		<div id='gmap_canvas' style='height: 400px; width: 100%;'></div>
		<div>
			<small><a href="http://www.embedgooglemaps.com/en/">Generate
					your map here, quick and easy! Give your customers directions Get
					found</a></small>
		</div>
	<!-- 	<div>
			<small><a
				href="https://top10geeks.com/top-10-best-dslr-cameras-beginners-2016/">Best
					DSLR cameras of 2016</a></small>
		</div> -->
	</div>
	<script type='text/javascript'>
		function init_map() {
			var myOptions = {
				zoom : 4,
				center : new google.maps.LatLng(37.09024, -95.71289100000001),
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			map = new google.maps.Map(document.getElementById('gmap_canvas'),
					myOptions);
			marker = new google.maps.Marker({
				map : map,
				position : new google.maps.LatLng(37.09024, -95.71289100000001)
			});
			infowindow = new google.maps.InfoWindow({
				content : '<strong>United States</strong><br>United States<br>'
			});
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map, marker);
			});
			google.maps.event.addListener(map, 'click', function(event) {
				/*console.log('Lat: ' + event.latLng.lat() + ' and Longitude is: '
						+ event.latLng.lng());*/
						//alert(event.latLng.lat());
				$.post("TwitterServlet", {
					"latitude" : event.latLng.lat(),
					"longitude": event.latLng.lng()
				}).done(successFunction).fail(ajaxFailure);
			});
			infowindow.open(map, marker);
		}
		google.maps.event.addDomListener(window, 'load', init_map);
		
		function successFunction(postData)
		{
			//alert("success");
			$('#locationcontent').empty();
			var genre = postData[0].genre;
			$.each(postData, function(index, value) {
				//alert(value.text);
				var img = $('<img>', {
					"src" : value.imageURL,
					"alt" : "Image" + index,
					"width" : "300px",
					"height" : "200px",
					"margin": "20px 20px 20px 2px",
					"background-color":"blue",
					"float": "left"
					
				});
				//img.addClass("imgTrend");
				var text = $('<p>', {
					"text" : value.text,
					"css" : {
						"padding" : "10px",
						"width" : "500px",
						"float" : "right",
						"color" : "red",
						"font-weight":"bold",
						"font-size":"20px",
						//"text-align":"center",
						//"overflow":"hidden"
						
					}
				});
				//text.addClass("textTrend")
				var div = $('<div>', {
					"css" : {
					//"text-align" : "center",
					//"width" : "100%",
					"height" : "200px",
					"background-color" : "darkslateblue",
					"border" : "3px solid white",
					"overflow": "hidden",
					"margin" : "20px"
				
					
				}
				});
				//div.addClass("content2");
				
				div.append(img);
				div.append(text);
				$('#locationcontent').append(div);
				
			});
		}
		function ajaxFailure(){
			alert("Failed");
		}
	
		
	</script>
	<div id="locationcontent"></div>
</body>
</html>
