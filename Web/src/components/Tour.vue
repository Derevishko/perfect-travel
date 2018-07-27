<template>
	<div class="tour container">
		<div class="row">
			<h3 class="tour-name">{{ tourName }}</h3>
			<button class="btn btn-outline-secondary" type="button">
				<i class="fa fa-times"></i>
			</button>
		</div>
		<div class="row">
			<img class="img" v-bind:src="tourImg">
			<p> {{ tourDesc }}</p>
		</div>
		<div class="row bot">
			<p>{{ tourCost }}</p>
			<a><button class="btn btn-outline-secondary">Далее</button></a>
		</div>		
	</div>
</template>

<script>
	export default {
		props: ['tourName','tourImg','tourDesc','tourCost','tourId']
		,data(){
			return {
			}
		}
	}
</script>

<style scoped>
	.tour {
		/*display: inline-block;*/
		padding: 10px 30px;
		margin: 5px;
		width: 100%;
		height: 310px;
		border: 1px solid #ccc;
	}
	.tour-name {
		margin: auto;
	}
	.bot {
		justify-content: space-between;
	}
	.img {
		height: 50%;
		width: 100%;
	}
</style>					