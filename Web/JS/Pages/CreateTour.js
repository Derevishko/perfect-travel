var CreateTour = {	
	Init() {
		CreateTour.UI.render();	
		CreateTour.Methods.addEventListeners();

	}
	,Methods: {
	async getDataFromServer() {
			var Cities = JSON.parse(await Requests.get('/api/city'))
			// console.log(Cities)
			var citiesArr = []
			for(city of Cities){
				// var field = city.toString()
				var cityInfo = JSON.parse(await Requests.get(`/api/city/${city}`))
				citiesArr.push(cityInfo);
			}
			// console.log(citiesArr)
			return citiesArr;

		}
		,addEventListeners(){
			var addCity = document.getElementById('addCity');
				addCity.addEventListener('click',function(e){
					document.getElementsByClassName('background')[0].classList.remove('off')
				})
			var select = document.getElementById('select')
				select.addEventListener('change',function(){

					const selected = document.querySelectorAll('#select option:checked');
					value = [];
					var value = Array.from(selected).map( (el) => ([el.value, parseInt(el.getAttribute('id'))]) )
					console.log((value[0])[1])
					var selectedPlaces = CreateTour.Data.Cities[(value[0])[1]].places;
					// console.log(selectedPlaces)
					var placesDiv = document.getElementsByClassName('places')[0]
					placesDiv.innerHTML = '';
					for(var place of selectedPlaces){
						var li = document.createElement('li');
							li.innerHTML = `<li><span> ${place.name} -</span>
											<span> ${place.description} </span>
											<button class="remove">
											<i class="fa fa-times"></i></button></li>`
							placesDiv.appendChild(li)
					}
					var li = document.createElement('li');
				

			var add = document.getElementById('ready');
				add.addEventListener('click',function(e){

					var cities = document.getElementsByClassName('cities')[0]
						for(var city of value){
							var li = document.createElement('li')
								li.innerHTML = `<i class="fa fa-building">${city[0]}</i><button class="remove"><i class="fa fa-times"></i></button>`
								li.setAttribute('id',city[1])
							cities.appendChild(li)
							value = [];
						}
						cities.addEventListener('click',function(e){
							if( e.target.classList.contains('remove') ){
								e.target.parentNode.remove();
								// value = [];

							} else if (e.target.classList.contains('fa-times')) {
								e.target.parentNode.parentNode.remove();
								// value = [];
							}
						})
					document.getElementsByClassName('background')[0].classList.add('off')
					value = [];
				})
				})
		}
	}
	,Constants: {

	}
	,Data: {
		Cities: []
		,Tours: []
	}
	,UI: {
		render(){
			CreateTour.Methods.getDataFromServer().then(function(data) {
				CreateTour.Data.Cities = data;
				// console.log(data)
				var select = document.getElementById('select');				
				for(var city in data){		
					var option = document.createElement('option')
						option.innerText = data[city].city.name;
						option.setAttribute('id',city)
					select.appendChild(option)
				}

			}, err => console.error(err));
		}
	}
}