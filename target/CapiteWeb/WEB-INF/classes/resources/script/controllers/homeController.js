var homeCtrl = angular.module('homeCtrl', []);
app.controller('homeCtrl', [ '$scope', '$location','$http','$rootScope',
    function($scope, $location, $http, $rootScope ) {
			$scope.sistema = 'CapiteWeb';

			$location.path('/home');
			
			$scope.agenda = function() {
				$location.path('/agenda');
			}
			
			$scope.home = function() {
				$location.path('/home');
			}
			
			$scope.led = function() {
				$location.path('/led');
			};
			
			$scope.template1 = function() {
				$rootScope.isVisible.loading = true;
				$location.path('/template1');
			}
			$scope.template2 = function() {
				$rootScope.isVisible.loading = true;
				$location.path('/template2');
			}
			
			$scope.plano = function() {
				$location.path('/plano');
			};
			
			$scope.contato = function() {
				$location.path('/contato');
			};
			
			$scope.login = function() {
				$location.path('/login');
			};
			

			
			$scope.cadastrar = function() {
				$location.path('/cadastrar');
			};
}]
);

		




