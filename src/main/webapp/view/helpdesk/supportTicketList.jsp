<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<c:if test="${!empty INSERT_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('${modelMap}/new');"> ${NEW} </a>
		</c:if>
		
		<!-- Buraya çeşitli filtreler sağlayan düğmeler ekle
		My Tickets (closed olmayanlar)
		Tickets assigned to me
		Unassigned tickets in my groups
		All tickets in my groups
		All unassigned tickets
		 -->
		 
	</div>

	<div class="box-body">
	
		<table id="dataTable1" class="table table-bordered table-striped">
			<thead>
				<tr>
				<c:forEach var="strTh" items="${recordHeaders}" varStatus="status">
					<th>${strTh}</th>
				</c:forEach>
					<th> &nbsp; </th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="record" items="${records}" varStatus="status">
			<tr>
				<td> <a href="#" onClick="doAjaxGet('${modelMap}/show?id=${record.id}');"> ${record.id} </a> </td>
    	        <td>${record.owner.username}</td>
    	        <td>${record.supportArea.caption}</td>
    	        <td>${record.issueDate}</td>
    	        <td>${record.priority}</td>
    	        <td>${record.ticketType}</td>
    	        <td>${record.status}</td>
    	        <td>${record.caption}</td>
    	        <td>${record.assetName}</td>
    	        <td>${record.agent.username}</td>
    	        <td>${record.supportGroup.caption}</td>
				<td>
					<c:if test="${!empty UPDATE_ALLOWED}">
						<a href="#" onClick="doAjaxGet('${modelMap}/edit?id=${record.id}');" class="btn btn-primary"> ${EDIT} </a> 
					</c:if>
					<c:if test="${!empty DELETE_ALLOWED}">
						<a href="#" onClick="doAjaxGet('${modelMap}/delete?id=${record.id}');" class="btn btn-danger"> ${DELETE} </a> 
					</c:if>
				</td>
			</tr>
			</c:forEach>         
			</tbody>
		</table>
	</div>
</div>

<script>
$(function () {
	$('#dataTable1').DataTable({
		'paging':false,
		'scrollY': '50vh',
		'lengthChange':false,
		'searching':true,
		'ordering':true,
		'info':true,
		'autoWidth': true,
		'responsive': true,
		'scrollCollapse': true
	})
})
</script>
