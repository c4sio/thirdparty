angular.module('ingest')
.controller('breadcrumbController', ['$scope', 'breadcrumbSvc',
  function($scope, breadcrumbSvc){
    var items, $el;

    function updateBreadcrumb() {
      items = breadcrumbSvc.get();
      if ( items.length ) {
        angular.forEach(items, function(v) {
          $scope.breadcrumb.push(v);
        });
      }
    }

    $scope.unregisterBreadCrumb = function ( event, index ) {
      $el = angular.element(event.currentTarget),
      href = $el.attr('href'),
      mustUnregister = href.search('void') === -1;

      if ( !mustUnregister ) {
        return;
      }

      breadcrumbSvc.clearRight( index );
      updateBreadcrumb();
    };

    $scope.breadcrumb = [];

    updateBreadcrumb();

    $scope.$on('breadcrumbRefresh', function refreshBreadcrumb () {
      updateBreadcrumb();
    });
  }
]);
