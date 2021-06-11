<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
  $(function () {
    //Initialize Select2 Elements
    $(".select2").select2();

 	//Date picker
    $('#startDate').datepicker({
      autoclose: true
    });
  });
</script>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<div class="box-body">
    
		<form name="f" id="f">

		<table class="table table-bordered table-hover">

			<tr>
				<th> ${PROJECT}	</th>
				<td>
					<select name=projectId id=projectId onChange="doAjaxGet('report/list?projectId=' + document.f.projectId.value + '&begda=' + document.f.begda.value + '&endda=' + document.f.endda.value);">
					<c:forEach var="project" items="${projects}">
						<c:choose>
							<c:when test="${project.id == projectId}">
							<option value="${project.id}" selected> ${project.caption} </option>
							</c:when>
							<c:otherwise>
							<option value="${project.id}"> ${project.caption} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<th> ${PERIOD}</th>
				<td><input type="text" name="year" id="year" value="${year}" onChange="var d1=new Date(year.value,month.value-1,1);var d2=new Date(year.value,month.value,0);begda.value= d1.getFullYear()+'-'+(d1.getMonth() < 9 ? '0' + (d1.getMonth() + 1) : (d1.getMonth() + 1))+'-'+(d1.getDate() < 10 ? '0' + d1.getDate() : d1.getDate());endda.value= d2.getFullYear()+'-'+(d2.getMonth() < 9 ? '0' + (d2.getMonth() + 1) : (d2.getMonth() + 1))+'-'+(d2.getDate() < 10 ? '0' + d2.getDate() : d2.getDate());">
					<select id="month" name="month" onChange="var d1=new Date(year.value,month.value-1,1);var d2=new Date(year.value,month.value,0);begda.value= d1.getFullYear()+'-'+(d1.getMonth() < 9 ? '0' + (d1.getMonth() + 1) : (d1.getMonth() + 1))+'-'+(d1.getDate() < 10 ? '0' + d1.getDate() : d1.getDate());endda.value= d2.getFullYear()+'-'+(d2.getMonth() < 9 ? '0' + (d2.getMonth() + 1) : (d2.getMonth() + 1))+'-'+(d2.getDate() < 10 ? '0' + d2.getDate() : d2.getDate());">
					<c:forEach var="m" items="${MONTHS_LIST}">
						<c:choose>
							<c:when test="${month == m.key}">
							<option value="${m.key}" selected> ${m.value} </option>
							<c:set var="MONTH_NAME" value="${m.value}"></c:set>
							</c:when>
							<c:otherwise>
							<option value="${m.key}"> ${m.value} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<th> ${BEGDA} ${ENDDA} </th>
				<td> <input type="date" name="begda" id="begda" value="${begda}"> - <input type="date" name="endda" id="endda" value="${endda}"> </td>
			</tr>

			<tr>
			<td colspan=2>
			<c:forEach var="report" items="${reports}" varStatus="status">
			<a href="#"  class="btn btn-primary" onclick="doAjaxPost('${report.value}');"> ${report.key} </a> &nbsp;
			</c:forEach>
			</td>
			</tr>
		</table>

		</form>
    </div>
</div>