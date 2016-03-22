'use strict'
/**
 * Directive to create a simple multiselect with input search.
 *
 * @params {Object} {
 *  Scope: {
 *   @param {Object} items Items to show.
 *   @param {Object=} selectModel Object that serves as model.
 *   @param {String=} optionValue Corresponding property to the object "items"
 *       It will be used to define 'value' property.
 *   @param {String=} optionText Corresponding property to the object "items"
 *       It will be used to define 'text' property.
 *   @param {String=} userLanguage User's language.
 *   @param {boolean=} isSelected If true, the options are selected according
 *       model, false otherwise.
 *  }
 * }
 */
angular.module('ingest')
.directive('multiselect', ['$timeout', function($timeout) {
  return {
    scope: {
      items: '=',
      selectModel: '=?',
      optionValue: '@?',
      optionText: '@?',
      userLanguage: '=?',
      isSelected: '@?'
    },
    replace: true,
    templateUrl: 'directives/multiselect/tmpl.html',
    link: function(scope, element) {
      if (scope.items.length) {
        $timeout(function() {
          angular.element(element).find('.multi-select').multiselect({
            nonSelectedText: 'Selecione',
            nSelectedText: 'selecionados',
            allSelectedText: 'Todos os registros selecionados',
            enableFiltering: true,
            includeSelectAllOption: true,
            selectAllText: 'Selecionar todos',
            selectAllValue: 0,
            enableCaseInsensitiveFiltering: true,
            filterBehavior: 'both',
            filterPlaceholder: 'Digite o NOME ou ID',
            checkboxName: 'multiselect[]'
          }).end();
        });
      }
    }
  }
}]);
