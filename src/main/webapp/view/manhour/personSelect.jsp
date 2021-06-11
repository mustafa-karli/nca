<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
function findSelected(cname) {
	var c = document.getElementsByName(cname);
	var x = "";
	for (var i in c) {
		if (c[i].checked)
			x = x + "," + c[i].value;
	}
	document.f.personIds.value = x;
	document.f.operation.value='CHOOSE';
}

function hideShow() {
    var x = document.getElementById('boxBody');
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}

$("#checkAll").click(function () {
    $('input:checkbox').not(this).prop('checked', this.checked);
});

$(function () {
    //Initialize Select2 Elements
    $(".select2").select2();
  });

</script>


<form class="form-horizontal" method="post" id="f" name="f">

<input type=hidden name=nextpage value="${nextpage}">
<input type=hidden name=parentKey value="${parentKey}">
<input type=hidden name=personIds value="">
<input type=hidden name=operation value="SEARCH">

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<div class="box-body" id="boxBody">
	
		<div class="form-group">
			<label class="col-sm-2 control-label" for="personId"> ${recordHeaders[0]} </label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="PERSON_ID" value="${personId}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="firstName">${recordHeaders[1]}</label>
			<div class="col-sm-10"> 
				<input type=text class="form-control" name="FIRST_NAME" value="${firstName}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="lastName">${recordHeaders[2]}</label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="LAST_NAME" value="${lastName}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="nationalityId">${recordHeaders[3]}</label>
			<div class="col-sm-10">
				<select class="form-control select2" name="NATIONALITY_ID" id="NATIONALITY_ID">
					<option value=""> - </option>
					<c:forEach var="c" items="${COUNTRY}" varStatus="status">
						<c:choose>
							<c:when test="${nationalityId == c.key}">
							<option value="${c.key}" selected> ${c.value} </option>
							</c:when>
							<c:otherwise>
							<option value="${c.key}"> ${c.value} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="email">${recordHeaders[4]}</label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="EMAIL" value="${email}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="employeeNumber">${recordHeaders[5]}</label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="EMPLOYEE_NUMBER" value="${employeeNumber}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="supervisor">${recordHeaders[6]}</label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="SUPERVISOR" value="${supervisor}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="position">${recordHeaders[9]}</label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="POSITION" value="${position}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="ORGANIZATION_ID">${recordHeaders[8]}</label>
			<div class="col-sm-10"> 
			<select class="form-control select2" name="ORGANIZATION_ID" id="ORGANIZATION_ID">
				<c:forEach var="c" items="${ORGANIZATION}" varStatus="status">
				<c:choose>
					<c:when test="${organizationId == c.key}">
						<option value="${c.key}" selected> ${c.value} </option>
					</c:when>
					<c:otherwise>
						<option value="${c.key}"> ${c.value} </option>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</select>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="subcontractor">${recordHeaders[10]}</label>
			<div class="col-sm-10">
				<form:input class="form-control" path="subcontractor" />
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" class="btn btn-primary" onclick="document.f.operation.value='SEARCH';doAjaxPost('worker/selectPerson');">${SEARCH}</a>
		<a href="#" class="btn btn-primary" onClick="findSelected('PERSON_IDS');doAjaxPost('worker/selectPerson');"> ${CHOOSE} </a>
		<a href="#" class="btn btn-warning" onclick="doAjaxGet('${prevpage}');"> ${CANCEL} </a>
		<a href="#" class="btn btn-warning" onclick="hideShow()"> Sakla/Göster </a>
	</div>

</div>

</form>

${DATATABLE1}

<div class="box box-primary">
	<div class="box-body">
    
	<table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th>${recordHeaders[0]}</th>
			<th>${recordHeaders[1]}</th>
			<th>${recordHeaders[2]}</th>
			<th>${recordHeaders[3]}</th>
			<th>${recordHeaders[4]}</th>
			<th>${recordHeaders[5]}</th>
			<th>${recordHeaders[6]}</th>
			<th>${recordHeaders[7]}</th>
			<th>${recordHeaders[8]}</th>
			<th>${recordHeaders[9]}</th>
			<th><input type=checkbox id="checkAll" name="checkAll"/></th>
		</tr>
		</thead>

		<c:forEach var="r" items="${records}" varStatus="status">
		<tr>
			<td>${r.personId}</td>
			<td>${r.firstName}</td>
			<td>${r.lastName}</td>
			<td>${r.nationalityId}</td>
			<td>${r.email}</td>
			<td>${r.employeeNumber}</td>
			<td>${r.supervisor}</td>
			<td>${r.organizationId}</td>
			<td>${r.organization}</td>
			<td>${r.position}</td>
			<td> <input type=checkbox name="PERSON_IDS" value="${r.personId}" /> </td>
		</tr>
      </c:forEach> 
    </table>
    </div>
</div>
