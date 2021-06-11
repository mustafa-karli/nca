<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- 
<form class="form-horizontal" method="post" id="f" name="f">

<input type=hidden name=nextpage value="${nextpage}">
<input type=hidden name=parentKey value="${parentKey}">
<input type=hidden name=personIds value="">
<input type=hidden name=operation value="SEARCH">

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<div class="box-body">
	
		<div class="form-group">
			<label class="col-sm-2 control-label" for="organizationId"> ${ORGANIZATION_ID} </label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="organizationId" value="${organizationId}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="organizationName">${ORGANIZATION_NAME}</label>
			<div class="col-sm-10"> 
				<input type=text class="form-control" name="organizationName" value="${organizationName}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="company">${COMPANY}</label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="company" value="${company}"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="locationId"> ${LOCATION_ID} </label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="locationId" value="${locationId}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="locationName">${LOCATION_NAME}</label>
			<div class="col-sm-10"> 
				<input type=text class="form-control" name="locationName" value="${locationName}"/>
			</div>
		</div>

		

	</div>

	<div class="box-footer">
		<a href="#" class="btn btn-primary" onclick="doAjaxPost('worker/selectPerson');">${SEARCH}</a>
		<a href="#" class="btn btn-primary" onClick="findSelected('PERSON_IDS');doAjaxPost('worker/selectPerson');"> ${CHOOSE} </a>
		<a href="#" class="btn btn-warning" onclick="doAjaxGet('${prevpage}');"> ${CANCEL} </a>
	</div>

</div>

</form>
 --%>
${DATATABLE1}

<div class="box box-primary">
	<div class="box-body">
    
	<table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th>${ORGANIZATION_ID}</th>
			<th>${ORGANIZATION_NAME}</th>
			<th>${COUNTRY}</th>
			<th>${LOCATION_ID}</th>
			<th>${LOCATION_NAME}</th>
		</tr>
		</thead>

		<c:forEach var="r" items="${records}" varStatus="status">
		<tr>
			<td>${r.organizationId}</td>
			<td>${r.organizationName}</td>
			<td>${r.country}</td>
			<td>${r.locationId}</td>
			<td>${r.locationName}</td>
		</tr>
      </c:forEach> 
    </table>
    </div>
</div>
