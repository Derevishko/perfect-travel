var Tour = {
	Init(id) {
		Tour.UI.render(id);	
	}
	,Methods: {
		async getDataFromServer(id) {
			var temp = JSON.parse(await Requests.get('/api/tour'));
			var tour = JSON.parse(await Requests.get(`/api/tour/${temp[id].name}`));
			console.log(tour)
			return tour;		
		}
		,setCurrentCity(){
			var from = {}
			var time1 = new Date(Tour.Data.thisTour.from)

			from.Year = time1.getFullYear();
			from.Month = time1.getMonth() + 1;
			from.Day = time1.getDate();
			from.Hours = time1.getHours();
			from.Minutes = time1.getMinutes();
			from.Seconds = time1.getSeconds();
			// console.log(from)

			var time2 = new Date();
			var now = {}
			now.Year = time2.getFullYear();
			now.Month = time2.getMonth() + 1;
			now.Day = time2.getDate();
			now.Hours = time2.getHours();
			now.Minutes = time2.getMinutes();
			now.Seconds = time2.getSeconds();
			// console.log(now)

		}
	}
	,Constants: {

	}
	,Data: {
		thisTour: []
	}
	,UI: {
		render(id){
			console.log(id)
			Tour.Methods.getDataFromServer(id).then(function(data) {
			Tour.Data.thisTour = data;
			var statusBar = document.getElementsByClassName('status-bar')[0]
				document.getElementsByClassName('slogan')[0].innerText = data.name;
				for(var city in data.cities){
					// console.log(city)
					var div = document.createElement('div')
						div.classList.add('col')
						div.classList.add('wrap')
						div.classList.add('status-item')
						// console.log(div)
						
						var first = `<div class="round-city row" id="${city}">${data.cities[city].name}</div>
										<div class="accordeon">
											<ol class="text-center">`
						var second = ``
												
							for(var place of data.cities[city].places) {
								var t1 = new Date(place.from)
								second += `<li>${t1.getHours()}:${t1.getMinutes()} - ${place.name}</li>`
							}

						var third = `</ol></div>`
						div.innerHTML = first + second + third;				
						statusBar.appendChild(div)
				}
				return data;
			}, err => console.error(err)).then(function(data){
				var rndCity = Array.from(document.getElementsByClassName('round-city'))
					for(var item of rndCity) {
						item.addEventListener('click',function(e){
							var el = e.target.parentNode.lastChild;
							var cityInfo = document.getElementsByClassName('cityInfo')[0]
							el.classList.toggle('accordeon-visible')
							if( el.classList.contains('accordeon-visible') ){
								cityInfo.innerHTML = '';
								var ul = document.createElement('ul')
								var currentCity = Tour.Data.thisTour.cities[e.target.getAttribute('id')]
								for( var key in currentCity ) {
									var li = document.createElement('li')
										li.innerHTML = `${key} - ${currentCity[key]}`;
										ul.appendChild(li)
								}

								cityInfo.appendChild(ul)
							} else {
								cityInfo.innerHTML = '';
							}
						})
					}
				Tour.Methods.setCurrentCity();
			});
		}
	}
}