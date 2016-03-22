angular.module('ingest')
.directive('navSearch', ['$location', function(location) {
  return {
    templateUrl: 'directives/nav-search/tmpl.html',
    replace: true,
    link: function(scope) {
      /**
       * Redirects the word for the search page.
       * @param  {string} searchData The word that user looking for.
       */
      scope.search = function(searchData) {
        location.path('/search').search('q=' + searchData);
      }
    }
  }
}]);
