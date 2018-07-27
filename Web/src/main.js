import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';

import Home from './components/Home.vue'
import CreateTour from './components/CreateTour.vue'
import clientReg from './components/clientReg.vue'
import addCity from './components/addCity.vue'

Vue.use(VueRouter)

var router = new VueRouter({
	routes: [
		{ path: "/home", component: Home }
		,{ path: "/createTour", component: CreateTour }
		,{ path: "/clientReg", component: clientReg }
		,{ path: "/addCity", component: addCity }
		// ,{ path: "/CreateCity/CreatePlace", component: CreatePlace }
		]
	,mode: 'history'
})

new Vue({
  el: '#app'
  ,router: router
  ,render: h => h(App)
})