angular
		.module("myapp", [])
		.controller(
				"MyController",
				function($scope, $http, $log) {

					$scope.retorno = null;
					$scope.error = false;
					$scope.clicked = false;

					$scope.submitTheForm = function(item, event) {
						$scope.submitted = true;
						$scope.clicked = true;

						if ($scope.myUserForm.$invalid) {
							$scope.clicked = false;
							return;
						}

						console.log("-> Submitting form");
						var dataObject = {
							name : $scope.name,
							password : $scope.password,
							email : $scope.email,
							subscreberId : $scope.subscreberId,
							androidId : $scope.androidId,
							appleId : $scope.appleId
						};

						var responsePromise = $http
								.post(
										"http://186.215.183.213/CreateUserJava/rest/services/user",
										dataObject, {});

						responsePromise.success(function(dataFromServer,
								status, headers, config) {
							console.log(dataFromServer);
							$scope.retorno = dataFromServer.retorno;
							$scope.error = dataFromServer.error;
							console.log($scope.retorno);
							$scope.submitted = false;
							$scope.clicked = false;
							$scope.clearMenu();
						});

						responsePromise
								.error(function(data, status, headers, config) {
									console.log("Submitting form failed!");
									$scope.retorno = 'There was a network error. Try again later.';
									$scope.clicked = false;
									$scope.submitted = false;
									$log.error(data);
								});
					};

					$scope.hideRetorno = function() {
						$scope.retorno = null;
					};

					$scope.clearMenu = function() {
						$scope.name = null;
						$scope.password = null;
						$scope.email = null;
						$scope.subscreberId = null;
						$scope.androidId = null;
						$scope.appleId = null;
					};

				});