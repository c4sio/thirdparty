angular.module('ingest')
.directive('breadcrumb', function() {
  return {
    restrict: 'AE',
    templateUrl : 'modules/breadcrumb/tmpl.html',
    controller: 'breadcrumbController',
    replace: true,
  }
});
