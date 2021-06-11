<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>

	<div class="box-body">

		<table id="dataTable10" class="display nowrap list" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>${language.getText("NOTIFICATION_TYPE") }</th>
					<th>${language.getText("NOTIFICATION_ID") }</th>
					<th>${language.getText("USERNAME") }</th>
					<th>${language.getText("ORDER_ID") }</th>
					<th>${language.getText("RAISE_DATE") }</th>
					<th>${language.getText("DUE_DATE") }</th>
					<th>${language.getText("DESCRIPTION") }</th>
					<th>${language.getText("ACTION") }</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="notification" items="${notifications}" varStatus="status">
					<tr>
						<td>${language.getText(notification.notificationType.tablename)}</td>
						<td>${notification.id}</td>
						<td>${notification.userAccount.id}</td>
						<td><a href="#" onClick="doAjaxGet('${statics.get(notification.notificationType.tablename).rootMapping}/show?id=${notification.objectId}')"> ${notification.objectId} </a></td>
						<td><fmt:formatDate value="${notification.raiseDate}" pattern="dd-MM-yyyy hh:mm" /></td>
						<td><fmt:formatDate value="${notification.dueDate}" pattern="dd-MM-yyyy hh:mm" /></td>
						<td>${notification.description}</td>
						<td><a id="approve_${notification.id}" class="approve btn btn-sm" data-id="${notification.id}" href="#" onClick="doAjaxGet('userNotification/complete?id=${notification.id}');">${language.getIcon("COMPLETE")}</a>
							<a id="detail_${notification.id}" class="btn btn-sm" data-id="${notification.id}" href="#" onClick="doAjaxGet('materialRequest/show?id=${notification.objectId}');">${language.getIcon("DETAILS")}</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="box-footer"></div>
</div>

<script type="text/javascript" src="js/dataTables.style.js"></script>

<script type="text/javascript">
$('a.approve_').on('click',function(){
debugger;
var id = $(this).data('id');
url = "userNotification/put/" + id;
reqType = "PUT";
$.ajax({
    type: reqType,
    url: url,
	data:JSON.stringify("{'status' : A}"), 
    cache: false,
    timeout: 600000,
    success: function (data) {

	        console.log("SUCCESS : ", data);
		  },
    error: function (e) {
     console.log("ERROR : ", e);
    }
});
});

</script>
