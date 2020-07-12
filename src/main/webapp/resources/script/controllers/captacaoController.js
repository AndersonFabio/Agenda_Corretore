var captacaoCtrl = angular.module('captacaoCtrl', []);
appAgenda.controller(
				'captacaoCtrl',
				[
						'$scope',
						'$http',
						'$routeParams',
						'$location','usuarioService','$rootScope',
						function($scope, $http, $routeParams, $location, usuarioService,$rootScope) {
							$scope.sistema = 'CapiteWeb';
							$scope.captacao = {};
							$scope.captacao.situacao = "A Pagar";
							$scope.captacao.titulo1 = "";
							$scope.captacao.anuncio1 = "";
							$scope.captacaos = [];
							$scope.index = true;
							$scope.empresa = {};
							$scope.login = usuarioService.popularLogin();
							$scope.captacao.login = $scope.login;
							$scope.parametro = usuarioService.popularParametro();
							
							$scope.alterar = function(captacao) {
								$scope.captacao = captacao;
								$scope.captacao.login = $scope.login;
								$scope.index = false;
							}

							$scope.novo = function() {
								$scope.captacao = {};
								$scope.captacao.situacao = "Disponivel";
								$scope.captacao.login = $scope.login;
								$scope.captacao.idEmpresa = $scope.empresa.id;
								$scope.index = false;
							}

							$scope.salvar = function(captacao) {
								captacao.login = $scope.login;
								if($scope.login.cargo != "Imobiliaria") {
									captacao.idCorretor = $scope.corretor.id;
								}
								captacao.idEmpresa = $scope.empresa.id;
								setTimeout(function() {
									$rootScope.isVisible.loading = true;
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "captacao/salvar",
											method : "POST",
											contentType : "application/json",
											data : captacao
										})
										.success(
											function(data) {
												if($scope.login.cargo == "Imobiliaria") {
													if($scope.vencido($scope.empresa.vencimentoLed)) {
														alert("Favor renovar sua contratação de led, para isso acesse sua conta.");
													} else {
														$scope.pagarCaptacao();
													}
												} else {
													if($scope.vencido($scope.corretor.vencimentoLed)) {
														alert("Favor renovar sua contratação de led, para isso acesse sua conta.");
													} else {
														$scope.pagarCaptacao();
													}
												}
												$rootScope.isVisible.loading = false;
												$scope.index = true;
												$scope.pesquisar();
											})
										.error(
											function(erro) {
												$rootScope.isVisible.loading = false;
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							

							$scope.carregar = function() {
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "captacao/get",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												if(data != null) {
													$scope.captacao = data;
												}
											})
										.error(
											function(erro) {
												$rootScope.isVisible.loading = false;
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.carregarCorretor = function() {
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/get",
											method : "GET",
											contentType : "application/json",
											params : {"id": $scope.parametro.login.idCorretor}
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.corretor = data;
											})
										.error(
											function(erro) {
												$rootScope.isVisible.loading = false;
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.pagarCaptacao = function() {
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "checkout/pagarCaptacao",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												window.location.href = data.url;
											})
										.error(
											function(erro) {
												$rootScope.isVisible.loading = false;
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.carregarEmpresa = function() {
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "empresa/get",
											method : "GET",
											contentType : "application/json",
											params : {"id": $scope.parametro.login.idEmpresa}
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.empresa = data;
											})
										.error(
											function(erro) {
												$rootScope.isVisible.loading = false;
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};

							$scope.cancelar = function() {
								$scope.index = true;
								$scope.captacao = {};
								$scope.carregar();
							};
							
							$scope.vencido = function(dataVencimento) {
								$scope.past = new Date();
								$scope.now = new Date(); // Data de hoje
								$scope.diff = 0;
								$scope.days = 0;
								if(dataVencimento != undefined) {
									$scope.data = dataVencimento.substring(6,10)+'-'+dataVencimento.substring(3,5)+'-'+dataVencimento.substring(0,2);
									$scope.past = new Date($scope.data); // Outra data no passado
									$scope.diff = Math.abs($scope.now.getTime() - $scope.past.getTime()); // Subtrai uma data pela outra
									$scope.days = Math.ceil($scope.diff / (1000 * 60 * 60 * 24));
								} else {
									$scope.days = -1;
								} 
									
								if($scope.days < 0) {
									return true;
								} else {
									return false;
								}
							};
							
							$scope.carregar();
							$scope.carregarEmpresa();
							if($scope.login.cargo != "Imobiliaria") {
								$scope.carregarCorretor();
							}
					} ]);
