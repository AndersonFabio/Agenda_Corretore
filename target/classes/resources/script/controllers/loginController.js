var loginCtrl = angular.module('loginCtrl', []);
app.controller('loginCtrl', [ '$scope', '$location','$http','$rootScope','usuarioServiceApp',
    function($scope, $location, $http, $rootScope,usuarioService ) {
			$scope.sistema = 'CapiteWeb';

			$scope.login = {};
			$scope.login.cargo = "Corretor/Supervisor";
			$scope.login.nome = "";
			
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
							usuarioService.salvarLogin(data);
							window.location.href = CONTEXTO+"agenda.html";
							
						} else {
							alert("Email ou Senha Incorreta.");
						}
						
					}).error( function (erro) {
						$rootScope.isVisible.loading = false;
						alert("ERRO no envio dos dados ! "+erro == undefined ? "" : erro);
					})},100);
		    }; 
		    
		    $scope.testaLogin = function (login) {
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
							return true;
						} else {
							login();
						}
					}).error( function (erro) {
						alert("ERRO no envio dos dados ! "+erro == undefined ? "" : erro);
					})},100);
		    }; 
		    
		    $scope.cancelar = function() {
		    	$rootScope.isVisible.loading = true;
		    	$scope.login = {};
		    	$scope.login.cargo = "Corretor/Supervisor";
		    	$rootScope.isVisible.loading = false;
		    }
}]
);
	
