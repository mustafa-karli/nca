<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language.localeStr}" scope="session" />
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>

	<div class="box-body">

		<a href="#" class="btn btn-primary" onClick="doAjaxGet('vendor/fastEntry')"> ${language.getText("NEW")} </a>

		<table id="dataTable5" class="table" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>${language.getText("VENDOR")}</th>
					<th>${language.getText("BEGDA")}</th>
					<th>&nbsp;</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="record" items="${records}" varStatus="status">
					<tr>
						<td><a href="#" onClick="doAjaxGet('vendor/fastEntry?id=${record.id}')"> ${record.businessPartner.caption} </a></td>
						<td><fmt:formatDate value="${record.begda}" pattern="dd MMMM yyyy" /></td>
						<td>
							<a href="#" onClick="doAjaxGet('employee/empUser?partnerId=${record.id}')"> <i class="fa fa-user"> ${language.getText("EMPLOYEE")} </i> </a>
							<a href="#" onClick="doAjaxGet('salesOrderCommitment/orders?partnerId=${record.id}')"> <i class="fa fa-order"> ${language.getText("ORDER")} </i> </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript" src="js/dataTables.style.js"></script>
