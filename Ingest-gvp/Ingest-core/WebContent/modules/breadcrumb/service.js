angular.module('ingest')
.factory('breadcrumbSvc', ['$rootScope', '$log',
  function($rootScope, $log){
    $log.info('breadcrumbService initialized.');
    var data = [], bcLabel = null;

    function broadcast () {
      $rootScope.$broadcast( 'breadcrumbRefresh' );
    }

    function getItems (index, list) {
      var arr = [];
      angular.forEach(list, function (value, key) {
        if ( key <= index ) {
          arr.push(value);
        }
      });
      return arr;
    }

    function createLabel () {
      if ( !bcLabel ) {
        return '';
      }
      return bcLabel.split('-');
    }

    // Parse the items to a format we can handle
    function parse (items) {
      var arr = [],
        item = {}
        label = createLabel();

      // If the first position is an empty string. Return an empty array.
      if (items[0] === '') {
        return arr;
      }

      angular.forEach(items, function(val, key) {
        // There is a '/' in the end of the URL. Do nothing in this case.
        if ( val === '' ) {
          return;
        }

        item.label = label[key];

        if ( key === 0 ) {
          item.href = '#/' + val;
        } else {
          item.href = '#/' + getItems(key, items).join('/');
        }

        arr.push(item);
      });

      return arr;
    }

    /**
     * @description Add multiple items to the breadcrumb
     * @param items {Array} URL params. Ex: ['dashboard', 'foo']
     * @param title {String} The document title, used to automatically create of
     *  breadcrumb items.
     **/
    function add (items, title) {
      bcLabel = title;
      var parsedItems,
          itShouldParseItems = !!title;

      if ( itShouldParseItems ) {
        parsedItems = parse(items);
        angular.forEach(parsedItems, function(val) {
          data.push(val);
        });
      } else {
        data = items;
      }

      broadcast();
    }

    // Get the breadcrumb items
    function get () {
      return data;
    }

    function clearRight (index) {
      var arr = [];
      angular.forEach(data, function (val, idx) {
        if ( idx > index )  {
          arr.push(val);
        }
      });
      data = arr;
    }

    $log.info('breadcrumbService execution ended.');

    return {
      add: add,
      get: get,
      clearRight: clearRight
    }
  }
])
