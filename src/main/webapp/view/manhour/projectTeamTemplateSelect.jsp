<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
	<h3> ${PAGETITLE} </h3>
	<input type=button id=sa1 value="select all" onClick="selectAll('WBS')" />
	<input type=button id=sa2 value="de-select all" onClick="deSelectAll('WBS')" />
	<input type=button id=sa3 value="toggle select" onClick="toggleSelect('WBS')" />
	</div>

	<div class="box-body">
	
	${wbshtml}

	</div>

	<div class="box-footer">
		<form name="f" id="f" method=post>
			<input type=hidden name="projectId" id="projectId" value="${projectId}">
			<input type=hidden name="teamId" id="teamId" value="${teamId}">
			<input type=hidden name="WBS_IDS" id="WBS_IDS" value="">
			<a href="#" onClick="findSelected('WBS');doAjaxPost('projectTeamTemplate/select');" class="btn btn-primary">${OK}</a>
			<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
		</form>
	</div>
</div>

<script type="text/javascript" src="j/selectCB.js"></script>

<script type="text/javascript">
function findSelected(cname) {
	var checkBoxes = document.getElementsByName(cname);
	var x = "";
	for (var i in checkBoxes) {
		if (checkBoxes[i].checked)
			x = x + "," + checkBoxes[i].value;
	}
	document.f.WBS_IDS.value = x;
}
</script>
