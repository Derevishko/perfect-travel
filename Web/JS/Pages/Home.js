var Home = {
	Init() {
		Home.UI.render();	
	}
	,Methods: {
		async getDataFromServer() {
			var Tours = JSON.parse(await Requests.get('/api/tour'))
			var Cities = JSON.parse(await Requests.get('/api/city'))
			return {Cities, Tours}
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
			var checkboxUl = document.getElementsByClassName('cb-ul')[0];
			var toursDiv = document.getElementsByClassName('rest-tours')[0];

			Home.Methods.getDataFromServer().then(function(data) {
				var citiesLimit;
				for(var city in data.Cities){			
					var li = document.createElement('li');
					li.innerHTML = `<input type="checkbox" id="${city}">
									<span class="cb-span">${data.Cities[city]}</span>`
					li.classList.add('cb-li')
					checkboxUl.appendChild(li)
				}
				var li = document.createElement('li')
				li.classList.add('cb-li')
				li.innerHTML = '<input id="showAll" type="text" placeholder="Показать все">'
				checkboxUl.appendChild(li);


			
				var toursLimit;
				for(var tour in data.Tours){	
					var tourdiv = document.createElement('div')
						tourdiv.classList.add('tour')
						tourdiv.innerHTML = `<h1 class="tour-title">${data.Tours[tour].name}</h1>
										<div class="pic-div">
											<img  class="pic" src="">
										</div>
										<div class="desc-div">
											<p class="desc">${data.Tours[tour].description}</p>
										</div>
										<div class="cost-btn row">
											<span class="cost">Стоимость: ${data.Tours[tour].price}</span>
											<a href="#tour"><button class="next btn" id="${tour}">Далее</button></a>
										</div>`
						toursDiv.appendChild(tourdiv);
				}
				var next = document.getElementsByClassName('next')
					for(var btn of next){
						btn.addEventListener('click',function(e){
							var id = e.target.getAttribute('id')
							Tour.Init(id);
						})
					}
					
			}, err => console.error(err))
		}
	}
}