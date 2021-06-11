<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
    <form name="f" id="f" method="post">
	<div class="box-header with-border">

		<h3 class="box-title"> ${PAGETITLE} </h3>
		<input type=hidden name=nextpage value="${nextpage}">
		<input type=hidden name=parentKey value="${parentKey}">
		<input type=hidden name=memberType value="${memberType}">
		<input type=hidden name=workerIds value="">

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="subcontractorId">${language.getText("SUBCONTRACTOR")}</label>
			<div class="col-sm-10">
				<select name=subcontractorId id=subcontractorId class="select2 form-control" onChange="doAjaxGet('worker/selectWorker?memberType=${memberType}&parentKey=${parentKey}&subcontractorId=' + document.f.subcontractorId.value);">
			    <c:forEach var="subcontractor" items="${subcontractors}" varStatus="status">
				<c:choose>
					<c:when test="${subcontractorId == subcontractor.id}">
						<option value="${subcontractor.id}" selected> ${subcontractor.businessPartner.caption} </option>
					</c:when>
					<c:otherwise>
						<option value="${subcontractor.id}"> ${subcontractor.businessPartner.caption} </option>
					</c:otherwise>
				</c:choose>
				</c:forEach>
				</select>
			</div>
		</div>
	</div>

	<div class="box-body">

		<c:choose>
			<c:when test="${empty workers}">
			</c:when>
			<c:when test="${empty memberType}">
				<table class="table table-bordered table-hover">
				<tr>
					<th>${language.getText("CAPTION")} </th>
					<th><input type=checkbox id="checkAll" name="checkAll"/></th>
				</tr>

		 		<c:forEach var="worker" items="${workers}" varStatus="status">
				<tr>
					<td>${worker.caption}</td>
					<td><input type=checkbox name="WORKER_ID" value="${worker.id}" /></td>
				</tr>
				</c:forEach>
				</table>
				<a href="#" class="btn btn-primary" onClick="findSelected('WORKER_ID');doAjaxPost('${postlink}');"> ${language.getIconText("OK")} </a> 
			</c:when>
			<c:otherwise>
				<table class="table table-bordered table-hover">
				<tr>
					<th>${language.getText("CAPTION")} </th>
				</tr>

		 		<c:forEach var="worker" items="${workers}" varStatus="status">
				<tr>
					<td> <a href="#" onClick="document.f.workerIds.value=',${worker.id}';doAjaxPost('${postlink}');"> ${worker.caption} </a> </td>
				</tr>
				</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${language.getIconText("CANCEL")} </a> 
    </div>
   	</form>
</div>

<script type="text/javascript">
//Find selected
function findSelected(cname) {
	var c = document.getElementsByName(cname);
	var x = "";
	for (var i in c) {
		if (c[i].checked)
			x = x + "," + c[i].value;
	}
	document.f.workerIds.value = x;
}
//Check All
	$("#checkAll").click(function () {
	    $('input:checkbox').not(this).prop('checked', this.checked);
	});
//Select2 initializer
	$(function () {
	    //Initialize Select2 Elements
	    $(".select2").select2();
	});
</script>