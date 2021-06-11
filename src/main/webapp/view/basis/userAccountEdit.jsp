<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f" name="f">

	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"> ${language.getText("USERNAME")} </label>
			<div class="col-sm-10">  
				<form:input class="form-control" path="id"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="firstName">${language.getText("FIRST_NAME")}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="firstName"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="lastName">${language.getText("LAST_NAME")}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="lastName"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="emailAddress">${language.getText("EMAIL_ADDRESS")}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="emailAddress"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="passtext">${language.getText("PASSWORD")}</label>
			<div class="col-sm-10"> 
				<form:password class="form-control" path="passtext"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="repeatPassword">${language.getText("PASSWORD")}</label>
			<div class="col-sm-10"> 
				<input type=password id="repeatPassword" name="repeatPassword" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="status">${language.getText("STATUS")}</label>
			<div class="col-sm-10"> 
				<form:select class="form-control" path="status" items="${selectOptions[1]}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label">${language.getText("LASTLOGON")}</label>
			<div class="col-sm-10"> 
				${record.lastlogon}
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label">${language.getText("PASSDATE")}</label>
			<div class="col-sm-10"> 
				${record.passdate}
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="if (document.f.passtext.value == document.f.repeatPassword.value) {doAjaxPost('${postlink}');} else {alert('passwords not equal');}" class="btn btn-primary">${language.getText("SAVE")}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getText("CANCEL")}</a>
	</div>

	</form:form>
</div>
