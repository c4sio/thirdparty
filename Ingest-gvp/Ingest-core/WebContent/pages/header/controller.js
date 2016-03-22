"use strict";
angular.module('ingest')
.controller("HeaderController", ['$scope', '$log', '$interval', 'importSvc', 'popupSvc', 'toaster',
	function($scope, $log, $interval, importSvc, popupSvc, toaster) {
		$log.info("HeaderController initialized"); 
		$log.info("HeaderController execution ended");
}]);
