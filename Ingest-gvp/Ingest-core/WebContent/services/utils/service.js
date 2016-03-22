'use strict'
/**
 * Utils service.
 */
angular.module('ingest')
.service('utils', [
  function() {
    /**
     * Moves element of array.
     *
     * @param {Array} array Array content.
     * @param {number} from Index of the item that will be moved.
     * @param {number} to Index to move.
     * @return {Array} New array ordered.
     */
    function moveElementArray(array, from, to) {
      return array.splice(to, 0, array.splice(from, 1)[0]);
    }

    return {
      moveElementArray: moveElementArray
    }
  }
]);
