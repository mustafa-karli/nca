<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link type="text/css" href="css/jquery.treegrid.css" rel="stylesheet"/>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} <BR> ${project.caption} </h3>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="tree table table-bordered table-hover report">
      <thead>
      <tr>
        <th rowspan=2 align="center">${CAPTION}</th>
        <th rowspan=2 align="center">${TREE_CODE}</th>
        <th rowspan=2 align="center">${PROGRESS}</th>
        <th colspan=4 align="center">${MANHOUR}</th>
		<th rowspan=2 align="center">${EARNED_MANHOUR}</th>
        <th rowspan=2 align="center">${PERFORMANCE}</th>
        <th rowspan=2 align="center">${PERFORMANCE_MULTIPLIER}</th>
		<th rowspan=2 align="center">${ESTIMATED_MANHOUR}</th>
        <th rowspan=2 align="center">${REMAINING_COMPLETION_MANHOUR}</th>
        <th rowspan=2 align="center">${DEVIATION}</th>
      </tr>
      <tr>
        <th>${TENDER}</th>
        <th>${PROJECT_APPLICATION_PLAN}</th>
        <th>${PLANNED}</th>
        <th>${REALISED}</th>
       </tr>
      </thead>

		<c:forEach var="record" items="${records}" varStatus="status">
			<c:choose>
				<c:when test = "${record.parentId > 0}">
					<c:set var="parent" value="treegrid-parent-${record.parentId}" />
				</c:when>
				<c:otherwise>
					<c:set var="parent" value="" />
				</c:otherwise>
			</c:choose>
		
			<tr id="${record.id}" class="treegrid-${record.id} ${parent}">
    	    	<td>${record.caption}</td>
    	    	<td>${record.treeCode}</td>
				<td> <c:if test="${record.progress != 0}">         <fmt:formatNumber type="percent" value="${record.progress}" maxFractionDigits="2"/> </c:if> </td>
				<td> <c:if test="${record.workforce != 0}">        <fmt:formatNumber type="number"  value="${record.workforce}" maxFractionDigits="0"/> </c:if> </td>
 				<td> <c:if test="${record.pupWorkforce != 0}">     <fmt:formatNumber type="number"  value="${record.pupWorkforce}" maxFractionDigits="0"/> </c:if> </td>
 				<td> <c:if test="${record.plannedWorkforce != 0}"> <fmt:formatNumber type="number"  value="${record.plannedWorkforce}" maxFractionDigits="0"/> </c:if> </td>
				<td> <c:if test="${record.sumManhour != 0}">       <fmt:formatNumber value="${record.sumManhour}" maxFractionDigits="0"/> </c:if> </td>
				<td> <c:if test="${record.earnedManhour != 0}">    <fmt:formatNumber value="${record.earnedManhour}" maxFractionDigits="0"/> </c:if> </td>
				<td> <c:if test="${record.performans != 0}">       <fmt:formatNumber value="${record.performans}" maxFractionDigits="2"/> </c:if> </td>
				<td> <c:if test="${record.performansMultip != 0}"> <fmt:formatNumber value="${record.performansMultip}" maxFractionDigits="2"/> </c:if> </td>
				<td> <c:if test="${record.estimatedCompMH != 0}">  <fmt:formatNumber value="${record.estimatedCompMH}" maxFractionDigits="0"/> </c:if> </td>
				<td> <c:if test="${record.remainingMH != 0}">      <fmt:formatNumber value="${record.remainingMH}" maxFractionDigits="0"/> </c:if> </td>
				<td> <c:if test="${record.deviation != 0}">        <fmt:formatNumber value="${record.deviation}" maxFractionDigits="2"/> </c:if> </td>
			</tr>
		</c:forEach>
    </table>
    </div>
	<div class="box-footer">
		<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${CANCEL} </a> 
	</div>
</div>

<script type="text/javascript" src="js/jquery.treegrid.min.js"></script>
<script type="text/javascript" src="js/jquery.treegrid.bootstrap3.js"></script>

<!-- <script type="text/javascript" src="jquery/jquery.maskMoney.min.js"></script> -->

<script type="text/javascript" src="js/dataTables.style.js"> </script>

<script>
 
   $('document').ready(function() {
	 //mask money
	    $('.currency').maskMoney({precision:0, thousands:' '}).maskMoney( "mask" );
	//Tree initialization
	    $('.tree').treegrid({
	  	  initialState:'collapsed',
	        expanderExpandedClass: 'glyphicon glyphicon-minus',
	        expanderCollapsedClass: 'glyphicon glyphicon-plus'
	    });
	 	
	});
</script>