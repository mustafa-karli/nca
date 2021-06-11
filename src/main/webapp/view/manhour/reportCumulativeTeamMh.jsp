<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="box box-primary">
	<form name="f" id="f" method="post">
	<input type=hidden id=projectId name=projectId value="${projectId}">
	<input type=hidden id=begda name=begda value="${begda}">
	<input type=hidden id=endda name=endda value="${endda}">

	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<p> <BR> ${project.caption} </p>
		<c:if test= "${unapprovedDate > ' '}">
			<p style="color:red"> Unapproved Record Date is: ${unapprovedDate} <BR></p>
		</c:if>
		<p>
			<c:choose>
			<c:when test="${empty ISTEAM}">	
			 	${SUBCONTRACTOR}
				<select name=subcontractorId id=subcontractorId onChange="doAjaxPost('report/subcontractorMh');">
				<option value=""> - </option>
				<c:forEach var="team" items="${project.projectTeams}">
					<c:forEach var="person" items="${team.projectTeamPersonnel}">
						<c:if test="${person.teamLead == 1}">
							<c:choose>
								<c:when test="${person.worker.subcontractor.id == subcontractorId}">
									<option value="${person.worker.subcontractor.id}" selected> ${person.worker.subcontractor.caption} </option>
								</c:when>
								<c:otherwise>
									<option value="${person.worker.subcontractor.id}"> ${person.worker.subcontractor.caption} </option>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
				</c:forEach>
				</select>
			</c:when>
			
			<c:otherwise>
				${PROJECT_TEAM}
				<select name=teamId id=teamId onChange="doAjaxPost('report/teamMh');">
				<option value=""> - </option>
				<c:forEach var="team" items="${project.projectTeams}">
					<c:choose>
						<c:when test="${team.id.teamId == teamId}">
							<option value="${team.id.teamId}" selected> ${team.caption}
							<c:forEach var="person" items="${team.projectTeamPersonnel}">
								<c:if test="${person.teamLead == 1}"> - ${person.worker.caption} </c:if>
							</c:forEach>
							</option>
						</c:when>
						<c:otherwise>
							<option value="${team.id.teamId}"> ${team.caption}
							<c:forEach var="person" items="${team.projectTeamPersonnel}">
								<c:if test="${person.teamLead == 1}"> - ${person.worker.caption} </c:if>
							</c:forEach>
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</select>
			</c:otherwise>
			</c:choose>
		</p>
	</div>
	</form>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover table-editable">
      <thead>
      <tr>
        <th colspan=2 align="center">${LOCAL}</th>
        <th colspan=2 align="center">${CUSTOMER}</th>
        <th rowspan=2 align="center">${UNIT}</th>
        <th colspan=3 align="center">${term} ${REALISED} </th>
        <th colspan=3 align="center">${TOTAL} ${REALISED} <fmt:formatDate value="${project.contractDate}" pattern="dd-MM-yyyy"/> .. </th>
        <th rowspan=2 align="center">${PUP_METRIC}</th>
        <th rowspan=2 align="center">${PLANNED_METRIC}</th>
      </tr>
      <tr>
      	<th>${CODE}</th>
      	<th>${CAPTION}</th>
        <th>${CODE}</th>
      	<th>${CAPTION}</th>
        <th>${WORKFORCE}</th>
        <th>${QUANTITY}</th>
        <th>${METRIC}</th>
        <th>${WORKFORCE}</th>
        <th>${QUANTITY}</th>
        <th>${METRIC}</th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.treeCode}</td>
          <td>${record.catCaption}</td>
          <td>${record.customerWbsCode}</td>
          <td>${record.customerWbsCaption}</td>
          <td>${record.unit}</td>
          <td> <c:if test="${record.termWorkforce != 0}"> ${record.termWorkforce} </c:if> </td>
          <td> <c:if test="${record.termQuantity != 0}"> ${record.termQuantity} </c:if> </td>
          <td> <c:if test="${record.termMetric != 0}"> <fmt:formatNumber value="${record.termMetric}" maxFractionDigits="2"/> </c:if> </td>
          <td> <c:if test="${record.sumWorkforce != 0}"> ${record.sumWorkforce} </c:if> </td>
          <td> <c:if test="${record.sumQuantity != 0}"> ${record.sumQuantity} </c:if> </td>
          <td> <c:if test="${record.sumMetric != 0}"> <fmt:formatNumber value="${record.sumMetric}" maxFractionDigits="2"/> </c:if> </td>
          <td> <c:if test="${record.pupMetric != 0}"> ${record.pupMetric} </c:if> </td>
          <td> <c:if test="${record.plannedMetric != 0}"> ${record.plannedMetric} </c:if> </td>
        </tr>
      </c:forEach>
    </table>
    </div>
	<div class="box-footer">
		<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${CANCEL} </a> 
	</div>
</div>

<script> 
	$(document).ready(function () {$('#dataTable1').DataTable({
							 	'paging':false, 
							 	'scrollY':'50vh',
							 	'lengthChange':false, 
							 	'searching':false, 
							 	'ordering':true, 
							 	'info':true, 
							 	'autoWidth': true ,
							 	'responsive': true,
							 	'scrollCollapse': true,
							 	dom: 'Bfrtip',
							    buttons: [
							        'copyHtml5',
							        'excelHtml5',
							        'csvHtml5',
							        {
						                "extend": "pdfHtml5",
						                "orientation": "landscape",
						                "text" : "PDF"		                    
						            },
						            'print'
							    ]
			}).columns.adjust().draw();
	 	});
</script>