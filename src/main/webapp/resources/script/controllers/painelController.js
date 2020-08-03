appAgenda.controller('painelCtrl', [
		'$scope',
		'$http',
		'$routeParams',
		'$location',
		'usuarioService',
		'$rootScope',
		function($scope, $http, $routeParams, $location, usuarioService,
				$rootScope) {

			$scope.login = usuarioService.popularLogin();
			$scope.parametro = usuarioService.popularParametro();
			if($scope.login.cargo == 'Imobiliaria') {
				$scope.corretor = false;
			} else {
				$scope.corretor = true;
			}
			$scope.cargo = $scope.login.cargo;
			$scope.imprimirResumoImobiliaria = function(idImobiliaria) {
				$scope.url = URL+"relatorio/resumoPorImobiliaria?idImobiliaria="+idImobiliaria;
				window.open($scope.url,'_blank');
				
			}
			
			$scope.imprimirGrafico = function(idImobiliaria) {
				$scope.url = URL+"relatorio/grafico?idEmpresa="+$scope.login.idEmpresa;
				window.open($scope.url,'_blank');
				
			}
			
			
			
		} ]);
