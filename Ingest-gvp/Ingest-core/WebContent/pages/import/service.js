angular.module('ingest')
.factory('importSvc',['requestSvc', function(request) {

	/**
	  * Stores the upload entity.
	  * @param {object} data The new upload data.
	  */
	 function addUpload(file) {
		var fd = new FormData(); 
		fd.append("uploadedFile", file);
		var options = {withCredentials: false, headers : {'Content-Type': undefined}, transformRequest: angular.identity};
		return request.post('/upload', fd, options).then(
	      function(uploadData) {
	    	  console.log('Aqui.. ');
			  console.log(uploadData.data);
	    	  return uploadData.data;
	      }
	    );
	  }
	 
	 /**
	  * List entity.
	  */
	 function listImports() {
		return request.get('/import').then(
				function(dataImport) {
					console.log('Aqui.. ');
					console.log(dataImport.data);
					return dataImport.data;
		});
	}

	return {						
		addUpload: addUpload,
		listImports : listImports
	};
} ]);
