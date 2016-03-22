'use strict';
/**
 * Popups service.
 */
angular.module('ingest')
.service('popupSvc', ['$modal',
  function($modal) {
    /**
     * Opens a modal popup.
     * @param {{
     *   templateUrl: string Path of the template to load.
     *   controller: string Path of the controller to load.
     * }} data Data to create the popup.
     * @private
     * @return {Promise} $modal promise.
     */
    function _open(data) {
      return $modal.open({
        templateUrl: data.templateUrl,
        controller: data.controller,
        windowClass: data.className || '',
        resolve: {
          options: function() {
            return data.options;
          }
        }
      });
    }

    /**
     * Opens a dialog popup.
     *
     * @params {Object} options Options object.
     * @return {Promise} $modal promise.
     */
    function openDialog(message) {
      return _open({
        templateUrl: 'pages/popups/dialog/tmpl.html',
        controller: 'dialogCtrl',
        options: {message:message}
      });
    }

    /**
     * Opens the edit staff pop-up.
     * @return {Promise} $modal promise.
     */
    function openUploadFile(options) {
    	console.log('Open popup');
    	return _open({
        templateUrl: 'pages/popups/import-upload/tmpl.html',
        controller: 'uploadController',
        options: options
      });
    }

    return {      
      openDialog: openDialog,     
      openUploadFile: openUploadFile
    };
  }
]);
