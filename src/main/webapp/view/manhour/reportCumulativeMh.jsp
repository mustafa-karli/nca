<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="box box-primary">

	<form name="f" id="f" method="post">
	<input type=hidden name=projectId value="${projectId}">
	</form>

	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<p> ${project.caption} <BR> </p>		
		<c:if test= "${unapprovedDate > ' '}">
			<p style="color:red"> Unapproved Record Date is: ${unapprovedDate} <BR></p>
		</c:if>
	</div>
 	<div class="box-body">
    
    <table id="dataTable3" class="table table-bordered table-hover table-editable report">
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
<script type="text/javascript" src="j/dataTables.style.js"> </script>
