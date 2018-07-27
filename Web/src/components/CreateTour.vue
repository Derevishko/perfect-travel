<template>
	<div class="wrap">
		<div class="action"> <h3>Создание тура</h3> </div>
		<div class="container">
				<div class="edit">
					<div class="column">
						<h5>Введите название тура</h5>
						<input type="text" class="form-control" placeholder="">
					</div>
					<div class="column" >
						<h5>Подробное описание тура</h5>
						<textarea type="text" class="form-control" placeholder=""></textarea>
					</div>
					<div class="column">
						<h5>Стоимость тура</h5>
						<input type="text" class="form-control" placeholder="">
					</div>
					<div class="column">
						<h5>Города</h5>
						<div class="select">
							<input type="text" class="input">
							<div class="cities">
								<label class="cb-city custom-control custom-checkbox" v-for="city in citiesArray">
									<input type="checkbox" class="custom-control-input">
									<span class="custom-control-indicator"></span>
									<span class="custom-control-description"> {{city.city_name}} </span>
								</label>
							</div>
						</div>
					</div>
				</div>
			<div class="submit-btn">
					<button class="add btn btn-outline-secondary"> Добавить тур</button>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data(){
			return {
				citiesArray: []
			}
		}
		,methods: {

		}
		,created(){
			const vm = this;
			const xhr = new XMLHttpRequest;
				  xhr.open("GET","../../DATABASE/JSON/Cities.json",true)
				  xhr.onload = function(){
					  var cities = JSON.parse(this.responseText);
				      vm.citiesArray = cities["Cities"];
				  }
				  xhr.send(null)
		}
	}
</script>

<style scoped>
	.on {
		display: block;
	}
	.cities {
		display: flex;
		flex-direction: column;
	}
	.custom-control-input:checked~.custom-control-indicator {
		background-color: #41B883;
	}
	.form-control:focus {
	    border-color: #41B883;
	}
	.cb-city {
		margin: auto ;
	}

	.container {
		margin-top: 20px;
	}
	textarea {
		height: 100px;
	}
	.form-control {
		width: 50%;
	}
	.edit {
		display: flex;
		flex-direction: column;
	}
	.column {
		min-height: 50px;
		margin-top: 20px;
		display: flex;
		flex-direction: row;
	}
	h5 {
		margin-right: 10px;
		text-align: right;
		width: 300px;
		display: inline-block;
	}
	h3 {
		margin: 5px 20px 10px 20px;
	}
	.wrap {
		text-align: center;
		margin: 0 -15px;
	}
	.action {
		text-align: left;
		border-bottom: 1px solid rgba(0,0,0,0.3);
	}
	.submit-btn {
		margin-top: 3%;
	}
</style>					