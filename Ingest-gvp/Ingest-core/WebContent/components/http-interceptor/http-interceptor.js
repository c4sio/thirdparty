"use strict";

window.module.factory("httpInterceptor", [ "$log", "$timeout", "$location", "$q", "toaster", function($log, $timeout, $location, $q, toaster) {
	var service = {};

	service.request = function(config) {
		return config;
	};

	service.requestError = function(rejection) {
		return $q.reject(rejection);
	};

	service.response = function(resp) {
		return resp;
	};

	service.responseError = function(rejection) {
		var responseStatus = rejection.status;
		var forbidden = responseStatus === 403;

		if (forbidden) {
			var toast = {
				"type" : "error",
				"title" : "Acesso Negado",
				"body" : "Você não tem permissão para essa requisição."
			};
			$location.path("/");

			toaster.pop(toast);
		}

		return $q.reject(rejection);
	}

	return service;
} ]);