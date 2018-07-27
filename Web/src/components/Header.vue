<template>
	<header class="bc-green row">
		<nav class="navbar navbar-toggleable-md navbar-light">
			<router-link class="navbar-brand" to="/home">PERFECT TRAVEL</router-link>
			<router-link class="navbar-link" to="/clientReg">Регистрация клиента</router-link>
			<router-link class="navbar-link" to="/addCity">Добавить город</router-link>
		</nav>
	</header>
</template>
<style scoped>
	.navbar-link {
		margin-right: 10px; 
		text-decoration: none;
		color: black;
	}
</style>