var ClientReg = {
	Init() {
		ClientReg.Methods.getDataFromServer();
	}
	,Methods: {
		async getDataFromServer() {
			var Tours = await Requests.get('/api/tour')
			var selectTour = document.getElementById('selectTour');
				for(var tour of Tours) {
					var opt = document.createElement('option')
						opt.innerText = tour.name;
						selectTour.appendChild(opt)
				}
		}
	}
	,Constants: {

	}
	,Data: {

	}
	,UI: {
		
	}
}