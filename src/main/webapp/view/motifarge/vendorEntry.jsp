<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="css/nauticana.table.css" rel="stylesheet" type="text/css" />

<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="HIDDEN" name="nextpage" value="${prevpage}">

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('VENDOR')}</h3>
		</div>

		<div class="box-body">
			<c:if test="${!empty partner}">
				<input type="HIDDEN" name="businessPartnerId" value="${partner.id}">
				<c:set var="caption" value="${partner.caption}"></c:set>
				<c:set var="hqCountry" value="${partner.hqCountry}"></c:set>
				<c:set var="taxCenter" value="${partner.taxCenter}"></c:set>
				<c:set var="taxNumber" value="${partner.taxNumber}"></c:set>
			</c:if>

			<div class="col-md-6">
				<div class="form-group">
					<label for="caption" class="col-md-4 col-sm-4 control-label">${language.getText("CAPTION")} </label>
					<div class="col-md-8  col-sm-8">
						<input type="text" class="form-control required" name="caption" id="caption" value="${caption}" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="hqCountry" class="col-md-4 col-sm-4 control-label">${language.getText("HQ_COUNTRY")} </label>
					<div class="col-md-8  col-sm-8">
						<select name="hqCountry" id="hqCountry" class="form-control select2" style="width: 100%;">
							<c:forEach var="opt" items="${countries}" varStatus="status">
								<c:choose>
									<c:when test="${hqCountry == opt.key}">
										<option value="${opt.key}" selected>${opt.value}</option>
									</c:when>
									<c:otherwise>
										<option value="${opt.key}">${opt.value}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="taxCenter" class="col-md-4 col-sm-4 control-label">${language.getText("TAX_CENTER")} </label>
					<div class="col-md-8  col-sm-8">
						<input type="text" class="form-control required" name="taxCenter" id="taxCenter" value="${taxCenter}" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="taxNumber" class="col-md-4 col-sm-4 control-label">${language.getText("TAX_NUMBER")} </label>
					<div class="col-md-8  col-sm-8">
						<input type="text" class="form-control required" name="taxNumber" id="taxNumber" value="${taxNumber}" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText('ADDRESS')}</h3>
		</div>

		<div class="box-body">
			<table class="table">
				<thead>
					<tr>
						<th>${language.getText("ADDRESS_ID")}</th>
						<th>${language.getText("ADDRESS_TYPE")}</th>
						<th>${language.getText("STREET")}</th>
						<th>${language.getText("CITY")}</th>
						<th>${language.getText("STATE")}</th>
						<th>${language.getText("COUNTRY")}</th>
						<th>${language.getText("PHONE")}</th>
					</tr>
				</thead>

				<tbody>
					<c:set var="addressId" value="1"></c:set>
					<c:if test="${!empty partner}">
						<c:forEach var="adr" items="${partner.partnerAddresses}" varStatus="status">
							<tr>
								<td><a href="#" onClick="doAjaxGet('partnerAddress/edit?id=${adr.id.businessPartnerId},${adr.id.addressId}');"> ${adr.id.addressId} </a> <c:if test="${adr.id.addressId >= addressId}">
										<c:set var="addressId" value="${adr.id.addressId+1}"></c:set>
									</c:if></td>
								<td>${adr.addressType}</td>
								<td>${adr.street}</td>
								<td>${adr.city}</td>
								<td>${adr.state}</td>
								<td>${adr.country}</td>
								<td>${adr.phone}</td>
							</tr>
						</c:forEach>
					</c:if>

					<tr>
						<td><input type="hidden" name="addressId" id="addressId" value="${addressId}" /> ${addressId}</td>
						<td><select name="addressType" id="addressType" class="form-control select2">
								<c:forEach var="opt" items="${addressTypes}" varStatus="status">
									<option value="${opt.key}">${opt.key}${opt.value}</option>
								</c:forEach>
						</select></td>
						<td><input type="text" class="form-control required" name="street" id="street" /></td>
						<td><input type="text" class="form-control required" name="city" id="city" /></td>
						<td><input type="text" class="form-control" name="state" id="state" /></td>
						<td><select name="country" id="country" class="form-control select2">
								<c:forEach var="opt" items="${countries}" varStatus="status">
									<option value="${opt.key}">${opt.value}</option>
								</c:forEach>
							</select>
						</td>
						<td><input type="text" class="form-control" name="phone" id="phone" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

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

		<div class="box-footer">
			<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a> <a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
		</div>
	</div>

</form:form>

<script type="text/javascript" src="js/dataTables.style.js">
</script>
<script>

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
