var Requests = {
	get(from){
		var promise = new Promise(function(resolve,reject){
			var xhr = new XMLHttpRequest;
				xhr.open("GET",from,true);
				xhr.onload = function(){
					resolve(this.responseText);
				}
				xhr.onerror = function(){
					alert("Не удалось получить данные с сервера.")
				}
				xhr.send(null);
		})
		return promise;
	}
	,send(to){
		var promise = new Promise(function(resolve,reject){
			var xhr = new XMLHttpRequest;
				xhr.open("POST",from,true);
				xhr.onload = function(){
					resolve(this.responseText);
				}
				xhr.onerror = function(){
					alert("Не удалось получить данные с сервера.")
				}
				xhr.send(null);
		})
		return promise;
	}
}