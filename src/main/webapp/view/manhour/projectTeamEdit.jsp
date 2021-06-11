<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="project/show?id=${record.id.projectId}"/>

	<div class="box-body">

		<div class="form-group">
			<label class="col-sm-2 control-label" for="id.projectId"> ${language.getText("PROJECT_ID")} </label>
			<div class="col-sm-10">  
				${record.id.projectId} ${record.project.caption} <form:input type="hidden" path="id.projectId"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="id.teamId"> ${language.getText("TEAM_ID")} </label>
			<div class="col-sm-10">
				${record.id.teamId} <form:input type="hidden" path="id.teamId"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="begDate">${language.getIconText("FORMEN")}</label>
			<div class="col-sm-10">
			<c:choose>
				<c:when test="${empty id.teamId}">
					${workerCaption}
					<input type="hidden" name="workerId" id="workerId" value="${workerId}"/>
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/selectPerson?memberType=LEAD&parentKey=${record.id.projectId}');"> ${language.getIconText("SELECT_EMPLOYEE")} </a>
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/selectWorker?memberType=LEAD&parentKey=${record.id.projectId}');"> ${language.getIconText("SELECT_SUBCONTRACTOR")} </a>
				</c:when>
			</c:choose>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${language.getText("CAPTION")}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="caption"/>
			</div>
		</div>

	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

	</form:form>
</div>