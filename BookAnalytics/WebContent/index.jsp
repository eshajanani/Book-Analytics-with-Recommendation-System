<html >
	<head>
		<title>Book Analytics</title>
		<link href="include/styles.css" rel="stylesheet">
		<!-- Use Bootstrap -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.min.js"></script>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28//angular-route.min.js"></script>
		<script type="text/javascript" src="include/app.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	
	<body >
  	<div class="container">
		<div class="header">
			<h1 class="custom">Book Analytics - Semantic Web</h1>
		</div>

		<div class="leftPanel">
			<div class="LeftPanelHeader">Menu</div>
			<table class="side">
			  <tr >
			  <td class="side"><a href="#/Home">Home</a></td>
			  </tr>
			  <tr >
			    <td class="side"><a href="#/viewAuthors">Popular Author </a></td>
			  </tr>
			   </tr>
			  <tr >
			    <td class="side"><a href="#/viewPublisher">Popular Publisher </a></td>
			  </tr>
			   </tr>
			  <tr >
			    <td class="side"><a href="#/viewCountry"> Country Analysis </a></td>
			  </tr>
			   </tr>
			  <tr >
			    <td class="side"><a href="#/viewState">State Analysis </a></td>
			  </tr>
			   </tr>
			  <tr >
			    <td class="side"><a href="#/viewTrends">Trend Analysis </a></td>
			  </tr>
			  <tr >
			    <td class="side"><a href="#/viewRecommendation">Recommendation System</a></td>
			  </tr>
			</table>
		</div>

		
		
		<div class="MainBody" ng-app="app">
		<ng-view></ng-view>   
		</div>

		<div class="footer">Book Data Analytics - Semantic Web</div>
	</div>
	</body>
</html>
