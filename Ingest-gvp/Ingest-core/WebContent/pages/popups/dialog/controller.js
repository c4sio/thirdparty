'use strict'
/**
 * Detail controller.
 */
angular.module('ingest')
.controller('dialogCtrl', ['$modalInstance', '$scope', 'toaster', 'options',
	function($modalInstance, $scope, toaster, options) {
		$scope.message = options.message;
}]);
