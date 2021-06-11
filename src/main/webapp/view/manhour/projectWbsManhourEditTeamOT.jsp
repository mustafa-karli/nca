<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
function captureData() {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        csv.push(row.join(","));        
    }
    document.f.data.value=csv.join("\n");
}
</script>
<style>
tr:nth-child(2n+5) {background: #F8F8F8}
</style>
${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">

	<p>${teamCaption}</p>
	<p>${date}</p>

	<table class="table table-bordered table-hover table-editable">
	<c:forEach var="row" items="${records}" varStatus="rowStatus">
		<tr>
		<c:forEach var="col" items="${row}" varStatus="colStatus">
			<c:choose>
				<c:when test="${rowStatus.index < 2 && !empty col}">
					<th colspan=2> ${col} </th>
				</c:when>
				<c:when test="${rowStatus.index < 2 && empty col}">
				</c:when>
				<c:when test="${rowStatus.index == 2}">
					<th> ${col} </th>
				</c:when>
				<c:when test="${rowStatus.index > 2 && colStatus.index < 2}">
					<th> ${col} </th>
				</c:when>
				<c:otherwise>
					<td contenteditable="true"> ${col} </td>
				</c:otherwise>
			</c:choose>
          </c:forEach>             
			<c:choose>
				<c:when test="${rowStatus.index < 2}">
					<th colspan=2> _._._._._._._._._._ </th>
				</c:when>
				<c:when test="${rowStatus.index == 2}">
					<th> MH </th> <th> OT </th>
				</c:when>
				<c:otherwise>
					<td> &nbsp; </td> <td> &nbsp; </td>
				</c:otherwise>
			</c:choose>
			
        </tr>
      </c:forEach>             
    </table>
	</div>

	<div class="box-footer">
		<form name="f" id="f" method="post">
			<input type=hidden name=projectId value="${projectId}">
			<input type=hidden name=teamId value="${teamId}">
			<input type=hidden name=date value="${date}">
			<input type=hidden name=data value="">
			<a href="#" class="btn btn-primary" onClick="captureData();doAjaxPost('projectWbsManhour/editTeam');"> ${language.getIconText("SAVE")} </a>
			<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${language.getIconText("CANCEL")} </a> 
		</form>
	</div>
</div>