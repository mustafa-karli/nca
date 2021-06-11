<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>

	<div class="box-body">

		<table  class="display nowrap report" cellspacing="0" width="100%" >
			<thead>
				<tr>
					<th align="center">${language.getText("MATERIAL_ID")}</th>
					<th align="center">${language.getText("CAPTION")}</th>
					<th align="center">${language.getText("QUANTITY")}</th>
					<th align="center">${language.getText("UNIT")}</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="record" items="${totals}" varStatus="rIndex">
					<tr>
						<td>${record.materialId}</td>
						<td>${record.caption}</td>
						<td>${record.quantity}</td>
						<td>${record.unit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<div class="box-footer">

		<table  id="example" class="display group" style="width:100%" >
			<thead>
				<tr>
					<th align="center">${language.getText("ORGANIZATION")}-${language.getText("MATERIAL_REQUEST")}-${language.getText("DUE_DATE")}</th>
					<th align="center">${language.getText("DUE_DATE")}</th>
					<th align="center">${language.getText("LINE")}</th>
					<th align="center">${language.getText("MATERIAL")}</th>
					<th align="center">${language.getText("QUANTITY")}</th>
					<th align="center">${language.getText("UNIT")}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${records}" varStatus="rIndex">
					<c:forEach var="line" items="${record.materialRequestItems}" varStatus="iIndex">
						<tr>
							<td>${language.getText("ORGANIZATION")}: ${record.organization.caption}- ${language.getText("ORDER_ID")}: ${record.id} - ${language.getText("ORDER_DATE")}: ( ${fn:substring(record.requestDate, 0, 10)} ) <img src="contentRelation/firstData/MR/${record.id}"></td>
							<td>${fn:substring(record.dueDate, 0, 10)}</td>
							<td>${line.id.line}</td>
							<td>${line.material.caption}</td>
							<td>${line.quantity}</td>
							<td>${line.unit}</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
		<a href="#" class="btn btn-warning" onClick="doAjaxGet('materialRequestReport/list');"> ${language.getText("CLOSE")} </a>
	</div>
</div>
		
<script type="text/javascript" src="js/dataTables.style.js"></script>

