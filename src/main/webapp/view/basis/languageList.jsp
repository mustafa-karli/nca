<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<c:if test="${!empty INSERT_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('language/new');"> ${NEW} </a>
		</c:if>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${LANGCODE}</th>
        <th>${CAPTION}</th>
        <th>${DIRECTION}</th>
        <th>${FLAG}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.id}</td>
          <td> <a href="#" onClick="doAjaxGet('language/show?id=${record.id}');"> ${record.caption} </a> </td>
          <td>${record.direction}</td>
          <td><span class="flag-icon ${record.flag}"></span> ${record.flag} </td>
			<td>
				<c:if test="${!empty UPDATE_ALLOWED}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('language/edit?id=${record.id}');"> ${EDIT} </a>
				</c:if>
				<c:if test="${!empty DELETE_ALLOWED}">
					<a class="btn btn-danger" href="#" onClick="doAjaxGet('language/delete?id=${record.id}');"> ${DELETE} </a>
				</c:if>
			</td>
        </tr>
      </c:forEach>             
    </table>
    </div>
</div>
