<template>
	<div class="wrap">
		<div class="action"> <h3>Добавить город</h3> </div>
		<div class="container">
			<div class="title">
				<input type="text" class="form-control" placeholder="Название города">
			</div>
			<div class="qwe">
				<div class="img-btns">
					<img src="../assets/logo.png" alt="">
					<button class="btn btn-outline-secondary">Добавить</button>
					<button class="btn btn-outline-secondary">Назад</button>
				</div>
				<div class="desc">
					<textarea class="text" placeholder="Описание города"></textarea>
				</div>
				<div class="map">

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
	.map {
		width: 25%;
	}
	.text {
		width: 100%;
		height: 70%;
	}
	.desc {
		padding: 10px;
		width: 50%;
	}
	.btn {
		margin-top: 5px;
		width: 150px;
		/*padding: 5px 40px;*/
	}
	.img-btns {
		width: 25%;
	}
	img {
		border: 1px solid rgba(0,0,0,0.3);
		border-radius: 50%;
	}
	.qwe {
		display: flex;
	}
	.title {
		margin: 10px auto;
		width: 20%;
	}
	.container {
		margin: 15px auto;
		border: 1px solid rgba(0,0,0,0.3);
	}
	.wrap {
		text-align: center;
		margin: 0 -15px;
	}
	h3 {
		margin: 5px 20px 10px 20px;
	}
	
	.action {
		text-align: left;
		border-bottom: 1px solid rgba(0,0,0,0.3);
	}
</style>
