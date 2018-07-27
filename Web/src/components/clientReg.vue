<template>
	<div class="wrap">
		<div class="action"> <h3>Регистрация клиента</h3> </div>
		<div class="container">
			<div class="reg">
				<input type="text" class="form-control" placeholder="Имя">
				<input type="text" class="form-control" placeholder="Фамилия">
				<input type="text" class="form-control" placeholder="Адрес электронной почты">
				<div class="div">
					<label class="cb custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input">
						<span class="custom-control-indicator"></span>
						<span class="custom-control-description"> Является гидом </span>
					</label>
					<button class="registr btn btn-outline-secondary">Зарегистрировать</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data(){
		return {}
	}
}

</script>

<style scoped>
	.wrap {
		text-align: center;
		margin: 0 -15px;
	}
	h3 {
		margin: 5px 20px 10px 20px;
	}
	.reg {
		width: 60%;
		margin: 20px auto;
	}
	input {
		margin-top: 20px;
	}
	.custom-control-input:checked~.custom-control-indicator {
		background-color: #41B883;
	}
	.form-control:focus {
	    border-color: #41B883;
	}
	.div {
		margin-top: 10px;
		display: flex;
		justify-content: space-between;
	}
	.action {
		text-align: left;
		border-bottom: 1px solid rgba(0,0,0,0.3);
	}
</style>
