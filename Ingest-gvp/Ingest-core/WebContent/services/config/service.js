'use strict'
/**
 * Config service.
 */
angular.module('ingest')
.factory('configSvc', ['requestSvc',
  function(request) {
    /**
     * Gets a config.
     *
     * @param {Obejct} channel Channel object.
     */
    function getConfig(channel) {
      return request.get('/config').then(function(config) {
        var tmpConfig = {};
        config.data.forEach(function(conf) {
          tmpConfig[conf.key] = conf.value;
        });

        return tmpConfig;
      });
    }

    return {
      getConfig: getConfig
    }
  }
]);
