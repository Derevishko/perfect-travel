var mainContent = document.getElementsByClassName("main-content")[0];

var routes = {
	"#home" : {
		path : "JS/Routes/Home.json"
		,event : function(){
			Home.Init();
		}
	}
	,"#createTour" : {
		path : "JS/Routes/CreateTour.json"
		,event : function(){
			CreateTour.Init();
		}
	}
	,"#clientReg" : {
		path : "JS/Routes/ClientReg.json"
		,event : function(){
			ClientReg.Init();
		}
	}
	,"#tour" : {
		path : "JS/Routes/Tour.json"
		,event : function(){
			
		}
	}
}

var change = function(){
	var path = routes[location.hash].path;
	var events = routes[location.hash].event;
	var xhr = new XMLHttpRequest; 
	xhr.open("GET", path, true);
	xhr.onload = function(){
		mainContent.innerHTML = this.responseText;
		events();
	}
	xhr.send(null);
}

window.onhashchange = function(){
	change();
}
if (location.hash in routes){
	change();
}