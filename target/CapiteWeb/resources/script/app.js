$.mobile.linkBindingEnabled = false;
$.mobile.hashListeningEnabled = false;
//var app = angular.module('ngdemoApp',['ngRoute', 'ngResource','ui.mask']);
//var app;

sessionStorage.SessionName = "CapiteWebEmail";
sessionStorage.SessionName = "CapiteWebSenha";
sessionStorage.SessionName = "CapiteWebCargo";
sessionStorage.SessionName = "CapiteWebNome";
sessionStorage.SessionName = "CapiteWebIdEmpresa";
sessionStorage.SessionName = "CapiteWebIdCorretor";

app.run(function($rootScope, $timeout) {
    $rootScope.isVisible = {
            loading: false
        };
/*        $rootScope.$on("$stateChangeStart", function() {
            $rootScope.isVisible.loading = true;
            //alert($rootScope.isVisible.loading)
        });
        $rootScope.$on("$viewContentLoaded", function () {
            $timeout(function () {
                $rootScope.isVisible.loading = false;
                //alert($rootScope.isVisible.loading)
            }, 2000);
        });
*/    }).directive('fullscreenDialog', function () {
	  return {
			controller: 'fsDialogController',
			link: fsDialogLinker,
			restrict : 'E',
			replace: true,
			transclude: true,
			template : '<div class="dialog-container"><ng-transclude></ng-transclude></div>'
		}
		function fsDialogLinker(scope, element, attribute) {
			scope.$on('$destroy', function () {
			    var bodyElement = document.getElementById("dialog");
		    	bodyElement.classList.remove('noscroll');
		  });
		}
	}).config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/home', { templateUrl: 'views/home.html', controller: 'homeCtrl'})
		.when('/plano', {templateUrl: 'views/plano.html', controller: 'planoCtrl'})
		.when('/led', {templateUrl: 'views/led.html', controller: 'ledCtrl'})
		.when('/contato', {templateUrl: 'views/contato.html', controller: 'contatoCtrl'})
		.when('/login', {templateUrl: 'views/login.html', controller: 'loginCtrl'})
		.when('/cadastrar', {templateUrl: 'views/cadastro.html', controller: 'cadastrarCtrl'})
		.when('/template1', {templateUrl: 'views/template1.html', controller: 'template1Controller'})
		.when('/template2', {templateUrl: 'views/template2.html', controller: 'template2Controller'})
      
		.otherwise({redirectTo: '/', controller: 'homeCtrl'});
	}]).controller('fsDialogController', function ($scope) {
		$scope.isPanelVisible = false;
		var bodyElement = document.getElementById("dialog");
		
		$scope.showDialog = function () {
			$scope.isPanelVisible = true;
			bodyElement.classList.add('noscroll');
		};
		$scope.hideDialog = function () {
			$scope.isPanelVisible = false;
			bodyElement.classList.remove('noscroll');
		};
	});
  



app.directive('jqm', function($timeout) {
	  return {
	    link: function(scope, elm, attr) {
	        $timeout(function(){
	            elm.trigger('create');
	        });
	    }
	  };
	});
