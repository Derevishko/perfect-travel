<template>
	<div class="cb-div">

		<div class="row">
	        <div class="container">
	            <div class="input-group">
	                <input class="form-control border-secondary py-2" type="search" placeholder="search">
	                <div class="input-group-append">
	                    <button class="btn btn-outline-secondary" type="button">
	                        <i class="fa fa-search"></i>
	                    </button>
	                </div>
	            </div>
	        </div>
	    </div>
	
		<ul>
			<li v-for="city in 5">
				<label class="cb-city custom-control custom-checkbox">
					<input type="checkbox" class="custom-control-input">
					<span class="custom-control-indicator"></span>
					<span class="custom-control-description"> {{city}} </span>
				</label>
			</li>
			<li>
				<select class="form-control form-control-sm">
				  <option>Показать все</option>
				</select>
				<div class="allCities">
					<div>
					<div  v-show="visible" v-for="city in cities">
					  	<label class="cb-city custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input">
							<span class="custom-control-indicator"></span>
							<span class="custom-control-description"> {{city}} </span>
						</label>
					</div>
					</div>
				</div>
			</li>
		</ul>

	</div>
</template>	
<script>
	export default {
		data(){
			return {
				cities: ['City1','City2','City3','City4','City5','City6','City7','City8','City9','City10']
				,visible: true
			}
		}
		,methods:{
			
		}
	}
</script>
<style scoped>
	.allCities {
	position: fixed;
	top: 320px;
	left: 20px;
	width: 100%;
	display: block;
	}
	.allCities > div {
	padding-left: 10px;
	width: 20%;
	box-shadow: -3px 3px 5px rgba(0,0,0,0.5);
	border: 1px solid rgba(0,0,0,0.3);
	height: 50%;
	}
	ul {
		list-style: none;
		margin-left: -30px;
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
	.input-group{
		/*margin:5px;*/
	}
	.cb-div {
		padding: 5px;
	}
</style>