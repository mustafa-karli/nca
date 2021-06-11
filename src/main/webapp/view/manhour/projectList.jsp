<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
table.dataTable {
	table.dataTable.row-border tbody th, table.dataTable.row-border tbody td, table.dataTable.display tbody th, table.dataTable.display tbody td {
    border-top: 2px solid #5c4e4e;
}
</style>
<script type="text/javascript">
//Confirm delete


$(document).ready(function(){
    $("a.btn").click(function(){
		var data = $(this).attr('id');
    	var buttonId = data.split('_');
		if (buttonId[0] == 'delete'){
			$("#confirmDelete_"+ buttonId[1]).show();
	        $("#cancelDelete_"+ buttonId[1]).show();
	        $("#delete_"+ buttonId[1]).hide();
				}
		else if (buttonId[0] == 'cancelDelete'){
			$("#confirmDelete_"+ buttonId[1]).hide();
	    	$("#delete_"+ buttonId[1]).show();
	    	$("#cancelDelete_"+ buttonId[1]).hide();
		}
    })
});
</script>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<c:if test="${!empty INSERT_ALLOWED}">
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/new');"> ${NEW} </a>
		</c:if>
	</div>
	
	<div class="box-body">
    
	<table id="dataTable1" class="table cell-border table-bordered thin-list">
		<thead>
		<tr>
			<th>${PROJECT_ID}</th>
			<th>${CAPTION}</th>
			<th>${STATUS}</th>
			<th>${CUSTOMER}</th>
			<th>${COUNTRY}</th>
			<th>${LOCATION}</th>
 			<th> &nbsp; </th>
 		</tr>
		</thead>

      <c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${record.id}</td>
			<td><a href="#" onClick="doAjaxGet('project/show?id=${record.id}');">${record.caption}</a></td>
			<td>${record.status}</td>
			<td>${record.customer}</td>
			<td>${record.country}</td>
			<td>${record.location}</td>
			<td>
				<c:if test="${!empty UPDATE_ALLOWED}">
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/edit?id=${record.id}');" id="edit_${record.id}"> ${EDIT} </a>
				</c:if>
				<c:if test="${!empty DELETE_ALLOWED}">
				<a class="btn btn-danger" href="#"  id="delete_${record.id}"> ${DELETE} </a>
				<a class="btn btn-warning" style="display: none;" href="#"  id="cancelDelete_${record.id}"> ${CANCEL} </a>
				<a class="btn btn-danger" style="display: none;" href="#" id="approveDelete_${record.id}"onClick="doAjaxGet('project/delete?id=${record.id}');" id="confirmDelete_${record.id}"> CONFIRM ${DELETE} </a>
				</c:if>
				<a href="#" onClick="doAjaxGet('project/projectWbs/list?projectId=${record.id}');"> ${PROJECT_WBS} </a>
				<a href="#" onClick="doAjaxGet('project/projectTeam/list?projectId=${record.id}');"> ${PROJECT_TEAM} </a>
			</td>
 		</tr>
      </c:forEach>
    </table>
    </div>
</div>
<script type="text/javascript" src="j/dataTables.style.js"> </script>