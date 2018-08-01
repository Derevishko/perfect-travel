var ClientReg = {
	Init() {
		ClientReg.Methods.getDataFromServer();
		var addClient = document.getElementById('addClient');
			addClient.addEventListener('click',function(e){

				var client = {}
				var name = document.getElementById('user').value;
					client.email = ClientReg.Data.Users.find(function(el,index,array){
						return (el.name == name) 
					}).email;
					client.name = document.getElementById('user_tour').value;
					console.log(ClientReg.Data.Users)
					console.log(client)
					Requests.send(client,'/api/adduser');
			})
	}
	,Methods: {
		async getDataFromServer() {
			ClientReg.Data.Tours = JSON.parse(await Requests.get('/api/tour'))
			ClientReg.Data.Users = JSON.parse(await Requests.get('/api/users'))
			// console.log(Tours)
			var user = document.getElementById('user');
				for(var u in ClientReg.Data.Users) {
					var opt = document.createElement('option')
						opt.innerText = ClientReg.Data.Users[u].name;
						opt.setAttribute('id',u)
						user.appendChild(opt)
				}
			var selectTour = document.getElementById('user_tour');
				for(var tour in ClientReg.Data.Tours) {
					var opt = document.createElement('option')
						opt.innerText = ClientReg.Data.Tours[tour].name;
						selectTour.appendChild(opt)
				}
		}
	}
	,Constants: {

	}
	,Data: {
		Tours: []
		,Users: []
	}
	,UI: {
		
	}
}