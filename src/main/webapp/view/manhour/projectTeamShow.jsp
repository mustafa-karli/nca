<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
	<table>
		<tr>
			<th> ${language.getText("PROJECT_ID")} </th>
	        <td> ${record.project.id} ${record.project.caption} </td>
		</tr>
		<tr>
        	<th> ${language.getText("TEAM_ID")} </th>
			<td> ${record.id.teamId} ${record.caption} </td>
		</tr>
	</table>
	</div>
</div>

<div class="nav-tabs-custom">

	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">${language.getText("PROJECT_TEAM_PERSON")}</a></li>
		<li><a href="#tab2" data-toggle="tab">${language.getText("PROJECT_TEAM_TEMPLATE")}</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="tab1">

			<div class="box box-info">
				<div class="box-header with-border">
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/selectPerson?parentKey=${record.id.projectId},${record.id.teamId}&nextpage=projectTeam/show?id=${record.id.projectId},${record.id.teamId}');"> ${language.getIconText("ADD_EMPLOYEE")} </a> 
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/selectWorker?parentKey=${record.id.projectId},${record.id.teamId}&nextpage=projectTeam/show?id=${record.id.projectId},${record.id.teamId}');"> ${language.getIconText("ADD_SUBCONTRACTOR")} </a>
				</div>
	
				<div class="box-body">
				    <table id="dataTable1" class="table table-bordered table-hover thin-list">
					<thead>
						<tr>
							<th> ${language.getText("TEAM_LEAD")} </th>
							<th> ${language.getText("PERSON_ID")} </th>
							<th> ${language.getText("SUBCONTRACTOR_ID")} </th>
							<th> ${language.getText("CAPTION")} </th>
							<th> &nbsp; </th>
						</tr>
					</thead>
		
					<c:forEach var="projectTeamPerson" items="${record.projectTeamPersonnel}" varStatus="status">
						<tr>
							<td> ${projectTeamPerson.teamLead} </td>
							<td> ${projectTeamPerson.worker.personId} </td>
							<td> ${projectTeamPerson.worker.subcontractor.businessPartner.caption}</td>
							<td> ${projectTeamPerson.worker.caption}</td>
							<td>
							<c:choose>
								<c:when test="${projectTeamPerson.teamLead != 1}">
								<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectTeamPerson/delete?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId},${projectTeamPerson.id.workerId}&nextpage=projectTeam/show?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId}');"> ${language.getIconText("DELETE")} </a>
								</c:when>
							</c:choose>
								<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/selectPerson?parentKey=${record.id.projectId},${record.id.teamId}&nextpage=projectTeam/show?id=${record.id.projectId},${record.id.teamId}&supervisor=${projectTeamPerson.worker.personId}');"> ${language.getIconText("ADD_SIBLINGS")} </a> 
							</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
		
		<div class="tab-pane" id="tab2">
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title"> ${language.getText("PROJECT_TEAM_TEMPLATE")} </h3>
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeamTemplate/select?id=${record.id.projectId},${record.id.teamId}');"> ${language.getIconText("CHOOSE")} </a>
				</div>

				<div class="box-body">
			 	    <table id="dataTable2" class="table table-bordered table-hover thin-list">
					<thead>
						<tr>
							<th> ${language.getText("TREE_CODE")} </th>
							<th> ${language.getText("CAPTION")} </th>
							<th> &nbsp; </th>
						</tr>
					</thead>

					<c:forEach var="category" items="${record.projectTeamCategory}" varStatus="status">
						<tr>
							<td>${category.treeCode}</td>
							<td>${category.caption}</td>
							<td>
								<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectTeamTemplate/delete?id=${record.id.projectId},${category.id},${record.id.teamId}');"> ${language.getIconText("DELETE")} </a>
							</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
		
	</div>
	
	<div class="box-footer">
		<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${language.getIconText("CANCEL")} </a> 
	</div>
	
</div>
<script type="text/javascript" src="js/dataTables.style.js"> </script>
<script type="text/javascript">
$(function() { 
    // for bootstrap 3 use 'shown.bs.tab', for bootstrap 2 use 'shown' in the next line
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        // save the latest tab; use cookies if you like 'em better:
        localStorage.setItem('lastTab', $(this).attr('href'));
        $('.table').DataTable( {retrieve: true, visible: true, api: true} ).columns.adjust();

    });

    // go to the latest tab, if it exists:
    var lastTab = localStorage.getItem('lastTab');
    if (lastTab) {
        $('[href="' + lastTab + '"]').tab('show');
    }
});

</script>
