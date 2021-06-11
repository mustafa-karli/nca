<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<p> ${language.getText("PROJECT_ID")} </p>
		<form name="f" id="f">
			<select name=projectId id=projectId onChange="doAjaxGet('projectWbsQuantity/approve?projectId=' + document.f.projectId.value);">
			<c:forEach var="project" items="${projects}">
				<c:choose>
					<c:when test="${project.id == projectId}">
					<option value="${project.id}" selected> ${project.caption} </option>
					</c:when>
					<c:otherwise>
					<option value="${project.id}"> ${project.caption} </option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</select>
		</form>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
			<tr>
				<th rowspan=2> ${language.getText("TEAM_CAPTION")} </th>
				<th colspan=2> ${language.getText("CATEGORY_CAPTION")} </th>
				<th rowspan=2> ${language.getText("UNIT")} </th>
				<th colspan=3> ${language.getText("TENDER")} </th>
				<th rowspan=2> ${language.getText("APPROVED_QUANTITY")} </th>
				<th colspan=3> ${language.getText("QUANTITY_TO_APPROVE")} </th>
				<th rowspan=2> &nbsp; </th>
			</tr>
			<tr>
				<th> ${language.getText("TREE_CODE")} </th>
				<th> ${language.getText("CATEGORY_CAPTION")} </th>
				<th> ${language.getText("METRIC")} </th>
				<th> ${language.getText("QUANTITY")} </th>
				<th> ${language.getText("WORKFORCE")} </th>
				<th> ${language.getText("BEGDA")} </th>
				<th> ${language.getText("ENDDA")} </th>
				<th> ${language.getText("QUANTITY")} </th>
			</tr>
		</thead>

		<c:set var="varProjectId"  value="-1"></c:set>
		<c:set var="varTeamId"     value="-1"></c:set>
		<c:set var="varCategoryId" value="-1"></c:set>

		<c:forEach var="project" items="${projects}">
			<c:choose>
			<c:when test="${project.id == projectId}">
			<c:forEach var="record" items="${records}">
				<tr>
					<td> ${record.teamCaption} </td>
					<td> ${record.treeCode} </td>
					<td> ${record.categoryCaption} </td>
					<td> ${record.unit} </td>
					<td> ${record.metric} </td>
					<td> ${record.quantity} </td>
					<td> ${record.workforce} </td>
					<td> ${record.approvedQuantity} </td>
					<td> ${record.id.begda} </td>
					<td> ${record.endda} </td>
					<td> ${record.quantityToApprove} </td>
					<td>
						<c:if test="${record.quantityToApprove > 0}">
							<a class="btn btn-primary" href="#" onClick="doAjaxPost('projectWbsQuantity/approve?projectId=${record.id.projectId}&categoryId=${record.id.categoryId}&teamId=${record.id.teamId}&begda=${record.id.begda}');"> ${language.getText("APPROVE_QUANTITY")} </a>
						</c:if>
						<c:if test="${record.id.projectId != varProjectId || record.id.teamId != varTeamId || record.id.categoryId != varCategoryId}">
							<a class="btn btn-primary" href="#" onClick="doAjaxPost('projectWbsQuantity/approve?projectId=${record.id.projectId}&categoryId=${record.id.categoryId}&teamId=${record.id.teamId}');"> ${language.getText("APPROVE_ALL")} </a>
							<c:set var="varProjectId"  value="${record.id.projectId}"></c:set>
							<c:set var="varTeamId"     value="${record.id.teamId}"></c:set>
							<c:set var="varCategoryId" value="${record.id.categoryId}"></c:set>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			</c:when>
			</c:choose>
		</c:forEach>
	</table>
	</div>
</div>