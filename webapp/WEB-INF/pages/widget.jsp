<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String userEmailId = (String)session.getAttribute("userEmailId"); 
String userName = (String)session.getAttribute("userName"); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://www.agilecarousel.com/agile_carousel/agile_carousel.a1.1.min.js"></script>
<script src="/js/bootstrap.js"></script>

<link rel="stylesheet" href="/css/bootstrap.css" />
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet" href="/css/agile_carousel.css" type='text/css'>

<style> 
.panel-body{
    width: 38em; 
    word-wrap: break-word;
}
</style>

</head>
<body>
	  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" style="color:#ffffff" href="#">recommended4U</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right"> 
            <!--<a href="http://localhost:8080/recommended4U/recommendations/search" style="color:#ffffff;">Go To Search</a>-->
			<input type="button" id="search-submit" onclick="performSearch()" class="btn btn-primary form-control" value="Click here for Search" />
			<span style="margin-left:20px;color:#ffffff">Welcome <%= userName%></span>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container" style="margin-top:20px;">
        <div class="slideshow" id="multiple_slides_visible"></div>
      </div>
    </div>

    <div class="container">
      <footer>
        <p align="center">reccomendations.com 2015 Company, Inc.</p>
      </footer>
    </div> 
	<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Product Info</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

</body>


<script>
function performSearch() {
	window.location.href="http://localhost:8080/recommended4U/recommendations/search";
}

function fetchReviews(asin) {
	var html;
	//$("#loadDiv").show();
	$.ajax({
    type: "GET",
    url: "http://localhost:8080/recommended4U/rest/recommendations/item/"+asin,
    contentType: "application/json; charset=utf-8",
    accept : "text/html",
    async: false,
    timeout: 120000,
    //dataType: "json",
    //data: queryString,
    success: function(data1) {
    	data = JSON.parse(data1);
		//$("#loadDiv").hide();
    },
    error: function (xhr, textStatus, errorThrown) {
        $("#error").html(xhr.responseText);
    }
	});
	if (data) {
		html =  '<div class="container">' +         
      '<img src="'+data.imURL+'" class="img-responsive" alt="Responsive image">'+
	  '<div class="panel-body"><h3>'+data.title+'</h3></div>'+
	  '<div class="panel-body">Actual Rating : '+data.actualScore+'</div>'+
	  '<div class="panel-body">Credibility Rating : '+data.finalScore+'</div>'+
	  '<div class="panel-body">Price : '+data.price+'</div>'+
	  '<div class="panel-body">Description : '+data.description+'</div>'+
	  '<div class="panel-body">Top Review : '+data.reviews+'</div>'+
'</div>';
	}
	$(".modal-body").html(html);
	$('#myModal').modal({
		keyboard: false
	});
}

var userEmailId = '<%= userEmailId%>';

var data;

$(document).ready(function(){
	//$("#loadDiv").show();
	$.ajax({
    type: "POST",
    url: "http://localhost:8080/recommended4U/rest/recommendations",
    contentType: "application/json; charset=utf-8",
    accept : "text/html",
    async: false,
	timeout: 120000,
    //dataType: "json",
    data: userEmailId,
    success: function(data1) {
    	data = JSON.parse(data1);
		//$("#loadDiv").hide();
    },
    error: function (xhr, textStatus, errorThrown) {
        $("#error").html(xhr.responseText);
    }
});
	
	$("#multiple_slides_visible").agile_carousel({
		carousel_data: data,
		carousel_outer_height:240,
		carousel_height: 200,
		slide_height: 200,
		carousel_outer_width: 480,
		slide_width: 160,
		number_slides_visible: 7,
		transition_time: 330,
		control_set_1: "previous_button,next_button",
		control_set_2: "group_numbered_buttons",
		persistent_content: "<p class='persistent_content'>Recommended For You</p>"       
	});
});
</script>
</html>
