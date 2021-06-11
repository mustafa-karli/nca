<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
function callSelectionPersonnel() {
  item0 = document.f.PERSON_ID;
  item1 = document.f.PERSON_CAPTION;
  selectWindow = window.open("personnelSelect", "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
  selectWindow.item1 = item1;
}

function callSelectionSubcontractor() {
  item0 = document.f.SUBCONTRACTOR_ID;
  item1 = document.f.SUBCONTRACTOR_CAPTION;
  selectWindow = window.open("subcontractorSelect", "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
  selectWindow.item1 = item1;
}
</script>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="projectTeam/show?id=${projectId},${teamId}"/>

	<div class="box-body">
		<table>
			<tr>
				<th> ${language.getText("PROJECT_ID")} </th>
				<td> ${projectId} ${projectCaption} </td>
			</tr>
			<tr>
				<th> ${language.getText("TEAM_ID")} </th>
				<td> ${teamId} ${teamCaption} </td>
			</tr>
			<tr>
				<th> ${language.getText("DATE")} </th>
				<td> ${begDate} .. ${endDate} </td>
			</tr>
		</table>
  
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"> ${language.getText("WORKER_ID")} </label>
			<div class="col-sm-10">
				${record.worker.caption}
				<form:input type="hidden" path="record.id.projectId"/>
				<form:input type="hidden" path="record.id.teamId"/>
				<form:input type="hidden" path="record.id.workerId"/>
				<form:input type="hidden" path="record.teamLead"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${language.getText("PERSON_ID")}</label>
			<div class="col-sm-10">
				<form:input type="hidden" path="personId" />
        		<a href="#" onClick="if (callSelectionPersonnel()) {document.f.subcontractorId.value=''; document.f.subcontractorCaption.value='';}"> ${language.getIconText("CHOOSE")} ${language.getText("PERSON_ID")} </a>
				<form:input path="personCaption" />
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${language.getText("SUBCONTRACTOR_ID")}</label>
			<div class="col-sm-10"> 
				<form:input type="hidden" path="subcontractorId" />
        		<a href="#" onClick="if (callSelectionSubcontractor()) {document.f.subcontractorId.value=''; document.f.subcontractorCaption.value='';}"> ${language.getIconText("CHOOSE")} ${language.getText("SUBCONTRACTOR_ID")} </a>
				<form:input path="subcontractorCaption" />
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

	</form:form>
</div>
