var agendaCtrl = angular.module('agendaCtrl', []);
appAgenda.controller('agendaCtrl', [
		'$scope',
		'$location',
		'$http',
		'$rootScope',
		'usuarioService',
		function($scope, $location, $http, $rootScope, usuarioService) {
	
			$location.path('/agenda');
			
			$scope.agendamentos = function() {
				$location.path('/agenda');
			}

			$scope.formLeadgen = function() {
				$location.path('/formLeadgen');
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
			
			$scope.painel = function() {
				$location.path('/painel');
			}
			
			$scope.cadastrarSituacao = function() {
				$location.path('/situacao');
			}
			
			$scope.cadastrarMidia = function() {
				$location.path('/midia');
			}
			
			$scope.captacao = function() {
				$location.path("/captacao");
			}
			
			$scope.vincularCorretor = function() {
				$location.path('/vincularCorretor');
			}
			
			$scope.vincularSupervisor = function() {
				$location.path('/vincularSupervisor');
			}
			
			$scope.resumoPorCorretor = function() {
				$location.path('/resumoPorCorretor');
			}
			
			$scope.resumoPorSituacao = function() {
				$location.path('/resumoPorSituacao');
			}
			
			$scope.gestao = function() {
				$location.path('/gestao');
			}
			
			$scope.logout = function() {

				window.location.href = CONTEXTO + "index.html";
			}
			$scope.sistema = 'CapiteWeb';
			$scope.login = usuarioService.popularLogin();
			$scope.agenda = {};
			$scope.agenda.agendado = true;
			$scope.agendas = [];
			$scope.index = true;
			$scope.empresa = {};
			$scope.clientes = [];
			$scope.situacoes = [];
			$scope.empreendimentos = [];
			$scope.ligacoes = [];
			$scope.agenda.login = $scope.login;
			$scope.parametro = usuarioService.popularParametro();
			$scope.detalhe = false;
			$scope.idSituacao =0;
			$scope.totalRegistros = 0;
			$scope.totalSituacao = 0;
			
			 
			$scope.tmp = $scope.login.nome.split(" ");
			$rootScope.usuario = $scope.tmp[0].toUpperCase();
			
			$scope.detalhar = function() {
				//if($scope.agenda.cliente.renda.substring(0,1) != "R") {
				if( $scope.agenda.cliente.renda != undefined && $scope.agenda.cliente.renda != null) {
					$scope.cliente.renda = $scope.agenda.cliente.renda.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
				} else {
					$scope.cliente.renda = "R$ 0,00";
				}
				if($scope.agenda.cliente.entrada != undefined && $scope.agenda.cliente.entrada != null) {
					$scope.cliente.entrada = $scope.agenda.cliente.entrada.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
				} else {
					$scope.cliente.entrada = "R$ 0,00";
				}
				if($scope.agenda.cliente.fgts != undefined && $scope.agenda.cliente.fgts != null) {
					$scope.cliente.fgts = $scope.agenda.cliente.fgts.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
				} else {
					$scope.cliente.fgts = "R$ 0,00";
				}
				if($scope.agenda.cliente.fone1 != undefined && $scope.agenda.cliente.fone1 != null) {
					$scope.cliente.telefone1 = "("+$scope.agenda.cliente.fone1.substring(0,2)+")"+$scope.agenda.cliente.fone1.substring(2,7)+"-"+$scope.agenda.cliente.fone1.substring(7,11);
				} else {
					$scope.cliente.telefone1 = "";
				}
				if($scope.agenda.cliente.fone2 != undefined && $scope.agenda.cliente.fone2 != null) {
					$scope.cliente.telefone2 = "("+$scope.agenda.cliente.fone2.substring(0,2)+")"+$scope.agenda.cliente.fone2.substring(2,7)+"-"+$scope.agenda.cliente.fone2.substring(7,11);
				} else {
					$scope.cliente.telefone2 = "";
				}
				if($scope.agenda.cliente.fone3 != undefined && $scope.agenda.cliente.fone3 != null) {
					$scope.cliente.telefone3 = "("+$scope.agenda.cliente.fone3.substring(0,2)+")"+$scope.agenda.cliente.fone3.substring(2,7)+"-"+$scope.agenda.cliente.fone3.substring(7,11);
				} else {
					$scope.cliente.telefone3 = "";
				}

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
				$scope.pesquisarClientes();
				$scope.pesquisarLigacoes();
				$scope.detalhar();
			}

			$scope.novo = function() {
				$scope.agenda = {};
				$scope.agenda.cliente = {};
				$scope.agenda.login = $scope.login;
				$scope.index = false;
				$scope.detalhe = false;
				//$scope.pesquisarClientes();
				$scope.detalhar();
				$scope.ligacoes = [];
			}
			
			$scope.pesquisarSituacoes = function() {
				$rootScope.isVisible.loading = true;
				setTimeout(function() {
					setTimeout(
					function() {
						$http(
						{
							url : URL
									+ "situacao/listPorEmpresa",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						})
						.success(
							function(data) {
								$rootScope.isVisible.loading = false;
								$scope.situacoes = data;
								//$scope.idSituacao = data[0].id;
								//$scope.pesquisar();
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
			$scope.pesquisarLigacoes = function() {
				$rootScope.isVisible.loading = true;
				setTimeout(function() {
					setTimeout(
					function() {
						$http(
						{
							url : URL
									+ "ligacoes/getPorIdAgenda",
							method : "POST",
							contentType : "application/json",
							data : $scope.agenda
						})
						.success(
							function(data) {
								$rootScope.isVisible.loading = false;
								$scope.ligacoes = data;
								
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
							/*$scope.situacao = "Contato";
							$scope.pesquisarClientes();*/
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
				if($scope.agenda.cliente != undefined) {
					$scope.parametro.idSituacao = $scope.agenda.cliente.idSituacao;
				}
				$rootScope.isVisible.loading = true;
				setTimeout(function() {
					setTimeout(function() {
						$http({
							url : URL + "agenda/listPorCorretorSituacao",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						}).success(function(data) {
							$rootScope.isVisible.loading = false;
							$scope.agendas = data;
							$scope.countPorCorretor();
							$scope.totalSituacao = $scope.agendas.length;
							//$scope.pesquisarClientes();

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
			
			$scope.countPorCorretor = function() {
				if($scope.agenda.cliente != undefined) {
					$scope.parametro.idSituacao = $scope.agenda.cliente.idSituacao;
				}
				$rootScope.isVisible.loading = true;
				setTimeout(function() {
					setTimeout(function() {
						$http({
							url : URL + "agenda/countPorCorretor",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						}).success(function(data) {
							$rootScope.isVisible.loading = false;
							$scope.totalRegistros = data;
							//$scope.pesquisarClientes();

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
			
			$scope.carregarPorCliente = function(agenda) {
				$rootScope.isVisible.loading = true;
				$scope.parametro.idCliente = agenda.cliente.id;
				setTimeout(function() {
					setTimeout(function() {
						$http({
							url : URL + "agenda/getPorCliente",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						}).success(function(data) {
							$rootScope.isVisible.loading = false;
							$scope.agenda = data;
							if($scope.agenda.idCliente == null) {
								$scope.agenda.idCliente = $scope.parametro.idCliente;
							}
							$scope.agenda.login = $scope.login;
							$scope.pesquisarLigacoes();
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
			
			$scope.pesquisarClientes = function(limpar) {
				$scope.parametro.idSituacao = $scope.agenda.cliente.idSituacao;
				setTimeout(function() {
					$rootScope.isVisible.loading = true;
					setTimeout(
					function() {
						$http(
						{
							url : URL
									+ "cliente/listPorNomeSituacao",
							method : "POST",
							contentType : "application/json",
							data : $scope.parametro
						})
						.success(
							function(data) {
								$rootScope.isVisible.loading = false;
								$scope.clientes = data;
								//$scope.agenda.idCliente = data[0].id;
								//$scope.carregarPorCliente($scope.agenda);
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
									+ "empreendimento/listPorEmpresaDisponivel",
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
				//$scope.agenda = {};
				//$scope.pesquisarSituacoes();
				$scope.pesquisar();
/*				$scope.situacao = "Contato";
				$scope.pesquisarClientes();*/
			};

			/*$scope.pesquisarClientes();*/
			$scope.pesquisarEmpreendimentos();
			$scope.pesquisarSituacoes();
			$scope.pesquisar();

		} ]);