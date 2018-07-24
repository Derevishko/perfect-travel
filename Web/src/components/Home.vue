<template>
	<div class="row">
		<div id="home" class="col-sm-12">
			<div class="">
				<h1 class="slogan text-center">Welcome to Perfect Travel!</h1>
				<div class="wrap container-fluid row">
					<div class="item-selector border-r col-sm-3">
						<pt-checkboxes></pt-checkboxes>	
					</div>
					<div class="tours row col-sm-9">
						<div class="tours-div">
							<pt-tour v-for="tour in toursArray" :tourId="tour.tour_id" v-bind:tourName="tour.tour_name" :tourImg="tour.tour_img" :tourDesc="tour.tour_desc" :tourCost="tour.tour_cost"></pt-tour>
							<div class="addNewTour">
								<h1 class="add"><i class="fa fa-plus"></i></h1>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import Tour from '../components/Tour.vue'
	import Checkboxes from '../components/Checkboxes.vue'

	export default {
		components: {
			ptTour: Tour
			,ptCheckboxes: Checkboxes
		}
		
		,data(){
			return {
				toursArray: []
			}
		}
		,methods:{

		}
		,created(){
			const vm = this;
			const xhr1 = new XMLHttpRequest;
				  xhr1.open("GET","../../DATABASE/JSON/Tours.json",true)
				  xhr1.onload = function(){
					  var tours = JSON.parse(this.responseText);
				      vm.toursArray = tours["Tours"];
				      console.log(vm.toursArray)
				  }
				  xhr1.send(null)
		}
	}
</script>

<style scoped>
	.slogan {
		margin: 10px 0 15px 0;	
	}
	.add {

	}
	.wrap {
		padding: 0;
		border-top: 1px solid rgba(0,0,0,0.3);
		justify-content: flex-start;
	}
	.tours {
		height: 100%;
	}
	.tours-div {
		width: 100%;
		flex-wrap: wrap;
	}
	
	.border-r {
		border-right: 1px solid rgba(0,0,0,0.3)
	}
	.item-selector {
		min-height: 100vh;
	}
	.addNewTour {
		display: inline-block;
		padding: 10px 30px;
		margin: 5px;
		width: 310px;
		height: 310px;
		border: 1px solid #ccc;
	}
</style>