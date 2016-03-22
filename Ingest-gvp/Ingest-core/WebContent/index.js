"use strict";

window.module = window.angular.module("ingest", [ "ngAnimate", "ngRoute", "ui.bootstrap", "toaster", "tc.chartjs", "infinite-scroll", "ui.mask"]);
window.module.config(function($httpProvider) {
	$httpProvider.interceptors.push("httpInterceptor");
});

window.module.run(['$rootScope', '$location', '$route', '$timeout',
	function($rootScope, $location, $route, $timeout) {
		$rootScope.checkActiveMenu = function() {
			var path = $location.$$path.split("/");
			$("#sidebar li").each(function(index) {
				var href = $(this).children("a").attr("href");
				if (href && href.length > 0) {
					href = href.replace(/^#/g, "").split("/");
					for (var i = 0; i < href.length; i++) {
						if (href[i] != path[i]) {
							return true;
						}
					}
					$("#sidebar li").removeClass("active");
					$(this).addClass("active");

					return false;
				}
			});
		};

		// Event to activate automatically scroll.
		$rootScope.$on('ingest::activateScroll', function() {
			var $this = $('.scrollable');
		  $this.ace_scroll({
		    size: $this.attr('data-size') || 100,
		  });
		});

		/**
		 * Reloads path without refresh page.
		 *
		 * @param {String} path Path to reload.
		 * @return {Objetct} $location object updated.
		 */
		$location.skipReload = function (path) {
			var lastRoute = $route.current,
				un = $rootScope.$on('$locationChangeSuccess', function () {
	          $route.current = lastRoute;
	          un();
	      });

			return $location.path.apply($location, [path]);
    };

		$rootScope.$on("$routeChangeSuccess", function(event, currentRoute) {
			$rootScope.checkActiveMenu();
		});
	}
]);

var DEFAULT_ERROR_CALLBACK = function(responseData) {
	toaster.pop({
		"type" : "error",
		"title" : responseData.errorMessage
	});
};
