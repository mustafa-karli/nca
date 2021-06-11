<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<form:form class="form-horizontal" method="post" modelAttribute="record" name="f" id="f">
	<input type="HIDDEN" name="nextpage" value="${prevpage}">
	<input type="HIDDEN" name="rfpId" value="${rfp.id}">
	<form:input type="hidden" path="id" />
	<form:input type="hidden" path="ownerId" />
	<form:input type="hidden" path="purchaseOrderId" />
	<form:input type="hidden" path="extraDiscount" />
	<form:input type="hidden" path="currency" />

	<div class="box box-primary">
		<div class="box-header with-border">
			<h2>${rfp.caption}</h2>
			<p>
				<b>${language.getText("REQUEST_DATE")}:</b> <fmt:formatDate value="${rfp.requestDate}" pattern="dd MMMM yyyy" /> &nbsp;
				<b>${language.getText("END_OF_PROPOSAL")}:</b> <fmt:formatDate value="${rfp.endOfProposal}" pattern="dd MMMM yyyy" /> &nbsp;
				<b>${language.getText("DELIVERY_DATE")}:</b> <fmt:formatDate value="${rfp.deliveryDate}" pattern="dd MMMM yyyy" /> &nbsp;
				<b> ${language.getText("DELIVERY_ADDRESS")}: </b> ${rfp.deliveryAddress.street} ${rfp.deliveryAddress.city} ${rfp.deliveryAddress.state} ${rfp.deliveryAddress.country} ${rfp.deliveryAddress.phone} &nbsp;
				<c:if test="${rfp.consortiumAllowed == 'Y'}"> &nbsp; ${language.getText("CONSORTIUM_ALLOWED")} &nbsp; </c:if>
				<c:if test="${rfp.partialAllowed == 'Y'}"> &nbsp; ${language.getText("PARTIAL_ALLOWED")} &nbsp; </c:if>
			</p>
			<h2 class="box-title">${language.getText('PROPOSAL_TO_RFP')}</h2>
		</div>

		<div class="box-body">

			<div class="col-md-12">
				<div class="form-group">
					<label for="description" class="col-md-2 col-sm-2 control-label">${language.getText("DESCRIPTION")} </label>
					<div class="col-md-10  col-sm-10">
						<form:input class="form-control required" path="description" required="true" />
					</div>
				</div>
			</div>

			<div class="col-md-6">

				<div class="col-md-12">
					<div class="form-group">
						<label for="validUntil" class="col-md-4 col-sm-4 control-label">${language.getText("VALID_UNTIL")} </label>
						<div class="col-md-8 col-sm-8">
							<form:input class="form-control required date" placeholder="DD/MM/YYYY" path="validUntil" required="true" />
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="form-group">
						<label for="shipmentBy" class="col-md-4 col-sm-4 control-label">${language.getText("SHIPMENT_BY")} </label>
						<div class="col-md-8  col-sm-8">
							<ncaTags:radio tagName="shipmentBy" tagOptions="${SHIPMENT_BY}" defvalue="${record.shipmentBy}"/>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="form-group">
						<label for="paymentType" class="col-md-4 col-sm-4 control-label">${language.getText("PAYMENT_TYPE")} </label>
						<div class="col-md-8  col-sm-8">
							<ncaTags:radio tagName="paymentType" tagOptions="${PAYMENT_TYPE}" defvalue="C"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
			
				<div class="col-md-12">
					<div class="form-group">
						<label for="itemTotal" class="col-md-4 col-sm-4 control-label">${language.getText("ITEM_TOTAL")} </label>
						<div class="col-md-8  col-sm-8">
							<input type="text" class="form-control" name="itemTotal" id="itemTotal" readonly />
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label for="taxTotal" class="col-md-4 col-sm-4 control-label">${language.getText("TAX_TOTAL")} </label>
						<div class="col-md-8  col-sm-8">
							<input type="text" class="form-control" name="taxTotal" id="taxTotal" readonly />
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label for="totalPrice" class="col-md-4 col-sm-4 control-label">${language.getText("TOTAL_PRICE")} </label>
						<div class="col-md-8  col-sm-8">
							<form:input type="text" class="form-control" path="totalPrice" readonly="true" />
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-12">
				<div class="form-group">
					<label for="paymentNote" class="col-md-2 col-sm-2 control-label">${language.getText("PAYMENT_NOTE")} </label>
					<div class="col-md-10  col-sm-10">
						<form:input class="form-control" path="paymentNote" />
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<label for="deliveryNote" class="col-md-2 col-sm-2 control-label">${language.getText("DELIVERY_NOTE")} </label>
					<div class="col-md-10  col-sm-10">
						<form:input class="form-control" path="deliveryNote" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('MATERIAL')}</h3>
		</div>

		<div class="box-body table-responsive no-padding">

			<table class="table table-condensed" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th rowspan=2>${language.getText("LINE")}</th>
						<th colspan=5>${language.getText("REQUESTED_MATERIAL")}</th>
						<th colspan=6>${language.getText("PROPOSAL")}</th>
					</tr>
					<tr>
						<th>${language.getText("MATERIAL_TYPE")}</th>
						<th>${language.getText("MATERIAL_SPECIFICATION")}</th>
						<th>${language.getText("BRAND")}</th>
						<th>${language.getText("QUANTITY")}</th>
						<th>${language.getText("UNIT")}</th>
						<th>${language.getText("UNIT_PRICE")}</th>
						<th>${language.getText("DISCOUNT")} %</th>
						<th>${language.getText("TAX_PCT")}</th>
						<th>${language.getText("FINAL_PRICE")}</th>
						<th>${language.getText("CURRENCY")}</th>
					</tr>
				</thead>

				<tbody>
					<c:set var="maxline" value="0"></c:set>
					<c:forEach var="item" items="${record.proposalToRfpItems}" varStatus="status">
						<tr>
							<td>${item.id.line}</td>
							<td>${item.materialType.caption} <input type="hidden" name="materialTypeId${item.id.line}" id="materialTypeId${item.id.line}" value="${item.materialType.id}" /> </td>
							<td><c:choose>
								<c:when test="${empty item.material}">
									<input type="text" name="caption${item.id.line}" id="caption${item.id.line}" class="form-control" value="${item.requestForProposalItem.specification}"/>
									<input type="hidden" name="materialId${item.id.line}" id="materialId${item.id.line}" value="0" />
								</c:when>
								<c:otherwise>
									${item.material.caption}
									<input type="hidden" name="caption${item.id.line}" id="caption${item.id.line}" value="${item.material.caption}" />
									<input type="hidden" name="materialId${item.id.line}" id="materialId${item.id.line}" value="${item.material.id}" />
								</c:otherwise>
								</c:choose>
							</td>
							<td><c:choose>
								<c:when test="${empty item.requestForProposalItem.manufacturer}">
									<input type="text" name="manufacturer${item.id.line}" id="manufacturer${item.id.line}" class="form-control" />
								</c:when>
								<c:otherwise>
									${item.requestForProposalItem.manufacturer.caption} <input type="hidden" name="manufacturer${item.id.line}" id="manufacturer${item.id.line}" value="${item.requestForProposalItem.manufacturer.id}" />
								</c:otherwise>
								</c:choose>
							</td>
							<td>${item.quantity} <input type="hidden" name="quantity${item.id.line}" id="quantity${item.id.line}" value="${item.quantity}" /></td>
							<td>${item.unit} <input type="hidden" name="unit${item.id.line}" id="qunit${item.id.line}" value="${item.unit}" /></td>
							<td><input type="text" name="unitPrice${item.id.line}" id="unitPrice${item.id.line}" value="${item.unitPrice}" class="form-control currency" onChange="getTotalPrice();" /></td>
							<td><input type="text" name="discountPct${item.id.line}" id="discountPct${item.id.line}" value="${item.discountPct}" class="form-control percentage" onChange="getTotalPrice();" value="0" /></td>
							<td><input type="text" name="taxPct${item.id.line}" id="taxPct${item.id.line}" value="${item.taxPct}" class="form-control percentage" onChange="getTotalPrice();" value="0" /></td>
							<td><input type="text" name="finalPrice${item.id.line}" id="finalPrice${item.id.line}" class="form-control currency" readonly /></td>
							<td><ncaTags:select tagName="currency${item.id.line}" tagOptions="${EXCHANGE}" defvalue="${item.currency}" notnull="required" onChange="getTotalPrice();" /></td>
							<c:if test="${item.id.line > maxline}">
								<c:set var="maxline" value="${item.id.line}"></c:set>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="hidden" name="maxline" id="maxline" value="${maxline}" />
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="if (checkDate(document.f.validUntil.value)) {doAjaxPost('${postlink}');}" class="btn btn-primary">${language.getIconText("SAVE")}</a> <a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

</form:form>

<script src="js/nauticana.style.js"></script>

<script>
function checkDate(vld) {
	var today = new Date();
	today.setHours(0,0,0,0);
	var ds = vld.split('-');
	if (ds.length != 3)
		ds = vld.split('/');
	var validU = new Date(ds[2],ds[1]-1,ds[0]);

	console.log(today);
	console.log(validU);
	console.log(today > validU);
	
	if (today > validU) {
		alert("Inproper dates, must be today <= valid until");
		return false;
	}
	return true;
}

function getTotalPrice() {
	var total = 0;
	var curr = new Array("TRY","EUR","USD","GBP","AUD","CAD");
	var tots = [0,0,0,0,0,0];
	var fins = [0,0,0,0,0,0];
	var taxs = [0,0,0,0,0,0];

	for(i=1;i<=document.f.maxline.value;i++) {
		var p=Number(document.getElementById('unitPrice'+i).value.replace(' ',''));
		var d=Number(document.getElementById('discountPct'+i).value.replace(' ',''));
		var t=Number(document.getElementById('taxPct'+i).value.replace(' ',''));
		var q=Number(document.getElementById('quantity'+i).value.replace(' ',''));
		var c=document.getElementById('currency'+i).value;
		var x=0;
		if (p>0 && q>0) {
			if (d > 0) {
				p = p - (p*d/100);
			}
			var f = p;
			if (t > 0) {
				t = t/100;
				console.log(t);
				x = p*t;
				console.log(x);
				f = p + x;
			}
			document.getElementById('finalPrice'+i).value=f;
			for(j=0;j<6;j++) {
				if (curr[j] == c) {
					tots[j] = tots[j] + (p*q);
					fins[j] = fins[j] + (f*q);
					taxs[j] = taxs[j] + (x*q);
				}
			}
		}
	}
	document.f.itemTotal.value='';
	document.f.taxTotal.value='';
	document.f.totalPrice.value='';
	for(j=0;j<6;j++) {
		if (tots[j] > 0) {
			var formatter = new Intl.NumberFormat('en-US', {
			  style: 'currency',
			  currency: curr[j],
			  minimumFractionDigits: 2
			});
			var fmt1 = formatter.format(tots[j]);
			var fmt2 = formatter.format(fins[j]-tots[j]);
			var fmt3 = formatter.format(fins[j]);
			
			document.f.itemTotal.value  = document.f.itemTotal.value  + fmt1 + ' ';
			document.f.taxTotal.value   = document.f.taxTotal.value   + fmt2 + ' ';
			document.f.totalPrice.value = document.f.totalPrice.value + fmt3 + ' ';

//			document.f.itemTotal.value  = document.f.itemTotal.value  + tots[j] + ' ' + curr[j] + ' ';
//			document.f.taxTotal.value   = document.f.taxTotal.value   + (fins[j]-tots[j]) + ' ' + curr[j] + ' ';
//			document.f.totalPrice.value = document.f.totalPrice.value + fins[j] + ' ' + curr[j] + ' ';
		}
	}
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
	getTotalPrice();
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
