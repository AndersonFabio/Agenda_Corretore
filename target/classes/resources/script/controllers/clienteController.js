var clienteCtrl = angular.module('clienteCtrl', []);
appAgenda.controller(
				'clienteCtrl',
				[
						'$scope',
						'$http',
						'$routeParams',
						'$location','usuarioService','$rootScope',
						function($scope, $http, $routeParams, $location, usuarioService,$rootScope) {
							
							$scope.login = {};
							$scope.login = usuarioService.popularLogin();
							$scope.parametro = usuarioService.popularParametro();
							
							$scope.sistema = 'CapiteWeb';
							$scope.empresa = {};
							$scope.cliente = {};
							$scope.cliente.situacao = "Selecione...";
							$scope.cliente.captacao = "Selecione...";
							$scope.clientes = [];
							$scope.index = true;
							$scope.cliente.login = $scope.login;
							
							$scope.corretores = [];
							$scope.supervisores = [];
							$scope.captadores = [];

							
							
							$scope.alterar = function(cliente) {
								$scope.cliente = cliente;
								$scope.cliente.login = $scope.login;
								$scope.index = false;
							}

							$scope.novo = function() {
								$scope.cliente = {};
								$scope.cliente.situacao = "Selecione...";
								$scope.cliente.captacao = "Selecione...";
								$scope.cliente.login = $scope.login;
								$scope.cliente.idEmpresa = $scope.empresa.id;
								$scope.index = false;
							}

							$scope.salvar = function(cliente) {
								setTimeout(function() {
									$rootScope.isVisible.loading = true;
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "cliente/salvar",
											method : "POST",
											contentType : "application/json",
											data : cliente
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.index = true;
												$scope.pesquisarPorNome();
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.pesquisarPorEmpresa = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								setTimeout(function() {
									$rootScope.isVisible.loading = true;
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "cliente/listPorEmpresa",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.clientes = data;
												
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.pesquisarPorNome = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "cliente/listPorNome",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.clientes = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};

							$scope.carregar = function(cliente) {
								$scope.parametro.idCliente = cliente.id;
								setTimeout(function() {
									$rootScope.isVisible.loading = true;
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "cliente/get",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.cliente = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};

							$scope.excluir = function(cliente) {
								if(!confirm('Deseja Excluir o Cliente ?')) {
									return;
								}
								cliente.login = $scope.login;
								$scope.parametro.idCliente = cliente.id; 
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "cliente/excluir",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.pesquisarPorNome();
													
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							////////////////////////////////////////////////////////
							
							$scope.getListCorretorPorIdEmpresa = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/getListCorretorPorIdEmpresa",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.corretores = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.getListCaptadorPorIdEmpresa = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/getListCorretorPorIdEmpresa",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.captadores = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};



							$scope.getListSupervisorPorIdEmpresa = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								setTimeout(function() {
									$rootScope.isVisible.loading = true;
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/getListSupervisorPorIdEmpresa",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.supervisores = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.getListSupervisorPorIdSupervisor = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/getListSupervisorPorIdSupervisor",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.supervisores = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.getListCorretorPorIdSupervisor = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/getListCorretorPorIdSupervisor",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.corretores = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.getListCorretorPorIdCorretor = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/getListCorretorPorIdCorretor",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.corretores = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};
							
							$scope.getListSupervisorPorIdCorretor = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "corretor/getListSupervisorPorIdCorretor",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.supervisores = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};

							$scope.getEmpresaPorId = function() {
								if($scope.parametro.login == undefined || $scope.parametro.login.email == undefined) {
									alert("Sem Parametros");
								}
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "empresa/getEmpresaPorId",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.empresa = data;
											})
										.error(
											function(erro) {
												alert("ERRO no envio dos dados ! "
														+ erro == undefined ? ""
														: erro);
											})
									}, 100);

								})
							};

							$scope.cancelar = function() {
								$scope.index = true;
								$scope.cliente = {};
								$scope.cliente.situacao = "Selecione...";
								$scope.cliente.captacao = "Selecione...";
							};
							if($scope.login.cargo == "Imobiliaria") {
								$scope.getListCorretorPorIdEmpresa();
								$scope.getListSupervisorPorIdEmpresa();
								$scope.getListCaptadorPorIdEmpresa();
							}
							if($scope.login.cargo == "Supervisor") {
								$scope.getListCorretorPorIdSupervisor();
								$scope.getListSupervisorPorIdSupervisor();
								$scope.getListCaptadorPorIdEmpresa();
							}
							if($scope.login.cargo == "Corretor") {
								$scope.getListCorretorPorIdCorretor();
								$scope.getListSupervisorPorIdCorretor();
								$scope.getListCaptadorPorIdEmpresa();
							}
							$scope.pesquisarPorNome();
							$scope.getEmpresaPorId();
						} ]);
