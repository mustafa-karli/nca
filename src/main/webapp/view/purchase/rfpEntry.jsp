<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<form:form class="form-horizontal" method="post" modelAttribute="record" name="f" id="f">
	<input type="HIDDEN" name="nextpage" value="${prevpage}">
	<c:set var="addressId" value="1"></c:set>
	<c:if test="${!empty record.deliveryAddress}">
		<c:set var="addressId" value="${record.deliveryAddress.id.addressId}"></c:set>
	</c:if>

	<div class="panel panel-primary">
		<div class="panel-header with-border">
			<h3 class="panel-title">${language.getText('REQUEST_FOR_PROPOSAL')}</h3>
			<form:input type="hidden" path="id" />
			
		</div>
		<div class="panel-body">
			<div class="col-md-12">
				<div class="form-group">
					<label for="caption" class="col-md-2 col-sm-2 control-label">${language.getText("CAPTION")} </label>
					<div class="col-md-10  col-sm-10">
						<form:input type="text" class="form-control required" path="caption" />
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<label for="deliveryAddressId" class="col-md-2 col-sm-2 control-label">${language.getText("DELIVERY_ADDRESS")} </label>
					<div class="col-md-10  col-sm-10">
						<ncaTags:select tagName="deliveryAddressId" tagOptions="${PARTNER_ADDRESS}" defvalue="${addressId}" notnull="required" />
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<form:input type="hidden" class="form-control required" path="purchaseArea" onChange="resetSelectOptions('materialType', 'selectOptions?set=MATERIAL_TYPE&client=' + document.f.purchaseArea.value, 'required');" />
				<label class="col-md-4  col-sm-4" for="purchaseAreaTx"> ${language.getText('PURCHASE_AREA')} </label>
				<div class="dropdown col-md-8  col-sm-58">
					<c:if test="${empty purchaseAreaTx}"> <c:set var="purchaseAreaTx" value="Select purchase area"></c:set> </c:if>
					<a data-toggle="dropdown" href="#" id="purchaseAreaTx"> ${purchaseAreaTx} <span class=caret></span> </a>
					<ul class="dropdown-menu">
						<ncaTags:selectTree tree="${businessTree}" elemId="document.f.purchaseArea" elemTx="purchaseAreaTx" language="${language}" />
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="endOfProposal" class="col-md-4 col-sm-4 control-label">${language.getText("END_OF_PROPOSAL")} </label>
					<div class="col-md-8  col-sm-8">
						<div class="input-group">
							<form:input type="text" class="form-control date required" placeholder="DD/MM/YYYY" path="endOfProposal" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="deliveryDate" class="col-md-4 col-sm-4 control-label">${language.getText("DELIVERY_DATE")} </label>
					<div class="col-md-8  col-sm-8">
						<div class="input-group">
							<form:input type="text" class="form-control date required" placeholder="DD/MM/YYYY" path="deliveryDate" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="consortiumAllowed" class="col-md-4 col-sm-4 control-label">${language.getText("CONSORTIUM_ALLOWED")} </label>
					<div class="col-md-8  col-sm-8">
						<ncaTags:radio tagName="consortiumAllowed" tagOptions="${YESNO}" defvalue="${record.consortiumAllowed}" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="partialAllowed" class="col-md-4 col-sm-4 control-label">${language.getText("PARTIAL_ALLOWED")} </label>
					<div class="col-md-8  col-sm-8">
						<ncaTags:radio tagName="partialAllowed" tagOptions="${YESNO}" defvalue="${record.partialAllowed}" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-primary">
		<div class="panel-header with-border">
			<h3 class="panel-title">${language.getText('MATERIAL')}</h3>
			<c:set var="itemCount" value="0"></c:set>
		</div>

		<div class="panel-body">

			<div class="col-md-4">
				<div class="form-group">
					<label for="materialType" class="col-md-4 col-sm-4 control-label">${language.getText("MATERIAL_TYPE")} </label>
					<div class="col-md-8 col-sm-8">
						<select name="materialType" id="materialType" class="form-control" > </select>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="form-group">
					<label for="specification" class="col-md-4 col-sm-4 control-label">${language.getText("PRODUCT_PROPERTY")} </label>
					<div class="col-md-8 col-sm-8">
						<input type="text" name="specification" id="specification" class="form-control" />
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="form-group">
					<label for="manufacturer" class="col-md-4 col-sm-4 control-label">${language.getText("BRAND")} </label>
					<div class="col-md-8 col-sm-8">
						<input type="text" name="manufacturer" id="manufacturer" class="form-control" />
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="form-group">
					<label for="quantity" class="col-md-4 col-sm-4 control-label">${language.getText("QUANTITY")} </label>
					<div class="col-md-8 col-sm-8">
						<input type="text" name="quantity" id="quantity" class="form-control" />
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="form-group">
					<label for="unit" class="col-md-4 col-sm-4 control-label">${language.getText("UNIT")} </label>
					<div class="col-md-8 col-sm-8">
						<ncaTags:select tagName="unit" tagOptions="${MEASUREMENT_UNIT}" defvalue="EA" notnull="required" />
					</div>
				</div>
			</div>

			<br> <a class="btn btn-primary" href="#" type="btn" onClick="addRfpItem();"> ${language.getIconText("ADD")}</a> <br> <br>

			<table id="treeTab" class="simple-table">
				<thead>
				<tr>
					<th>${language.getText("LINE")}</th>
					<th>${language.getText("MATERIAL_TYPE")}</th>
					<th>${language.getText("PRODUCT_PROPERTY")}</th>
					<th>${language.getText("BRAND")}</th>
					<th>${language.getText("QUANTITY")}</th>
					<th>${language.getText("UNIT")}</th>
					<th> &nbsp; </th>
				</tr>
				</thead>
				<c:if test="${!empty record.requestForProposalItems}">
				<tbody>
					<c:forEach var="item" items="${record.requestForProposalItems}" varStatus="status">
						<tr>
							<td>${item.id.line}</td>
							<td> <c:if test="${!emptyitem.materialType}"> ${item.materialType.caption} </c:if> </td>
							<td>${item.specification}</td>
							<td> <c:if test="${!emptyitem.manufacturer}"> ${item.manufacturer.caption} </c:if> </td>
							<td>${item.quantity}</td>
							<td>${item.unit}</td>
							<td>&nbsp;</td>
						</tr>
						<c:set var="itemCount" value="${item.id.line}"></c:set>
					</c:forEach>
				</tbody>
				</c:if>
			</table>

		</div>
	</div>

	<div class="panel-footer">
		<input type="hidden" name="lastLine" id="lastLine" class="form-control" value="${itemCount}" />
		<input type="hidden" name="itemCount" id="itemCount" class="form-control" value="${itemCount}" />
		<a href="#" onclick="if (checkDate(document.f.endOfProposal.value,document.f.deliveryDate.value)) {doAjaxPost('${postlink}');}" class="btn btn-primary">${language.getIconText("SAVE")}</a> <a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

</form:form>

<script>
function checkDate(eop, dlv) {
	var today = new Date();
	today.setHours(0,0,0,0);
	var es = eop.split('-');
	if (es.length != 3)
		es = eop.split('/');
	var endofP = new Date(es[2], es[1]-1, es[0]);
	var ds = dlv.split('-');
	if (ds.length != 3)
		ds = dlv.split('/');
	var delivD = new Date(ds[2], ds[1]-1, ds[0]);

	console.log(today);
	console.log(endofP);
	console.log(delivD);
	console.log(today > endofP);
	console.log(today > delivD);
	console.log(endofP > delivD);

	if (today > endofP || today > delivD || endofP > delivD) {
		alert("Inproper dates, must be today <= end of proposal <= delivery date");
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

}

function addRfpItem() {
	var counter = document.f.itemCount.value;
	counter++;
	var r1 = '<input type="hidden" name="materialType'  + counter + '" id="materialType'  + counter + '" value="' + document.f.materialType.value  + '" />' + document.f.materialType.value;
	var r2 = '<input type="hidden" name="specification' + counter + '" id="specification' + counter + '" value="' + document.f.specification.value + '" />' + document.f.specification.value;
	var r3 = '<input type="hidden" name="manufacturer'  + counter + '" id="manufacturer'  + counter + '" value="' + document.f.manufacturer.value  + '" />' + document.f.manufacturer.value;
	var r4 = '<input type="hidden" name="quantity'      + counter + '" id="quantity'      + counter + '" value="' + document.f.quantity.value      + '" />' + document.f.quantity.value;
	var r5 = '<input type="hidden" name="unit'          + counter + '" id="unit'          + counter + '" value="' + document.f.unit.value          + '" />' + document.f.unit.value;
	var r6 = '&nbsp;';
	var t = document.getElementById('treeTab');
	var r = t.insertRow(counter);
	var c0 = r.insertCell(0);
	var c1 = r.insertCell(1);
	var c2 = r.insertCell(2);
	var c3 = r.insertCell(3);
	var c4 = r.insertCell(4);
	var c5 = r.insertCell(5);
	var c6 = r.insertCell(6);

	c0.innerHTML = counter;
	c1.innerHTML = r1;
	c2.innerHTML = r2;
	c3.innerHTML = r3;
	c4.innerHTML = r4;
	c5.innerHTML = r5;
	c6.innerHTML = r6;
	document.f.itemCount.value = counter;
}

$(document).ready(function() {
	$('.required').attr({ 'placeholder' : '${REQUIRED}' });
	$(".select2").select2();
	$('.date').datepicker({
		autoclose : true,
		useCurrent: false,
		uiLibrary: 'bootstrap',
		format : 'dd-mm-yyyy'
	});
	if (document.forms.f.elements.purchaseArea.value > 0) {
		document.forms.f.elements.purchaseArea.onchange();
	}
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

<script type="text/javascript" src="js/dataTables.style.js"></script>
