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
	,send(what,to){
		var promise = new Promise(function(resolve,reject){
			var xhr = new XMLHttpRequest;
			var json = JSON.stringify(what);
				xhr.open("POST",to,true);
				xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
				xhr.onload = function(){
					resolve(this.responseText);
				}
				xhr.onerror = function(){
					alert("Не удалось получить данные с сервера.")
				}
				xhr.send(json);
		})
		return promise;
	}
}