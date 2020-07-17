var agendaCtrl = angular.module('agendaCtrl', []);
appAgenda.controller('agendaCtrl', [
		'$scope',
		'$location',
		'$http',
		'$rootScope',
		'usuarioService',
		function($scope, $location, $http, $rootScope, usuarioService) {
			$scope.sistema = 'CapiteWeb';
			$scope.login = usuarioService.popularLogin();
			$location.path('/agenda');
			
			$scope.agendamentos = function() {
				$location.path('/agenda');
			}

			$scope.captacao = function() {
				$location.path('/captacao');
			}
			
			$scope.conta = function() {
				$location.path('/conta');
			}
			
			$scope.empreendimento = function() {
				$location.path('/empreendimento');
			}
			$scope.cliente = function() {
				$location.path('/cliente');
			}
			
			$scope.gestao = function() {
				if($scope.login.cargo != "Imobiliaria") {
					alert("Acesso somente para Imobiliárias!");
					$location.path('/agenda');
				} else {
					$location.path('/gestao');
				}
			}
			
			$scope.logout = function() {

				window.location.href = CONTEXTO + "index.html";
			}

			$scope.sistema = 'CapiteWeb';
			$scope.agenda = {};
			$scope.agenda.agendado = true;
			$scope.agendas = [];
			$scope.index = true;
			$scope.empresa = {};
			$scope.clientes = [];
			$scope.empreendimentos = [];
			$scope.login = usuarioService.popularLogin();
			$scope.agenda.login = $scope.login;
			$scope.parametro = usuarioService.popularParametro();
			$scope.detalhe = false;
			
			$scope.detalhar = function() {
				if($scope.agenda.cliente == undefined) {
					$scope.detalhe == false;
					alert("Detalhes do Cliente não Disponível na Inclusão do Agendamento.");
					return false;
				}
				if($scope.detalhe == true) {
					$scope.detalhe = false;
				} else {
					$scope.detalhe = true;
				}
			}

			$scope.alterar = function(agenda) {
				$scope.detalhe = false;
				$scope.agenda = agenda;
				$scope.agenda.login = $scope.login;
				$scope.index = false;
			}

			$scope.novo = function() {
				$scope.agenda = {};
				$scope.agenda.login = $scope.login;
				$scope.index = false;
				$scope.agenda.agendado = '0';
				$scope.detalhe = false;
			}

			$scope.salvar = function(agenda) {
				if(agenda.data == undefined || agenda.data == "") {
					alert("Informe a Data, e se quiser descartar o cliente desmarque o agendamento.");
					return false;
				}
				agenda.idEmpresa = $scope.login.idEmpresa;
				//agenda.data = agenda.data.substring(4,8)+'-'+agenda.data.substring(2,4)+'-'+agenda.data.substring(0,2)+'T'+agenda.data.substring(8,10)+':'+agenda.data.substring(10,12)+':00';
				if(agenda.data.substring(2,3) != "/") {
					agenda.data = agenda.data.substring(0,2)+'/'+agenda.data.substring(2,4)+'/'+agenda.data.substring(4,8)+' '+agenda.data.substring(8,10)+':'+agenda.data.substring(10,12);
				}
				agenda.idCorretor = $scope.login.idCorretor;
				setTimeout(function() {
					$rootScope.isVisible.loading = true;
					setTimeout(function() {
						$http({
							url : URL + "agenda/salvar",
							method : "POST",
							contentType : "application/json",
							data : agenda
						}).success(function(data) {
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
					setTimeout(function() {
						$http({
							url : URL + "agenda/listPorCorretor",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						}).success(function(data) {
							$rootScope.isVisible.loading = false;
							$scope.agendas = data;

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


			$scope.carregar = function(agenda) {
				$rootScope.isVisible.loading = true;
				$scope.parametro.idAgenda = agenda.id;
				setTimeout(function() {
					setTimeout(function() {
						$http({
							url : URL + "agenda/get",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						}).success(function(data) {
							$rootScope.isVisible.loading = false;
							$scope.agenda = data;
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

			$scope.excluir = function(agenda) {
				if (!confirm('Deseja Excluir o Agenda ?')) {
					return;
				}
				agenda.login = $scope.login;
				$rootScope.isVisible.loading = true;
				$scope.parametro.login.idAgenda = agenda.id;
				setTimeout(function() {
					setTimeout(function() {
						$http({
							url : URL + "agenda/excluir",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						}).success(function(data) {
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
					setTimeout(function() {
						$http({
							url : URL + "empresa/getEmpresaPorId",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						}).success(function(data) {
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
			
			$scope.pesquisarClientes = function() {
				setTimeout(function() {
					$rootScope.isVisible.loading = true;
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
								$rootScope.isVisible.loading = false;
								alert("ERRO no envio dos dados ! "
										+ erro == undefined ? ""
										: erro);
							})
					}, 100);

				})
			};
			
			$scope.pesquisarEmpreendimentos = function() {
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


			$scope.cancelar = function() {
				$scope.index = true;
				$scope.agenda = {};
			};

			$scope.pesquisarClientes();
			$scope.pesquisarEmpreendimentos();
			$scope.pesquisar();

		} ]);