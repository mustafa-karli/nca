<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="box box-primary">
	<form name="f" id="f">

	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<input type=hidden name=operation value="SEARCH">
		<input type=hidden name="begda" id="begda" value="${begda}">
		<input type=hidden name="endda" id="endda" value="${endda}">
		<input type=hidden name="nextpage" id="nextpage" value="projectWbsQuantity/select?projectId=${projectId}&year=${year}&month=${month}">

		<select name=projectId id=projectId onChange="doAjaxGet('projectWbsQuantity/select?projectId=' + document.f.projectId.value + '&year=' + document.f.year.value + '&month=' + document.f.month.value);">
			<option value="" selected> ${language.getIconText("CHOOSE")} </option>
	    <c:forEach var="project" items="${projects}" varStatus="status">
		<c:choose>
			<c:when test="${projectId == project.id}">
				<option value="${project.id}" selected> ${project.caption} </option>
			</c:when>
			<c:otherwise>
				<option value="${project.id}"> ${project.caption} </option>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		</select>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover table-editable">
      <thead>
      <tr>
        <th rowspan=2 align="center">${language.getText("TEAM_CAPTION")}</th>
        <th rowspan=2 align="center">${language.getText("CAT_CAPTION")}</th>
        <th rowspan=2>${language.getText("UNIT")}</th>
<!--    <th colspan=3 align="center">${TENDER}</th>  -->
        <th colspan=3 align="center">${language.getText("REALISED")}</th>
        <th colspan=2 align="center">${language.getText("LAST")}</th>
        <th align="center">${language.getText("NEW")} ${language.getText("QUANTITY")}</th>
      </tr>
      <tr>
<!--    <th>${METRIC}</th>
        <th>${QUANTITY}</th>
        <th>${WORKFORCE}</th>  -->
        <th>${language.getText("METRIC")}</th>
        <th>${language.getText("QUANTITY")}</th>
        <th>${language.getText("MANHOUR")}</th>
        <th>${language.getText("DATE")}</th>
        <th>${language.getText("LAST_QUANTITY")}</th>
		<th><input type="text" name="year" id="year" value="${year}" size=4 onChange="doAjaxGet('projectWbsQuantity/select?projectId=' + document.f.projectId.value + '&year=' + document.f.year.value + '&month=' + document.f.month.value);">
			<select id="month" name="month" id="month" onChange="doAjaxGet('projectWbsQuantity/select?projectId=' + document.f.projectId.value + '&year=' + document.f.year.value + '&month=' + document.f.month.value);">
			<c:forEach var="m" items="${MONTHS}">
				<c:if test="${!empty m.key}">
					<c:if test="${month == m.key}">
						<c:set var="selected" value="selected"></c:set>	
						<c:set var="MONTH_NAME" value="${m.value}"></c:set>
					</c:if>
				</c:if>
				<option value="${m.key}" ${selected}> ${m.value} </option>
				<c:set var="selected" value=""></c:set>	
			</c:forEach>
			</select>
		</th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <th>${record.teamCaption} <input type="hidden" name="teamId${status.index}" id="teamId${status.index}" value="${record.id.teamId}"> </th>
          <th>${record.treeCode} ${record.catCaption} <input type="hidden" name="categoryId${status.index}" id="categoryId${status.index}" value="${record.id.categoryId}"> </th>
          <th>${record.unit}</th>
<!--      <th> <c:if test="${record.metric != 0}">       ${record.metric} </c:if> </th>
          <th> <c:if test="${record.quantity != 0}">     ${record.quantity} </c:if> </th>
          <th> <c:if test="${record.workforce != 0}">    ${record.workforce} </c:if> </th>  -->
          <th><c:if test="${record.sumQuantity != 0}">   <fmt:formatNumber value="${record.sumManhour/record.sumQuantity}" maxFractionDigits="2"/> </c:if> </th>
          <th> <c:if test="${record.sumQuantity != 0}">  ${record.sumQuantity} </c:if> </th>
          <th> <c:if test="${record.sumManhour != 0}">   ${record.sumManhour} </c:if> </th>
		  <th> <fmt:formatDate value="${record.begda}" pattern="MM-yyyy"/><input type="hidden" name="lastEndda${status.index}" id="lastEndda${status.index}" value="${record.endda}"></th>
          <th>	<c:if test="${record.lastQuantity != 0}">
          		 ${record.lastQuantity}
          		 <a href="#" onClick="doAjaxGet('projectWbsQuantity/delete?id=${projectId},${record.id.categoryId},${record.id.teamId},<fmt:formatDate value="${record.begda}" pattern="dd/MM/yyyy"/>&nextpage='+document.f.nextpage.value);"> ${language.getIconText("DELETE")} </a>
          		</c:if>
          </th>
          <td> <input type="text" name="quantity${status.index}" id="quantity${status.index}"> </td>
        </tr>
      </c:forEach>
    </table>
    </div>
	<div class="box-footer">
			<a href="#" class="btn btn-primary" onClick="doAjaxPost('${postlink}');"> ${language.getIconText("SAVE")} </a>
			<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${language.getIconText("CANCEL")} </a> 
	</div>
	</form>
</div>
