<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="subcontractor/show?id=${record.subcontractor.id}"/>

	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"> ${SUBCONRACTOR_ID} </label>
			<div class="col-sm-10">  
				${record.subcontractor.id}
				<form:input type="hidden" path="subcontractor.id"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"> ${WORKER_ID} </label>
			<div class="col-sm-10">  
				${record.id}
				<form:input type="hidden" path="id"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="caption"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="citizenShip">${CITIZENSHIP}</label>
			<div class="col-sm-10">
				<form:select name="citizenShip" path="citizenShip"  class="form-control select2" items="${COUNTRY_LIST}"/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('subcontractor/show?id=${record.subcontractor.id}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>
