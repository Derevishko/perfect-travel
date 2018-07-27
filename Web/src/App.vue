<template>
	<div id="app">
 		<div class="container-fluid">
 			<pt-header></pt-header>
 		</div>
 		<div class="container-fluid height">
 			<router-view></router-view> 
 		</div>
 		<div class="container-fluid">
 			<pt-footer></pt-footer>
 		</div>
	</div>
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import Home from './components/Home.vue'

export default {
	components: {
		ptHeader: Header
		,ptFooter: Footer
		,ptHome: Home
	}
	,data(){
		return {}
	}
}

</script>

<style>
	.height{
		min-height: calc(100vh - 110px)
	}
	.bc-green {
		background-color: #41B883;
	}
		.btn-outline-secondary:hover {
		background-color: #41B883;
		border-color: #41B883;
	}
</style>
