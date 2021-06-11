<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language.localeStr}" scope="session" />
<div class="box box-primary">
	<div class="box-header with-border">
		<strong>${PAGETITLE}</strong>
		<a class="btn btn-primary btn-sm" href="#" onClick="doAjaxGet('productPriceCommitment/finalize?materialId=${materialId}&businessPartnerId=${client}&orderDeadLine=<fmt:formatDate value="${orderDeadLine}" pattern="dd-MM-yyyy" />');"> <i class="fa fa-gavel"> ${language.getText("FINALIZE")} </i> </a>
	</div>

	<div class="box-body">

		<table id="dataTable5" class="table" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>${language.getText("SALES_ORDER_ID")}</th>
					<th>${language.getText("INITIAL_SEQUENCE")}</th>
					<th>${language.getText("QUANTITY")}</th>
					<th>${language.getText("UNIT_PRICE")}</th>
					<th>${language.getText("DISCOUNT")}</th>
					<th>${language.getText("CURRENCY")}</th>
					<th>${language.getText("STATUS")}</th>
					<th>${language.getText("ORDER_DATE")}</th>
					<th>${language.getText("ADVANCE_PAYMENT")}</th>
					<th>${language.getText("DESCRIPTION")}</th>
					<th>${language.getText("CUSTOMER")}</th>
					<th>${language.getText("USERNAME")}</th>
					<th>${language.getText("NAME")}</th>
					<th>${language.getText("EMAIL_ADDRESS")}</th>
					<th>${language.getText("ADDRESS")}</th>
					<th>${language.getText("CITY")}</th>
					<th>${language.getText("STATE")}</th>
					<th>${language.getText("COUNTRY")}</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="record" items="${records}" varStatus="status">
					<tr>
						<td>${record.salesOrderId}</td>
						<td>${record.initialSequence}</td>
						<td>${record.quantity}</td>
						<td>${record.unitPrice}</td>
						<td>${record.discount}</td>
						<td>${record.currency}</td>
						<td>${record.status}</td>
						<td>${record.orderDate}</td>
						<td>${record.advancePayment}</td>
						<td>${record.description}</td>
						<td>${record.caption}</td>
						<td>${record.username}</td>
						<td>${record.firstName} ${record.lastName}</td>
						<td>${record.emailAddress}</td>
						<td>${record.address}</td>
						<td>${record.city}</td>
						<td>${record.state}</td>
						<td>${record.country}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript" src="js/dataTables.style.js"></script>
