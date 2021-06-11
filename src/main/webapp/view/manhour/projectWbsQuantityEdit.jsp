<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="projectWbs/show?id=${record.id.projectId},${record.id.categoryId}"/>

	<div class="box-body">
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.projectId">${PROJECT_ID}</label>
			<div class="col-sm-10"> 
				<form:input type=hidden path="id.projectId" /> ${record.project.caption}
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.categoryId">${CATEGORY_ID}</label>
			<div class="col-sm-10"> 
				<form:input type=hidden path="id.categoryId" /> ${record.category.caption}
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.teamId">${TEAM_ID}</label>
			<div class="col-sm-10"> 
				<form:input type=hidden path="id.teamId" /> ${record.projectTeam.caption}
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.begda">${BEGDA}</label>
			<div class="col-sm-10"> 
				<form:input type=hidden class="form-control" path="id.begda" /> ${record.id.begda}
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="endda">${ENDDA}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="endda" type="date"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="quantity">${QUANTITY}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="quantity"/> ${record.projectWbs.unit}
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="status">${STATUS}</label>
			<div class="col-sm-10"> 
				<form:input type=hidden path="status" /> record.status
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>