/**
 * Creation of spinner directive.
 */
angular.module('ingest')
.directive('spinner', function() {
  return {
    scope: {
      type: '@?'
    },
    templateUrl: 'directives/spinner/tmpl.html'
  };
});
