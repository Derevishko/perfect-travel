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
			<button class="btn btn-outline-secondary">Далее</button>
		</div>		
	</div>
</template>

<script>
	export default {
		props: ['tourName','tourImg','tourDesc','tourCost']
		,data(){
			return {
				// tourName: "Name"
				// ,tourImg: "https://retarcorp.by/img/logo.png"
				// ,tourDesc: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequatur, optio cumque expedita quam quos nobis?"
				// ,tourCost: "150p"
			}
		}
	}
</script>

<style scoped>
	.tour {
		display: inline-block;
		padding: 10px 30px;
		margin: 5px;
		width: 310px;
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