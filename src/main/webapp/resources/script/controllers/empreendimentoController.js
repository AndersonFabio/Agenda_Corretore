var empreendimentoCtrl = angular.module('empreendimentoCtrl', []);
appAgenda.controller(
				'empreendimentoCtrl',
				[
						'$scope',
						'$http',
						'$routeParams',
						'$location','usuarioService','$rootScope',
						function($scope, $http, $routeParams, $location, usuarioService,$rootScope) {
							$scope.sistema = 'CapiteWeb';
							$scope.empreendimento = {};
							$scope.empreendimento.situacao = "Disponivel";
							$scope.empreendimentos = [];
							$scope.index = true;
							$scope.empresa = {};
							$scope.login = usuarioService.popularLogin();
							$scope.empreendimento.login = $scope.login;
							$scope.parametro = usuarioService.popularParametro();
							
							$scope.alterar = function(empreendimento) {
								$scope.empreendimento = empreendimento;
								$scope.empreendimento.login = $scope.login;
								$scope.index = false;
							}

							$scope.novo = function() {
								$scope.empreendimento = {};
								$scope.empreendimento.situacao = "Disponivel";
								$scope.empreendimento.login = $scope.login;
								$scope.empreendimento.idEmpresa = $scope.empresa.id;
								$scope.index = false;
							}

							$scope.salvar = function(empreendimento) {
								setTimeout(function() {
									$rootScope.isVisible.loading = true;
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "empreendimento/salvar",
											method : "POST",
											contentType : "application/json",
											data : empreendimento
										})
										.success(
											function(data) {
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
							
							$scope.pesquisar = function() {
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "empreendimento/listPorEmpresa",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.empreendimentos = data;
												
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
							
							$scope.pesquisarPorNome = function() {
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "empreendimento/listPorNome",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.empreendimentos = data;
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

							$scope.carregar = function(empreendimento) {
								$rootScope.isVisible.loading = true;
								$scope.parametro.idEmpreendimento = empreendimento.id;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "empreendimento/get",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
												$scope.empreendimento = data;
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

							$scope.excluir = function(empreendimento) {
								if(!confirm('Deseja Excluir o Empreendimento ?')) {
									return;
								}
								empreendimento.login = $scope.login;
								$scope.parametro.idEmpreendimento = empreendimento.id;
								$rootScope.isVisible.loading = true;
								setTimeout(function() {
									setTimeout(
									function() {
										$http(
										{
											url : URL
													+ "empreendimento/excluir",
											method : "POST",
											contentType : "application/json",
											data : $scope.parametro
										})
										.success(
											function(data) {
												$rootScope.isVisible.loading = false;
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
							
							$scope.getEmpresaPorId = function() {
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
								$scope.empreendimento = {};
								$scope.empreendimento.situacao = "Disponivel";
							};
							
							$scope.pesquisar();
							$scope.getEmpresaPorId();
						} ]);
