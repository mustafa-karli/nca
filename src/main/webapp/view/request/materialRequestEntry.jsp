<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans" />

<style>
.btn-group {
	position: relative;
	top: -5px;
}

.thumbnail {
	padding-top: 0px;
	padding-right: 0px;
	padding-bottom: 0px;
	padding-left: 0px;
	box-shadow: 2px 2px 6px #aaa;
	margin-bottom: 5px;
	margin-top: 5px;
	height: 130px;
	/*height:220px;*/
	/*background-color: #e7d7d7;*/
}

.thumbnail:hover {
	border: 1.2px solid gray;
}

.card-img-top {
	max-height: 80%;
	max-width: 100%;
	border-radius: 2%
}

.product_caption {
	font-family: Open Sans;
	font-weight: 600;
	font-size: 12px;
	font-size: 1, 7vw;
	text-align: center;
	/*vertical-align: middle;
	line-height: 105px;*/
	width: 100%;
}

.caption {
	/* 	max-height: 105px; */
	width: 100%;
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

.renk2 {
	color: crimson;
	position: absolute;
	top: 0px;
	right: 5px;
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


<div class="box box-primary">

	<div class="box-body">
		<div class="col-md-9 col-sm-9 col-xs-6">
			<div class="panel-group" id="accordion">


				<div class="panel panel-default">

					<div class="panel-heading">
						<div class="panel-title">
							<a class="clps" data-toggle="collapse" data-parent="#accordion"
								href="#collapse1"> ${language.getTextUpper('MY_FAVORITE_PRODUCTS')} </a>
							<div class="btn-group btn-group-sm pull-right">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#favoriteList">${MY_FAVORITE_ORDERS}</button>
							</div>
						</div>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div id="favProducts" class="panel-body">
							<c:forEach var="product" items="${productGroup.items}"
								varStatus="pCount">

								<div class="col-md-3 col-sm-4 col-xs-12 kutu ${product.favorite}">
									<div class="sc-product-item thumbnail sc-add-to-cart urun"
										style="cursor: pointer">
										<img class="card-img-top"
											src="contentRelation/firstData/MT/${product.id}"
											alt="${product.caption}">
										<div class="caption">
											<div class="product_caption" data-name="product_name">${product.caption}</div>
											<p data-name="product_desc">${product.description}</p>

											<div>
												<input name="product_price" value="${product.price}"
													type="hidden" /> <input name="product_id" id="product_id"
													value="${product.id}" type="hidden" />
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<ncaTags:productGroup productGroups="${productGroups}" />

			</div>
		</div>

		<div class="col-md-3 col-sm-3 col-xs-6">
			<form method="POST" name=f id=f>
				<div id="smartcart" style="position: fixed; height: 80%;"></div>
				<input type="hidden" name="materialRequestId" id="materialRequestId"
					value="${materialRequestId}" />
			</form>
		</div>

	</div>
</div>

<div class="modal fade" id="favoriteList" tabindex="-1" role="dialog"
	aria-labelledby="favorites" aria-hidden="true">

	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="favorites">${MY_FAVORITE_ORDERS}</h5>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form>
					<div>
						<table id="tblFavorites" class="table-popup" data-col="id">

						</table>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Kapat</button>
			</div>
		</div>
	</div>
</div>


<script src="js/jquery.smartCart.js" type="text/javascript"> </script>
<script type="text/javascript">

$('#favoriteList').on('show.bs.modal', function (event) {
// 	  var button = $(event.relatedTarget) // Button that triggered the modal
// 	  var recipient = button.data('whatever') // Extract info from data-* attributes
	  var modal = $(this)
	  debugger;
	  $.getJSON("materialRequest/listFav", function(data){
			var tbl = modal.find('table');
			tbl.empty();
			var trHTML = '';
			var trHeader = '';

			$.each(data, function(index, element) {
			var date = new Date();

				trHTML += '<tr data-dismiss="modal" style="cursor: pointer">';
				trHTML += '<td data-col="id">' + element["id"]  + '</td>';
				trHTML += '<td data-col="username">' +  element["username"] + '</td>';
				trHTML += '<td data-col="requestDate">' + format_date(element["requestDate"],"D") + '</td>';
				trHTML += '<td data-col="dueDate">' + format_date(element["dueDate"],"D") + '</td>';

				
// 				for(code in element){
// 					if(index == 1){
// 						trHeader += '<th data-col="' + code + '">' + code + '</th>';
// 						}
// 					trHTML += '<td data-col="' + code + '">' + element[code] + '</td>';
// 				}
				trHTML += '</tr>';
		    });
			//trHeader = '<tr>' + trHeader + '</tr>';
			
			trHeader += '<tr><th data-col="id"> Sipariş Nu. </th>';
			trHeader += '<th data-col="username"> Kullanıcı Adı </th>';
			trHeader += '<th data-col="requestDate">Sipariş Tarihi </th>';
			trHeader += '<th data-col="dueDate">Teslim Tarihi </th></tr>';
			
		    tbl.append(trHeader);
		    tbl.append(trHTML);
      });
		 //modal.find('.modal-title').text('Material Request Date ' + element.id);
	})
	
$('#tblFavorites').on('click', 'tr', function(){
		var data_col = $('#tblFavorites').data('col');
		var mrId = $(this).find("td[data-col=" + data_col + "]").text();
		debugger;
		$.getJSON("materialRequest/getFav?id="+ mrId, function( data ){
			debugger;
					$('#smartcart').smartCart('clear');
					$('#smartcart').removeData('smartCart');
					$('#smartcart').empty()
					$("#smartcart").unbind('click');
					$('#smartcart').smartCart({
						'cart' : data
		 			});		
		  		 });

			$('#materialRequestId').val("");


	});
		

	$(document).ready(function() {
		debugger;
 		$('#smartcart').smartCart({
 			'cart' : [${mrJson}]});
 		$('div.Y').each(function() {
 	 		$( this ).appendTo("#favProducts").find("i").removeClass("fa-heart-o").addClass("fa-heart");
 		});

 		$('body').addClass("sidebar-collapse");
	});
	
	$('i.fa-list').on('click', function(){
			var nonFav = $(this).parent().parent().parent().siblings().find('div.N');
			if (nonFav.is(':hidden')) {
				nonFav.fadeIn("slow");
			} else {
				nonFav.fadeOut("slow");
			}
})

// 	$('a.clps').on('click', function() {
// 		$('html,body').animate({
// 			scrollTop : $(this).offset().top
// 		}, 2000);

// 	});
	
	$('.panel-collapse').on('shown.bs.collapse', function () {
		debugger;
		$('html,body').animate({
			scrollTop : $(this).parent().offset().top
		}, 800);
		})

	$('button#btnAddtoFav').on('click', function(){
		debugger;
		var mi = $(this);
		var icon = mi.find('i');
		var parentDiv = mi.parent();
		var parentDivIsFav = parentDiv.hasClass("Y");
		var matId = parentDiv.find('#product_id').val();
		var uname = $('#username').val();
		var obj = "";
		var url = "";
		var reqType = "";
		var groupId = "";
		var panelDiv = "";
		if(parentDivIsFav){
			url = "userFavorite/del/" + uname + ",MT," + matId;
			reqType = "DELETE";
			$.ajax({
		        type: reqType,
		        url: url,
		        cache: false,
		        timeout: 600000,
		        success: function (data) {
			         debugger;
					groupId = parentDiv.data("group");
					panelBody = $("#col_" + groupId).find('.panel-body');
					panelDiv = panelBody.find("[data-product='" + matId + "']");
					panelDiv.removeClass("Y").addClass("N");
					panelDiv.find('i').removeClass("fa-heart").addClass("fa-heart-o");
					
					$("#favProducts").find("[data-product='" + matId + "']").remove();
 			        console.log("SUCCESS : ", data);
	      		  },
		        error: function (e) {
		         console.log("ERROR : ", e);

		        }
		    });
		}else{
			url = "userFavorite/create";
			reqType = "POST"
			obj =  {"id":{"username": uname ,"favoriteType":"MT","objectId": matId}};
			$.ajax({
		        type: reqType,
		        contentType: "application/json",
		        url: url,
		        data: JSON.stringify(obj),
		        dataType: 'json',
		        cache: false,
		        timeout: 600000,
		        success: function (data) {
					parentDiv.removeClass("N").addClass("Y");
					icon.removeClass("fa-heart-o").addClass("fa-heart");
					parentDiv.clone(true,true).appendTo("#favProducts");
		            console.log("SUCCESS : ", data);
		   		     },
		        error: function (e) {
			         console.log("ERROR : ", e);
			         debugger;
		       		 }
			    });
			};
		});
</script>

<style type="text/css">
 .panel-body { 
  	padding-top: 0px; */
 	padding-right: 0px; */
 	padding-bottom: 0px; */
 	padding-left: 0px; */
 	background-color: #f1f1f1; */
 } 
 
 
  .kutu{ 
  	padding-top: 0px; */
 	padding-right: 0px; */
 	padding-bottom: 0px; */
 	padding-left: 0px; */
 	background-color: #f1f1f1; */
 } 
/* .panel-heading { */
/* 	/*     padding: 10px 0px; */
* /
	/* 	padding-top: 10px; */
	/* 	padding-right: 15px; */
	/* 	padding-bottom: 10px; */
	/* 	padding-left: 15px; */
	/* } */ 

.kutu {
  	padding-top: 0px; */
 	padding-right: 0px; */
 	padding-bottom: 0px; */
 	padding-left: 0px; */
 	background-color: #f1f1f1; */
	/*min-height: 240px;*/
	/*max-height: 240px;*/
}

/* .panel-default { */
/* 	border-top-color: #3c8dbc; */
/* 	border-top-width: 3px; */
/* } */
.padding5 {
	padding-right: 5px;
	padding-left: 5px;
}
</style>