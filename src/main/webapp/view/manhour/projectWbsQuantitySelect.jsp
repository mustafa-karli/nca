<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover">
      <thead>
      <tr>
        <th>${language.getText("PRJ_CAPTION")}</th>
        <th>${language.getText("TREE_CODE")}</th>
        <th>${language.getText("CAT_CAPTION")}</th>
        <th>${language.getText("UNIT")}</th>
        <th>${language.getText("METRIC")}</th>
        <th>${language.getText("QUANTITY")}</th>
        <th>${language.getText("SUM_QUANTITY")}</th>
        <th>${language.getText("SUM_MANHOUR")}</th>
        <th>${language.getText("SUM_OVERTIME")}</th>
        <th>${language.getText("BEGDA")}</th>
        <th>${language.getText("ENDDA")}</th>
        <th>${language.getText("LAST_QUANTITY")}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.prjCaption}</td>
          <td>${record.treeCode}</td>
          <td>${record.catCaption}</td>
          <td>${record.unit}</td>
          <td>${record.metric}</td>
          <td>${record.quantity}</td>
          <td>${record.sumQuantity}</td>
          <td>${record.sumManhour}</td>
          <td>${record.sumOvertime}</td>
          <td>${record.begda}</td>
          <td>${record.endda}</td>
          <td>${record.lastQuantity}</td>
          <td>
            <fmt:formatDate value="${record.begda}" var="begda" pattern="dd-MM-yyyy"/>
            <fmt:formatDate value="${record.nextBegda}" var="nextBegda" pattern="dd-MM-yyyy"/>
			<a href="#" onClick="doAjaxGet('projectWbsQuantity/edit?id=${record.id.projectId},${record.id.categoryId},${begda}');" class="btn btn-info"> ${language.getIconText("EDIT")} </a>
			<a href="#" onClick="doAjaxGet('projectWbsQuantity/new?id=${record.id.projectId},${record.id.categoryId},${nextBegda}');" class="btn btn-primary"> ${language.getIconText("NEW")} </a>
		  </td>
        </tr>
      </c:forEach>
    </table>
    </div>
</div>