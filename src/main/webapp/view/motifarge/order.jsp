<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="css/nauticana.table.css" rel="stylesheet" type="text/css" />

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${language.getText('ORDER')}</h3>
	</div>

	<div class="box-body">

			<div class="box box-primary">
				<div class="box-header with-border">
					<div class="col-md-8">
					</div>
					<div class="col-md-4">
					</div>
				</div>
				
				<div class="box-body">

				<div class="col-md-4">
					<h3 class="box-title">${language.getText('PRODUCT')}</h3>
					<table class="table">
						<tr> <th> ${language.getText("CAPTION")} </th> <td> ${record.material.caption} </td> </tr>
						<tr> <th> ${language.getText("PART_NUMBER")} </th> <td> ${record.material.partNumber} </td> </tr>
						<tr> <th> ${language.getText("ORDER_DEAD_LINE")} </th> <td> <fmt:formatDate value="${record.id.orderDeadLine}" pattern='dd MMMM yyyy'/> </td> </tr>
						<tr> <th> ${language.getText("DELIVERY_PROMISE")} </th> <td> <fmt:formatDate value="${record.deliveryPromise}" pattern='dd MMMM yyyy'/> </td> </tr>
						<tr> <th> ${language.getText("UNIT")} </th> <td> ${record.unit} </td> </tr>
					</table>
				</div>
				
				<div class="col-md-4">
					<h3 class="box-title">${language.getText('SALES')}</h3>
					<table class="table">
						<tr> <th> ${language.getText("MIN_QUANTITY")} </th> <td> ${record.minQuantity} </td> </tr>
						<tr> <th> ${language.getText("MAX_QUANTITY")} </th> <td> ${record.maxQuantity} </td> </tr>
						<tr> <th> ${language.getText("BOX_QUANTITY")} </th> <td> ${record.boxQuantity} </td> </tr>
						<tr> <th> ${language.getText("CURRENT_ORDER")} </th> <td> ${record.currentOrder} </td> </tr>
						<tr> <th> ${language.getText("START_PRICE")} </th> <td> ${record.startPrice} </td> </tr>
						<tr> <th> ${language.getText("CURRENT_PRICE")} </th> <td> ${record.currentPrice} </td> </tr>
						<tr> <th> ${language.getText("CURRENCY")} </th> <td> ${record.currency} </td> </tr>
					</table>
				</div>
				
				<div class="col-md-4">
					<h3 class="box-title">${language.getText('DISCOUNT_STEPS')}</h3>
					<table class="table">
						<tr> <th> ${language.getText("QUANTITY")} </th> <th> ${language.getText("PRICE")} </th> </tr>
						<c:forEach var="dc" items="${record.productPriceCommitmentItems}" varStatus="dcnt">
							<tr> <td> ${dc.id.quantity} </td> <td> ${dc.price} </td> </tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<form:form class="form-horizontal" method="post" modelAttribute="record" id="f" name="f">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">${language.getText('ORDER')}</h3>
				</div>

				<div class="box-body">
					<input type="HIDDEN" name="nextpage" value="${prevpage}">
					<input type="HIDDEN" name="materialId" id="materialId" value="${record.id.materialId}">
					<input type="HIDDEN" name="businessPartnerId" id="businessPartnerId" value="${record.id.businessPartnerId}">
					<input type="HIDDEN" name="orderDeadLine" id="orderDeadLine" value="<fmt:formatDate value="${record.id.orderDeadLine}" pattern='dd-MM-yyyy'/>">
					<input type="HIDDEN" name="dueDate" id="dueDate" value="<fmt:formatDate value="${record.deliveryPromise}" pattern='dd-MM-yyyy'/>">
					<input type="HIDDEN" name="unitPrice" id="unitPrice" value="${record.startPrice}">
					<input type="HIDDEN" name="unit" id="unit" value="EA">
					<input type="HIDDEN" name="currency" id="currency" value="${record.currency}">
					<input type="HIDDEN" name="maxQuantity" id="maxQuantity" value="${record.maxQuantity}">
					<input type="HIDDEN" name="boxQuantity" id="boxQuantity" value="${record.boxQuantity}">
				
					<div class="col-md-6">
						<div class="form-group">
							<label for="quantity" class="col-md-4 col-sm-4 control-label">${language.getText("QUANTITY")}</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="quantity" id="quantity" class="form-control integer" />
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label for="description" class="col-md-2 col-sm-2 control-label">${language.getText("DESCRIPTION")}</label>
							<div class="col-md-10 col-sm-10">
								<input type="text" name="description" id="description" class="form-control" />
							</div>
						</div>
					</div>

				</div>
				
			</div>

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">${language.getText('DELIVERY_ADDRESS')}</h3>
				</div>

				<div class="box-body">
					<div class="col-md-6">
						<div class="form-group">
							<label for="address" class="col-md-4 col-sm-4 control-label">${language.getText("ADDRESS")}</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="address" id="address" class="form-control" />
							</div>
						</div>
					</div>

					<div class="col-md-3">
						<div class="form-group">
							<label for="city" class="col-md-4 col-sm-4 control-label">${language.getText("CITY")}</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="city" id="city" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<label for="state" class="col-md-6 col-sm-6 control-label">${language.getText("STATE")}</label>
							<div class="col-md-6  col-sm-6">
								<input type="text" name="state" id="state" class="form-control" />
							</div>
						</div>
					</div>

					<div class="col-md-2">
						<div class="form-group">
							<label for="country" class="col-md-4 col-sm-4 control-label">${language.getText("COUNTRY")}</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="country" id="country" class="form-control" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">${language.getText('CREDIT_CARD')}</h3>
				</div>

				<div class="box-body">
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="cardNumber" class="col-md-4 col-sm-4 control-label">${language.getText("CARD_NUMBER")}</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="cardNumber" id="cardNumber" value="${card.id}" class="form-control card" />
							</div>
						</div>
					</div>
					
					<div class="col-md-2">
						<div class="form-group">
							<label for="cvc2" class="col-md-6 col-sm-6 control-label">${language.getText("CVC2")}</label>
							<div class="col-md-6  col-sm-6">
								<input type="text" name="cvc2" id="cvc2" value="${card.cvc2}" class="form-control integer" />
							</div>
						</div>
					</div>
					
					<div class="col-md-2">
						<div class="form-group">
							<label for="expireMonth" class="col-md-6 col-sm-6 control-label">${language.getText("EXPIRE_MONTH")}</label>
							<div class="col-md-6  col-sm-6">
								<input type="text" name="expireMonth" id="expireMonth" value="${card.expireMonth}" class="form-control month" />
							</div>
						</div>
					</div>

					<div class="col-md-2">
						<div class="form-group">
							<label for="expireYear" class="col-md-6 col-sm-6 control-label">${language.getText("EXPIRE_YEAR")}</label>
							<div class="col-md-6  col-sm-6">
								<input type="text" name="expireYear" id="expireYear" value="${card.expireYear}" class="form-control year" />
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="nameOnCard" class="col-md-4 col-sm-4 control-label">${language.getText("NAME_ON_CARD")}</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="nameOnCard" id="nameOnCard" value="${card.nameOnCard}" class="form-control" />
							</div>
						</div>
					</div>
				</div>
			</div>
			</form:form>

		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="if (checkBoxQty()>0) doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a> <a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

</div>

<script type="text/javascript" src="js/dataTables.style.js">
</script>
<script>
function checkBoxQty(){
  var bs = document.f.boxQuantity.value;
  var os = document.f.quantity.value;
  var ps = os/bs;
  var pi = Math.trunc(ps);
  if (pi != ps)
  {
	alert("Order quantity " + os + " must be multiplies of box quantity " + bs);
	return 0;
  }
  return 1;
};

$(document).ready(function() {
	$('.required').attr({ 'placeholder' : '${REQUIRED}' });
	$(".select2").select2();
	$('.date').datepicker({
		autoclose : true,
		format : 'dd-mm-yyyy'
	});
});
console.log(navigator.userAgent);
$(".integer").inputmask( {
	alias: 'integer',		
	groupSeparator: ' ',
	autoGroup: true, 
	digits: 0, 
	digitsOptional: true,
	rightAlign: false
});
$(".decimal").inputmask({
	alias : 'decimal',
	groupSeparator : ' ',
	autoGroup : true,
	digits : 2,
	digitsOptional : true,
	rightAlign : false
});
$(".currency").inputmask({
	alias : 'decimal',
	groupSeparator : ' ',
	autoGroup : true,
	digits : 2,
	digitsOptional : true,
	rightAlign : false
});
$(".percentage").inputmask({
	alias : 'numeric',
	autoGroup : false,
	integerDigits : 3,
	digits : 2,
	min : 0,
	max : 100,
	digitsOptional : true,
	rightAlign : false
});
$(".year").inputmask({
	alias : 'numeric',
	autoGroup : false,
	integerDigits : 4,
	min : 2019,
	max : 2040,
	digits : 0,
	rightAlign : false
});
$(".month").inputmask({
	alias : 'numeric',
	autoGroup : false,
	integerDigits : 2,
	min : 1,
	max : 12,
	digits : 0,
	rightAlign : false
});
$(".card").inputmask({
	alias : 'numeric',
	integerDigits : 20,
	digits : 0,
	rightAlign : false
});

</script>
