var homeCtrl = angular.module('homeCtrl', []);
app.controller('homeCtrl', [ '$scope', '$location','$http','$rootScope','usuarioServiceApp',
    function($scope, $location, $http, $rootScope,usuarioServiceApp ) {
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
			
			$scope.demo = function() {
				$scope.login = {};
				$scope.login.email = "contato@capiteweb.com.br";
				$scope.login.senha = "demo";
				$scope.login.cargo = "Imobiliaria";
				$scope.logar($scope.login);
			}
			
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
			
			$scope.logar = function (login) {
				$rootScope.isVisible.loading = true;
				setTimeout(function() {
					$http({
						url: URL+"login/validar",
						method: "POST",
						contentType: "application/json",
						data : login
					}).success(function (data) {
						$rootScope.isVisible.loading = false;
						if(data.acesso == 'S') {
							usuarioServiceApp.salvarLogin(data);
							window.location.href = CONTEXTO+"agenda.html";
							
						} else {
							alert("Email ou Senha Incorreta.");
						}
						
					}).error( function (erro) {
						$rootScope.isVisible.loading = false;
						alert("ERRO no envio dos dados ! "+erro == undefined ? "" : erro);
					})},100);
		    }; 
}]
);

		




