<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<form:form class="form-horizontal" method="post" modelAttribute="order" name="f" id="f">
	<input type="HIDDEN" name="nextpage" value="${nextpage}">
	<input type="HIDDEN" name="reasonType" value="${reasonType}">
	<input type="HIDDEN" name="reasonId" value="${reasonId}">
	<input type="HIDDEN" name="refType" value="${refType}">
	<input type="HIDDEN" name="refId" value="${refId}">

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3>${language.getText("PURCHASE_ORDER")}</h3>
		</div>
		
		<div class="box-body">
		
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 col-sm-4 control-label">${language.getText("VENDOR")} </label>
					<div class="col-md-8  col-sm-8">
						<input type="hidden" name="vendorId" id="vendorId" value="${vendor.id}" /> ${vendor.caption}
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 col-sm-4 control-label">${language.getText("PURCHASE_ORGANIZATION")} </label>
					<div class="col-md-8  col-sm-8">
						<input type="hidden" name="organizationId" id="organizationId" value="${order.organization.id}"/> ${order.organization.caption}
					</div>
				</div>
			</div>

			<div class="col-md-12">
				<div class="form-group">
					<label for="description" class="col-md-2 col-sm-2 control-label">${language.getText("DESCRIPTION")} </label>
					<div class="col-md-10  col-sm-10">
						<form:input type="text" class="form-control" path="description" />
					</div>
				</div>
			</div>

			<div class="col-md-12">
				<div class="form-group">
					<label for="address" class="col-md-2 col-sm-2 control-label">${language.getText("DELIVERY_ADDRESS")} </label>
					<div class="col-md-10  col-sm-10">
						<ncaTags:select tagName="addressId" tagOptions="${addresses}" defvalue="1" notnull="required" />
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="form-group">
					<label for="dueDate" class="col-md-6 col-sm-6 control-label">${language.getText("DUE_DATE")} </label>
					<div class="col-md-6  col-sm-6">
						<input type="text" class="form-control pull-right date required" placeholder="DD/MM/YYYY" name="dueDate" id="dueDate" value="<fmt:formatDate value="${order.dueDate}" pattern="dd-MM-yyyy" />" />
					</div>
				</div>
			</div>

			<div class="col-md-3">
				<div class="form-group">
					<label for="discount" class="col-md-3 col-sm-3 control-label">${language.getText("DISCOUNT")} </label>
					<div class="col-md-9  col-sm-9">
						<input type="text" class="form-control currency" name="discount" id="discount" value="${order.discount}" readonly />
					</div>
				</div>
			</div>

			<div class="col-md-5">
				<div class="form-group">
					<label for="totalPrice" class="col-md-3 col-sm-3 control-label">${language.getText("TOTAL_PRICE")} </label>
					<div class="col-md-9 col-sm-9">
						<input type="text" class="form-control" name="totalPrice" id="totalPrice" value="${totalPrice}" readonly />
					</div>
				</div>
			</div>

		</div>
	</div>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('PURCHASE_ORDER_ITEMS')}</h3>
		</div>
		
		<div class="box-body">

			<table class="table table-condensed" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>${language.getText("LINE")}</th>
						<th>${language.getText("MATERIAL")}</th>
						<th>${language.getText("MANUFACTURER")}</th>
						<th>${language.getText("PART_NUMBER")}</th>
						<th>${language.getText("QUANTITY")}</th>
						<th>${language.getText("UNIT")}</th>
						<th>${language.getText("UNIT_PRICE")}</th>
						<th>${language.getText("TAX")}</th>
						<th>${language.getText("TOTAL")}</th>
						<th>${language.getText("CURRENCY")}</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="item" items="${items}" varStatus="ind">
						<c:set var="maxline" value="${ind.index+1}"></c:set>
						<tr>
							<td>${maxline}</td>
							<td>${item.material.caption} <input type="hidden" name="materialId${maxline}" id="materialId${maxline}" value="${item.material.id}" /></td>
							<td>${item.material.manufacturer.caption}</td>
							<td>${item.material.partNumber}</td>
							<td><input type="text" class="form-control required" name="quantity${maxline}" id="quantity${maxline}" value="${item.quantity}" readonly /></td>
							<td><input type="text" class="form-control required" name="unit${maxline}" id="unit${maxline}" value="${item.unit}" readonly /></td>
							<td><input type="text" class="form-control required currency" name="unitPrice${maxline}" id="unitPrice${maxline}"  value="${item.unitPrice}" readonly /></td>
							<td>
							<c:set var="taxCnt" value="0"></c:set>
							<c:set var="taxAmt" value="0"></c:set>
							<c:forEach var="tax" items="${item.purchaseOrderTaxes}" varStatus="tind">
								<c:set var="taxCnt" value="${tind.index+1}"></c:set>
								<input type="hidden" name="taxType${maxline}_${taxCnt}" id="taxType${maxline}_${taxCnt}"  value="${tax.id.taxId}" />
								<input type="text" class="form-control required currency" name="taxAmt${maxline}_${taxCnt}" id="taxAmt${maxline}_${taxCnt}"  value="${tax.amount}" readonly />
								<c:set var="taxAmt" value="${taxAmt + tax.amount}"></c:set>
							</c:forEach>
								<input type="hidden" name="taxCnt${maxline}" id="taxCnt${maxline}"  value="${taxCnt}" />
							</td>
							<td><input type="text" class="form-control required currency" name="total${maxline}" id="total${maxline}"  value="${item.quantity*item.unitPrice+taxAmt}" readonly /></td>
							<td><input type="text" class="form-control required" name="currency${maxline}" id="currency${maxline}" value="${item.currency}" readonly /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="hidden" name="maxline" id="maxline" value="${maxline}" />
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="if (checkDate(document.f.dueDate.value)) {doAjaxPost('${postlink}');}" class="btn btn-primary">${language.getIconText("SAVE")}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

</form:form>

<script src="js/nauticana.style.js"></script>

<script>
function checkDate(eop) {
	var today = new Date();
	today.setHours(0,0,0,0);
	var endofP = new Date(eop);

	if (today > endofP) {
		alert("Inproper dates, must be today <= due date");
		return false;
	}
	return true;
}

function updateHtml(img,tmb) {
//	var btnTrash = $('a.btn-danger').get( -1 );
	var btnTrash =$('<a href="#" onclick="" class="btn btn-danger btn-md pull-left"><i class="fa fa-trash"> Sil </i></a>');
	var newDiv = $('<tr role="row" class="odd"> '+
			'<td><a href="#" onclick="" class="btn btn-danger btn-sm pull-left"><i class="fa fa-trash"> Sil </i></a></td> '+
			'<td>'+ $('#caption').val()+'</td> '+
			'<td><a target="_blank" class="pull-left" href="'+ img +'"><img src="' + tmb + '" alt="'+ $('#caption').val()+'"> </a></td></tr>');
			
	debugger;

	$('tbody').prepend(newDiv)	
}

function deleteImg(objType, id, contId){

	var row  = $('tr[data-id="' + contId + '"]');
	var response = 	doAjaxDelete('contentRelation/delWithObj/' + objType + '/'+ id + '/' + contId, row  ) ;

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

</script>
