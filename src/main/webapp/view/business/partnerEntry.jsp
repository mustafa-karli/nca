<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<link href="css/nauticana.table.css" rel="stylesheet" type="text/css" />
    
<c:if test="${!empty errorText}">
	<p>
		<a href="#" class="btn btn-danger"> ERROR from server: ${errorText} </a>
	</p>
</c:if>

<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="HIDDEN" name="nextpage" value="${prevpage}">
	<c:if test="${!empty newPerson}">
		<input type="HIDDEN" name="newPerson" value="X">
	</c:if>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('BUSINESS_PARTNER')}</h3>
		</div>

		<div class="box-body">
			<div class="col-md-8">
				<form:input type="HIDDEN" path="id" />
				
				<div class="col-md-6">
					<div class="form-group">
						<label for="caption" class="col-md-4 col-sm-4 control-label">${language.getText("CAPTION")} </label>
						<div class="col-md-8  col-sm-8">
							<form:input type="text" class="form-control required" path="caption" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="webAddress" class="col-md-4 col-sm-4 control-label">${language.getText("WEB_ADDRESS")} </label>
						<div class="col-md-8  col-sm-8">
							HTTP://<form:input type="text" class="form-control required" path="webAddress" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="hqCountry" class="col-md-4 col-sm-4 control-label">${language.getText("HQ_COUNTRY")} </label>
						<div class="col-md-8  col-sm-8">
							<ncaTags:select tagName="hqCountry" tagOptions="${countries}" defvalue="${record.hqCountry}" notnull="required" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="taxCenter" class="col-md-4 col-sm-4 control-label">${language.getText("TAX_CENTER")} </label>
						<div class="col-md-8  col-sm-8">
							<form:input type="text" class="form-control required" path="taxCenter" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="taxNumber" class="col-md-4 col-sm-4 control-label">${language.getText("TAX_NUMBER")} </label>
						<div class="col-md-8  col-sm-8">
							<form:input type="text" class="form-control required" path="taxNumber" />
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<c:if test="${!empty binaryContent}">
					<c:forEach items="${binaryContent}" var="cfile" varStatus="fCount">
						<a target="_blank" href="contentRelation/read/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}"> <img src="contentRelation/thumb/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}" alt="${cfile.caption}"></a>
						<br>
						<br>
					</c:forEach>
				</c:if>

			</div>
		</div>
	</div>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('PARTNER_ROLES')}</h3>
		</div>
		<div class="box-body">

			<div class="col-md-6 col-sm-6">
				<label> <input type="checkbox" name="isBusinessOwner" id="isBusinessOwner" value="X" <c:if test="${!empty dates[4]}"> checked=true </c:if>> ${language.getText("BUSINESS_OWNER")} </label> <br> 
				<label> <input type="checkbox" name="isSubcontractor" id="isSubcontractor" value="X" <c:if test="${!empty dates[5]}"> checked=true </c:if>> ${language.getText("SUBCONTRACTOR")}</label> <br>
				<label> <input type="checkbox" name="isVendor" id="isVendor" value="X" <c:if test="${!empty dates[6]}"> checked=true </c:if>> ${language.getText("VENDOR")}</label> <br>
				<label> <input type="checkbox" name="isCustomer" id="isCustomer" value="X" <c:if test="${!empty dates[7]}"> checked=true </c:if>> ${language.getText("CUSTOMER")}</label> <br>
			</div>

			<c:if test="${!empty businessTree}">
				<div class="col-md-6  col-sm-6">
					<input type="hidden" class="form-control" name="vendorBusinessId" id="vendorBusinessId" onchange="addvb();" />
					<input type="hidden" class="form-control" name="vendorBusinessCount" id="vendorBusinessCount" />
					<div class="dropdown col-md-12 col-sm-12">
						<c:if test="${empty vendorBusinessTx}"> <c:set var="vendorBusinessTx" value="Select business Area"></c:set> </c:if>
						<a data-toggle="dropdown" href="#" id="vendorBusinessTx">${vendorBusinessTx}</a>
						<ul class="dropdown-menu">
							<ncaTags:selectTree tree="${businessTree}" elemId="vendorBusinessId" elemTx="vendorBusinessTx" language="${language}" />
						</ul>
					</div>
					<table class="table" id="vbTab">
						<tr>
							<th>${language.getText('VENDOR_BUSINESS')}</th>
						</tr>
						<c:forEach items="${vendorBusiness}" var="vb" varStatus="fCount">
						<tr>
							<td>${vb[0]}</td>
							<td>${vb[1]}</td>
						</tr>
						</c:forEach>
						<c:forEach items="${newVendorBusiness}" var="vb" varStatus="fCount">
						<tr>
							<td>${vb[0]}</td>
							<td>${vb[1]}</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>

	</div>

	<c:if test="${!empty newPerson}">
		<div class="box box-primary">
			<ncaTags:personElements title="${language.getText('FIRST')} ${language.getText('PERSON')}" veu="${veu}" language="${language}" />
		</div>
	</c:if>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('BANK_ACCOUNT')}</h3>
		</div>

		<div class="box-body">
			<table class="table">
				<thead>
					<tr>
						<th>${language.getText("CURRENCY")}</th>
						<th>${language.getText("IBAN")}</th>
						<th>${language.getText("SWIFT")}</th>
						<th>${language.getText("ACCOUNT_NO")}</th>
						<th>${language.getText("ACCOUNT_TYPE")}</th>
					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty accounts}">
						<c:forEach var="account" items="${accounts}" varStatus="status">
							<tr>
								<td>${account.currency}</td>
								<td><a href="#" onClick="doAjaxGet('bankAccount/edit?id=${account.id}');"> ${account.id} </a></td>
								<td>${account.bankBranch.id}</td>
								<td>${account.accountNo}</td>
								<td>${account.accountType}</td>
							</tr>
						</c:forEach>
					</c:if>

					<tr>
						<td><input type="text" class="form-control" name="currency1" id="currency1" value="TRY" /></td>
						<td><input type="text" class="form-control" name="iban1" id="iban1" /></td>
						<td><input type="text" class="form-control" name="swift1" id="swift1" /></td>
						<td><input type="text" class="form-control" name="accountNo1" id="accountNo1" /></td>
						<td><input type="text" class="form-control" name="accountType1" id="accountType1" /></td>
					</tr>

					<tr>
						<td><input type="text" class="form-control" name="currency2" id="currency2" value="USD" /></td>
						<td><input type="text" class="form-control" name="iban2" id="iban2" /></td>
						<td><input type="text" class="form-control" name="swift2" id="swift2" /></td>
						<td><input type="text" class="form-control" name="accountNo2" id="accountNo2" /></td>
						<td><input type="text" class="form-control" name="accountType2" id="accountType2" /></td>
					</tr>

					<tr>
						<td><input type="text" class="form-control" name="currency3" id="currency3" value="EUR" /></td>
						<td><input type="text" class="form-control" name="iban3" id="iban3" /></td>
						<td><input type="text" class="form-control" name="swift3" id="swift3" /></td>
						<td><input type="text" class="form-control" name="accountNo3" id="accountNo3" /></td>
						<td><input type="text" class="form-control" name="accountType3" id="accountType3" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('ADDRESS')}</h3>
		</div>

		<div class="box-body">
			<table class="table" id="treeTab">
				<thead>
					<tr>
						<th>${language.getText("ADDRESS_ID")}</th>
						<th>${language.getText("ADDRESS_TYPE")}</th>
						<th>${language.getText("COUNTRY")}</th>
						<th>${language.getText("STATE")}</th>
						<th>${language.getText("CITY")}</th>
						<th>${language.getText("STREET")}</th>
						<th>${language.getText("PHONE")}</th>
					</tr>
				</thead>

				<tbody>
					<c:set var="addressId" value="0"></c:set>
					<c:if test="${!empty record}">
						<c:forEach var="adr" items="${record.partnerAddresses}" varStatus="status">
							<tr>
								<td><a href="#" onClick="doAjaxGet('partnerAddress/edit?id=${adr.id.businessPartnerId},${adr.id.addressId}');"> ${adr.id.addressId} </a> <c:if test="${adr.id.addressId >= addressId}">
										<c:set var="addressId" value="${adr.id.addressId}"></c:set>
									</c:if></td>
								<td>${adr.addressType}</td>
								<td>${adr.country}</td>
								<td>${adr.state}</td>
								<td>${adr.city}</td>
								<td>${adr.street}</td>
								<td>${adr.phone}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:set var="firstAddressId" value="${addressId+1}"></c:set>
					<c:if test="${!empty newAddress}">
						<c:forEach var="adr" items="${newAddress}" varStatus="status">
							<c:if test="${!empty adr}">
								<c:set var="addressId" value="${addressId+1}"></c:set>
								<tr>
									<td><input type="hidden" name="addressId${addressId}" id="addressId${addressId}" value="${addressId}" /> &nbsp;</td>
									<td>${adr.addressType}</td>
									<td>${adr.country}</td>
									<td>${adr.state}</td>
									<td>${adr.city}</td>
									<td>${adr.street}</td>
									<td>${adr.phone}</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>

					<tr>
						<td><input type="hidden" name="addressId" id="addressId" value="${addressId+1}" /> <input type="hidden" name="firstAddressId" id="firstAddressId" value="${firstAddressId}" /> &nbsp;</td>
						<td><ncaTags:select tagName="addressType" tagOptions="${addressTypes}" defvalue="B" notnull="required" /></td>
						<td><ncaTags:select tagName="country" tagOptions="${countries}" defvalue="TR" notnull="required" onChange="resetSelectOptions('city', 'tableOptions?table=CITY&fields=CITY_CODE,CAPTION&where=COUNTRY_CODE&filter=' + document.getElementById('country').value + '&orderby=1', 'required');" /></td>
						<td><input type="text" class="form-control" name="state" id="state" value="" /></td>
						<td><select name="city" id="city" class="form-control" > </select></td>
						<td><input type="text" class="form-control" name="street" id="street" value="" /></td>
						<td><input type="text" class="form-control" name="phone" id="phone" /></td>
						<td><a href="#" type="btn" onClick="addAddress();"> ${language.getIconText("ADD")}</a></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="box-footer">
			<c:if test="${!empty errorText}">
				<p>
					<a href="#" class="btn btn-danger"> ERROR from server: ${errorText} </a>
				</p>
			</c:if>
			<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a>
			<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
		</div>
	</div>

</form:form>

<div class="box box-primary">
	<div class="box-body">
		<c:if test="${record.id>0}">
			<c:forEach items="${controller.contentTypes}" var="content" varStatus="iCount">
				<form:form class="form-horizontal" method="post" modelAttribute="record" id="binData${iCount.index}" enctype="multipart/form-data">
					<input type="HIDDEN" name="purpose" value="${content.objectType}">
					<div class="col-sm-12 col-md-12 form-group" style="margin-right: ''; margin-left: ''">
						<div class="col-md-5 form-group">
							<label for="binFile" class="col-md-2 col-sm-2 control-label">${language.getText(content.caption)} </label>
							<div class="col-sm-10 col-md-10">
								<input type="file" name="binFile" id="binFile" />
							</div>
						</div>
						<div class="col-md-5 form-group">
							<label for="caption" class="col-md-4 col-sm-4 control-label">${language.getText("CAPTION")} </label>
							<div class="col-sm-8 col-md-8">
								<input type="TEXT" name="caption" id="caption" class="form-control">
							</div>
						</div>
						<div class="col-sm-2 col-md-2">
							<a href="#" onclick="doAjaxBinPost('contentRelation/postBinaryData/${content.objectType}/${record.id.toString()}', '#binData${iCount.index}', 'POST');" class="btn btn-primary btn-sm pull-left">${language.getIconText("UPLOAD")}</a>
						</div>
					</div>
				</form:form>
			</c:forEach>
		</c:if>
	</div>
</div>

<script type="text/javascript" src="js/dataTables.style.js"></script>

<script>
function addAddress() {
	var counter = document.forms.f.elements.addressId.value;
	var r1 = '<input type="hidden" name="addressType'  + counter + '" id="addressType' + counter + '" value="' + document.forms.f.elements.addressType.value + '" />' + document.forms.f.elements.addressType.value;
	var r2 = '<input type="hidden" name="street'       + counter + '" id="street'      + counter + '" value="' + document.forms.f.elements.street.value      + '" />' + document.forms.f.elements.street.value;
	var r3 = '<input type="hidden" name="city'         + counter + '" id="city'        + counter + '" value="' + document.forms.f.elements.city.value        + '" />' + document.forms.f.elements.city.value;
	var r4 = '<input type="hidden" name="state'        + counter + '" id="state'       + counter + '" value="' + document.forms.f.elements.state.value       + '" />' + document.forms.f.elements.state.value;
	var r5 = '<input type="hidden" name="country'      + counter + '" id="country'     + counter + '" value="' + document.forms.f.elements.country.value     + '" />' + document.forms.f.elements.country.value;
	var r6 = '<input type="hidden" name="phone'        + counter + '" id="phone'       + counter + '" value="' + document.forms.f.elements.phone.value       + '" />' + document.forms.f.elements.phone.value;
	var r7 = '&nbsp;';
	var t = document.getElementById('treeTab');
	var r = t.insertRow(counter);
	var c0 = r.insertCell(0);
	var c1 = r.insertCell(1);
	var c2 = r.insertCell(2);
	var c3 = r.insertCell(3);
	var c4 = r.insertCell(4);
	var c5 = r.insertCell(5);
	var c6 = r.insertCell(6);
	var c7 = r.insertCell(7);

	c0.innerHTML = counter;
	c1.innerHTML = r1;
	c2.innerHTML = r2;
	c3.innerHTML = r3;
	c4.innerHTML = r4;
	c5.innerHTML = r5;
	c6.innerHTML = r6;
	c7.innerHTML = r7;
	counter++;
	document.forms.f.elements.addressId.value = counter;
}

function addvb() {
	var counter = document.forms.f.elements.vendorBusinessCount.value;
	var vbId = document.forms.f.elements.vendorBusinessId.value;
	var vbTx = document.getElementById("vendorBusinessTx").innerHTML;
	counter++;
	var r0 = '<input type="hidden" name="vendorBusinessId' + counter + '" id="vendorBusinessId' + counter + '" value="' + vbId + '" />' + vbId;
	var r1 = '<input type="hidden" name="vendorBusinessTx' + counter + '" id="vendorBusinessTx' + counter + '" value="' + vbTx + '" />' + vbTx;
	var t = document.getElementById('vbTab');
	var r = t.insertRow(counter);
	var c0 = r.insertCell(0);
	var c1 = r.insertCell(1);

	c0.innerHTML = r0;
	c1.innerHTML = r1;
	document.forms.f.elements.vendorBusinessCount.value = counter;
}

$(document).ready(function() {
	$('.required').attr({ 'placeholder' : '${REQUIRED}' });
	$(".select2").select2();
	$('.date').datepicker({
		autoclose : true,
		format : 'dd-mm-yyyy'
	});
	resetSelectOptions('city', 'tableOptions?table=CITY&fields=CITY_CODE,CAPTION&where=COUNTRY_CODE&filter=' + document.getElementById('country').value + '&orderby=1', 'required');
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
