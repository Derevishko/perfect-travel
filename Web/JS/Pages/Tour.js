var Tour = {
	Init() {
		Tour.UI.render();	
	}
	,Methods: {
		async getDataFromServer() {

		}
	}
	,Constants: {

	}
	,Data: {
		Cities: []
	}
	,UI: {
		render(){

			Tour.Methods.getDataFromServer().then(function(data) {
						
				
			}, err => console.error(d));
		}
	}
}