(function() {
	
	var app = angular.module("app", ['ngRoute']);
	app.config(function($routeProvider) {
	    $routeProvider
	        .when('/home', {
	            templateUrl: 'home.jsp',
	            controller: 'HttpCtrl as app'
	        })
	        .when('/viewAuthors', {
	            templateUrl: 'viewAuthors.jsp',
	            controller: 'auth as app'
	        })
	        .when('/viewPublisher', {
	            templateUrl: 'viewPublisher.jsp',
	            controller: 'pub as app'
	        })
	        .when('/viewCountry', {
	            templateUrl: 'viewCountry.jsp',
	            controller: 'cont as app'
	        })
	        .when('/viewState', {
	            templateUrl: 'viewState.jsp',
	            controller: 'stat as app'
	        })
	        .when('/viewTrends', {
	            templateUrl: 'viewTrends.jsp',
	            controller: 'trend as app'
	        })
	        .when('/viewRecommendation', {
	            templateUrl: 'viewRecommendation.jsp',
	            controller: 'recom as app'
	        })
	        .otherwise({
	            redirectTo: '/home'
	        });
	});
	
	app.controller("auth", function($scope, $http) {
		var app = this;
		
		
		
		var response = $http.get('/BookAnalytics/rest/authors/');
		response.success(function(data) {
			$scope.authors = data;
			
		})
		response.error(function(data, status, headers, config) {
			alert("AJAX failed to get data, status=" + status);
		})
	});
	
	app.controller("pub", function($scope, $http) {
		var app = this;
		
		
		
		var response = $http.get('/BookAnalytics/rest/publ/');
		response.success(function(data) {
			$scope.publisher = data;
			
		})
		response.error(function(data, status, headers, config) {
			alert("AJAX failed to get data, status=" + status);
		})
	});
	
	
	
	app.controller("cont", function($scope, $http) {
		var app = this;		
		
		var response = $http.get('/BookAnalytics/rest/country/');
		response.success(function(data) {
			$scope.country = data;
			
		})
		response.error(function(data, status, headers, config) {
			alert("AJAX failed to get data, status=" + status);
		})
	});
	app.controller("stat", function($scope, $http) {
		var app = this;
		
		
		
		var response = $http.get('/BookAnalytics/rest/stat/');
		response.success(function(data) {
			$scope.state = data;
			
		})
		response.error(function(data, status, headers, config) {
			alert("AJAX failed to get data, status=" + status);
		})
	});
	
	
	app.controller("trend", function($scope, $http) {
		var app = this;
		
	});
	
	app.controller("recom", function($scope, $http) {
		var app = this;
		
		
		
		var response = $http.get('/BookAnalytics/rest/recommendation/');
		response.success(function(data) {
			$scope.recommend = data;
			
		})
		response.error(function(data, status, headers, config) {
			alert("AJAX failed to get data, status=" + status);
		})
	});
	
})();