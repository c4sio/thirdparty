/**
 * Constant configuration.
 */
angular.module('ingest').constant('config', {
  'ENDPOINTS': {
     'INGEST': './rest'
     // 'INGEST': 'http://172.30.1.44:8080/ingest/rest'
  },  
  'SEARCH': {
    'DATA_PER_PAGE': 10
  },
  'DATE': {
    'PT_BR': {
      'DATE_FORMAT': 'dd/MM/yyyy - HH:mm'
    },
    'EN_US': {
      'DATE_FORMAT': 'MM/dd/yyyy - HH:mm'
    },
    'ES_ES': {
      'DATE_FORMAT': 'dd/MM/yyyy - HH:mm'
    },
  }
});
