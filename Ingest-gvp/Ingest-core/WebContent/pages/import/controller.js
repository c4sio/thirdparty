// INGEST Controller's Import
angular.module('ingest')
.controller('importCtrl', ['$scope', '$filter', '$log', '$interval', 'importSvc', 'config', 'popupSvc', 'toaster',
  function($scope, $filter, $log, $interval, importSvc, config, popupSvc, toaster){  
	 
	/**
	 * Upload file.
	 */
    $scope.uploadFile = function() {
    	console.log('Abrir o popup');
    	popupSvc.openUploadFile().result.then(
        function(data) {
        	toaster.pop({'type': 'success', 'title': 'Dado(s) lido(s) com sucesso'});
        	$scope.dataImport = data;
        }
      );
    };
   
    /**
	 * List files.
	 */
    function _getImports() {
          // Gets values
    	  importSvc.listImports()
          .then(function(response) {
        	$scope.dataImport = response;
            $scope.$emit('ingest::activateScroll');
          })
          .catch(function(error) {
            $log.warn('An unexpected error to GET values: ' +
              error.data.message);
          });       
      }
    
    _getImports();
    $scope.dataImport={};
  }
]);
