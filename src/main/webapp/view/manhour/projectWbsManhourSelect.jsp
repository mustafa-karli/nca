<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- ${DATATABLE1} -->

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<div class="box-body">
    
	<form name="f" id="f">
    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
			<tr>
				<th> 
				<select name=projectId id=projectId onChange="doAjaxGet('projectWbsManhour/select?projectId=' + document.f.projectId.value + '&date=' + document.f.date.value);">
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
				</th>
				<th colspan=2> ${TEAM} </th>
				<th> <input type="date" name="date" id="date" value="${date}"> </th>
			</tr>
		</thead>
		<c:forEach var="project" items="${projects}">
			<c:choose>
			<c:when test="${project.id == projectId}">
			<c:forEach var="projectTeam" items="${project.projectTeams}">
				<tr>
					<td> ${project.caption} </td>
					<td> ${projectTeam.caption} </td>
					<c:forEach var="worker" items="${projectTeam.projectTeamPersonnel}">
					<c:if test="${worker.teamLead > 0}">
					<td> ${worker.worker.caption} </td>
					</c:if>
					</c:forEach>
					<td>
						<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbsManhour/editTeam?id=${projectTeam.id.projectId},${projectTeam.id.teamId},' + document.f.date.value);"> ${PROJECT_MANHOUR_1} </a>
						<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbsManhour/editTeamLead?id=${projectTeam.id.projectId},${projectTeam.id.teamId},' + document.f.date.value);"> ${PROJECT_MANHOUR_N} </a>
					</td>
				</tr>
			</c:forEach>
			</c:when>
			</c:choose>
		</c:forEach>
	</table>
	</form>
	</div>
</div>