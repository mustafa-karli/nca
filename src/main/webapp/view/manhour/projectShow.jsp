<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>
	<div class="box-body">
		<p>
			${language.getIconText("PROJECT_ID")} : ${record.id} ${record.caption} 
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/edit?id=${record.id}');"> ${language.getIconText("EDIT")} </a>
			<c:choose>
				<c:when test="${record.status == 'APPROVE_WBS'}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/approveFinal?id=${record.id}');"> ${language.getIconText("APPROVE_FINAL")} </a>
				</c:when>
				<c:when test="${record.status == 'APPROVE_FINAL'}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/withdrawApprove?id=${record.id}');"> ${language.getIconText("APPROVE_WITHDRAW")} </a>
				</c:when>
			</c:choose>
		</p>
	</div>
</div>


<div class="nav-tabs-custom">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">${language.getText("PROJECT_WBS")}</a></li>
		<li><a href="#tab2" data-toggle="tab">${language.getText("PROJECT_TEAM")}</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="tab1">

			<div class="box box-info">
				<div class="box-header with-border">
					<c:choose>
						<c:when test="${record.status == 'INITIAL'}">
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/select?id=${record.id}');"> ${language.getIconText("CHOOSE")} </a>
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/approveWbs?id=${record.id}');"> ${language.getIconText("APPROVE_WBS")} </a>
						</c:when>
						<c:when test="${record.status == 'APPROVE_WBS'}">
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/withdrawApprove?id=${record.id}');"> ${language.getIconText("APPROVE_WITHDRAW")} </a>
						</c:when>
					</c:choose>
				</div>

				<div class="box-body">

					<table id="dataTable1" class="table table-stripe wide-list">
						<thead>
							<tr>
								<th colspan=2>${language.getText("LOCAL")}</th>
								<th colspan=2>${language.getText("CUSTOMER")}</th>
								<th rowspan=2>${language.getText("UNIT")}</th>
								<th colspan=3>${language.getText("TENDER")}</th>
								<th colspan=3>${language.getText("PUP")}</th>
								<th colspan=3>${language.getText("PLANNED")}</th>
								<th>&nbsp;</th>
							</tr>

							<tr>
							
								<th>${language.getText("CODE")}</th>
								<th>${language.getText("CAPTION")}</th>
								<th>${language.getText("CODE")}</th>
								<th>${language.getText("CAPTION")}</th>
								<th>${language.getText("METRIC")}</th>
								<th>${language.getText("QUANTITY")}</th>
								<th>${language.getText("WORKFORCE")}</th>
								<th>${language.getText("METRIC")}</th>
								<th>${language.getText("QUANTITY")}</th>
								<th>${language.getText("WORKFORCE")}</th>
								<th>${language.getText("METRIC")}</th>
								<th>${language.getText("QUANTITY")}</th>
								<th>${language.getText("WORKFORCE")}</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<c:set var="tenderWf" value="0"></c:set>
						<c:set var="pupWf" value="0"></c:set>
						<c:set var="plannedWf" value="0"></c:set>

						<c:forEach var="projectWbs" items="${record.projectWbses}"
							varStatus="status">
							<tr>
							
								<td>${projectWbs.category.treeCode}</td>
								<td><a href="#" onClick="doAjaxGet('projectWbs/show?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> ${projectWbs.category.caption} </a> </td>
								<td>${projectWbs.customerWbsCode}</td>
								<td>${projectWbs.customerWbsCaption}</td>
								<td>${projectWbs.unit}</td>
								<td><fmt:formatNumber value="${projectWbs.metric}" maxFractionDigits="2"/></td>
								<td><fmt:formatNumber value="${projectWbs.quantity}" maxFractionDigits="2"/></td>
								<td  class="workforce"><fmt:formatNumber value="${projectWbs.workforce}" maxFractionDigits="2"/></td>
								<td><fmt:formatNumber value="${projectWbs.pupMetric}" maxFractionDigits="2"/></td>
								<td><fmt:formatNumber value="${projectWbs.pupQuantity}" maxFractionDigits="2"/></td>
								<td class="workforce"><fmt:formatNumber value="${projectWbs.pupWorkforce}" maxFractionDigits="2"/></td>
								<td><fmt:formatNumber value="${projectWbs.plannedMetric}" maxFractionDigits="2"/></td>
								<td><fmt:formatNumber value="${projectWbs.plannedQuantity}" maxFractionDigits="2"/></td>
								<td class="workforce"><fmt:formatNumber value="${projectWbs.plannedWorkforce}" maxFractionDigits="2"/></td>
								<c:set var="tenderWf" value="${tenderWf + projectWbs.workforce}"></c:set>
								<c:set var="pupWf" value="${pupWf + projectWbs.pupWorkforce}"></c:set>
								<c:set var="plannedWf" value="${plannedWf + projectWbs.plannedWorkforce}"></c:set>
								<td><c:choose>
										<c:when test="${record.status == 'INITIAL'}">
											<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/edit?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> ${language.getIconText("EDIT")} </a>
											<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbs/delete?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> ${language.getIconText("DELETE")} </a>
										</c:when>
										<c:otherwise>
											<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/edit?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> ${language.getIconText("EDIT")} </a>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
						<tfoot>
								<tr>
									<th colspan=7 >${language.getText("TOTAL")}</th>
									<th ><fmt:formatNumber value="${tenderWf}" maxFractionDigits="2"/></th>
									<th colspan=2> &nbsp;</th>
									<th > <fmt:formatNumber value="${pupWf}" maxFractionDigits="2" /> </th>
									<th colspan=2> &nbsp;</th>
									<th > <fmt:formatNumber value="${plannedWf}" maxFractionDigits="2" /></th>
									<th> &nbsp;</th>
								</tr>
						</tfoot>
					</table>
				</div>
			</div>

		</div>

		<div class="tab-pane" id="tab2">

			<div class="box box-info">
				<div class="box-header with-border">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeam/new?parentKey=${record.id}');"> ${language.getIconText("NEW")} </a>
				</div>

				<div class="box-body">
					<table id="dataTable2" class="table table-bordered table-hover thin-list" >
						<thead>
							<tr>
								<th>${language.getText("CAPTION")}</th>
								<th>&nbsp;</th>
								<th>${language.getText("TEAM_ID")}</th>
							</tr>
						</thead>

						<c:forEach var="projectTeam" items="${record.projectTeams}"
							varStatus="status">
							<tr>
								<td> <a href="#" onClick="doAjaxGet('projectTeam/show?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${projectTeam.caption} </a> </td>
								<td><a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeam/edit?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${language.getIconText("EDIT")} </a>
									<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectTeam/delete?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${language.getIconText("DELETE")} </a>
								</td>
								<td> ${projectTeam.id.teamId} </td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</div>
	</div>
</div>



<script type="text/javascript" src="j/dataTables.style.js"> </script>

<script type="text/javascript">
	$(function() {
		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			// save the latest tab; use cookies if you like 'em better:
			localStorage.setItem('lastTabProjectTeam', $(this).attr('href'));

			$('.table').DataTable( {retrieve: true, visible: true, api: true} ).columns.adjust();
			
		});

		// go to the latest tab, if it exists:
		var lastTabProjectTeam = localStorage.getItem('lastTabProjectTeam');
		if (lastTabProjectTeam) {
			$('[href="' + lastTabProjectTeam + '"]').tab('show');
		}
	});
</script>
