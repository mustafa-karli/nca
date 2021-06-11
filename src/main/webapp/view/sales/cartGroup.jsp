<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>


<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans" />
<style>
.thumbnail {
	padding-top: 0px;
	padding-right: 0px;
	padding-bottom: 0px;
	padding-left: 0px;
	box-shadow: 2px 2px 6px #aaa;
	margin-bottom: 10px;
	height: 240px;
	/*height:220px;*/
	/*background-color: #e7d7d7;*/
}

.thumbnail:hover {
	border: 1.2px solid gray;
}

.card-img-top {
	max-height: 150px;
	max-width: 150px;
	height: auto;
	width: auto;
	border-radius: 2%
}

.product_caption {
	font-family: Open Sans;
	font-weight: 600;
	font-size: 12px;
	font-size: 1, 7vw;
	text-align: center;
	vertical-align: middle;
	line-height: 105px;
	width: 100%;
}

.caption {
	max-height: 105px;
	height: 100%;
	width: 100%;
	height: 100%;
}

.thumbnail .caption {
	padding-top: 0px;
	padding-bottom: 0px;
}

.btn-group-xs>.btn, .btn-xs {
	padding: 1px 3px;
	font-size: 16px;
	line-height: 1;
	border-radius: 3px;
}

.form-control {
	display: inline-block;
	width: inherit;
}

.cart_product_caption {
	font-family: Open Sans;
	font-weight: 600;
	font-size: 12px;
	/*font-size: 1, 7vw;*/
	/*max-width: 150px;*/
}

.sc-cart-item-qty {
	height: 25px;
	padding: 6px 6px;
	font-size: 12px;
	min-width: 50px;
	width: 100%;
	/*width: 240px;*/
}

/*
.box-body {
	background-color: #ede6e6;
}*/
.sc-cart-count {
	margin-left: 15px;
}

.font_yeni {
	font-family: Open Sans;
	font-weight: 600;
	font-size: 12px;
	color: #333333;
}

.box-header {
	padding: 3px;
	background-color: #f5f5f5;
}

.box-body {
	padding: 0px;
	padding-left: 10px;
	background-color: #f1f1f1;
}

.renk {
	color: #333333;
}

.list-group-item {
	width: 100%;
	padding: 5px 10px;
}

.order_items {
	padding-right: 5px;
	padding-left: 5px;
}

.list-group {
	/*width: 250px;*/
	
}

.cart_margin {
	margin-right: 0px;
	margin-left: 0px;
}

.box {
	position: relative;
	border-radius: 1px;
	background: #ffffff;
	border-top: 1px solid #d2d6de;
	margin-bottom: 0px;
	width: 100%;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}
</style>

<div class="row">

	<div class="col-md-8 col-sm-8 col-xs-6">
		<div class="panel panel-default">
			<!-- 			<div class="panel-heading"> -->
			<%-- 				${PAGE_TITLE} --%>
			<!-- 			</div> -->
			<div class="panel-body">
				<div class="row">
					<ncaTags:productGroup productGroups="${productGroups}" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-6">
		<form method="POST" name=f id=f>
			<div id="smartcart" style="position: fixed; height: 80%;"></div>
		</form>
	</div>

</div>

<script src="js/jquery.smartCart.js" type="text/javascript"> </script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#smartcart').smartCart();
	});

	$('button.btn-box-tool').on('click', function() {
		//	$('html,body').animate({scrollTop: $('#smartcart').offset().top}, 800);
		$('html,body').animate({
			scrollTop : $(this).offset().top
		}, 800);

	});
</script>
<style>
<!--
-->
.panel-body {
	padding-top: 0px;
	padding-right: 15px;
	padding-bottom: 15px;
	padding-left: 15px;
	background-color: #f1f1f1;
}

.panel-heading {
	/*     padding: 10px 0px; */
	padding-top: 10px;
	padding-right: 15px;
	padding-bottom: 10px;
	padding-left: 15px;
}

.kutu {
	padding-right: 5px;
	padding-left: 5px;
	/*min-height: 240px;*/
	/*max-height: 240px;*/
}
</style>



