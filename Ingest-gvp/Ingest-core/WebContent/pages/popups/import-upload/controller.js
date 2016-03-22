angular.module('ingest')
.controller('uploadController', ['$scope', '$log', '$modalInstance', 'options', 'importSvc',
  function($scope, $log, $modalInstance, options, importSvc){
    
	$scope.submit = function() {
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
    	importSvc.addUpload(file).then(
    		function(data) {
    			$modalInstance.close(data);
    		}
      ).catch(function(error) {
        $log.warn('An unexpected error to REGISTER upload: ' + error.data.message);
      });
    }
  }
])
.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}])
.directive('validFile',function(){
    return {
        require:'ngModel',
        link:function(scope,el,attrs,ngModel){
          //change event is fired when file is selected
            el.bind('change',function(){
                    scope.$apply(function(){
                        ngModel.$setViewValue(el.val());
                        ngModel.$render();
                    });
            });
        }
    };
});
