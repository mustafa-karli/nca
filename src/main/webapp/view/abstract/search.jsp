<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form class="form-horizontal" method="post" id="f">

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${PAGETITLE} ${language.getText('SEARCH')}</h3>
			<div class="btn-group pull-right">
				<button type="button" class="btn btn-primary"
					onClick="doAjaxGet('${controller.rootMapping}/new');">${language.getIconText("NEW")}</button>
				<button type="button" class="btn btn-primary"
					onclick="doAjaxPost('${postlink}');">${language.getIconText("SEARCH")}</button>
				<button type="button" class="btn btn-warning"
					onclick="doAjaxGet('${prevpage}');">${language.getIconText("CANCEL")}</button>
			</div>
		</div>

		<div class="box-body">
			<c:forEach items="${controller.fields}" var="field"
				varStatus="iCount">
				<c:choose>
					<c:when test="${field.editStyle == 'H'}">
					</c:when>
					<c:when test="${field.editStyle == 'G'}">
					</c:when>
					<c:when test="${field.editStyle == 'M'}">
					</c:when>
					<c:otherwise>
						<div class="col-md-6">
							<div class="form-group">
								<c:choose>
									<c:when test="${field.editStyle == 'T'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-md-8 col-sm-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'S'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control integer" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'D'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<div class="input-group date">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" class="form-control pull-right"
													placeholder="dd-mm-yyyy" name="${field.fieldName}"
													id="${field.fieldName}" />
											</div>
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'I1'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control integer" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'I2'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control integer" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'I4'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control integer" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'I8'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control integer" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'C'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control currency" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:when test="${field.editStyle == 'F'}">
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<input type="text"
												placeholder="${language.getText(field.fieldName)}"
												class="form-control decimal" name="${field.fieldName}"
												id="${field.fieldName}" />
										</div>
									</c:when>
									<c:otherwise>
										<label for="${field.fieldName}"
											class="col-sm-4 col-md-4 control-label">
											${language.getText(field.fieldName)} </label>
										<div class="col-sm-8 col-md-8">
											<select name="${field.fieldName}" id="${field.fieldName}"
												class="form-control select2" style="width: 100%;">
												<option value=""></option>
												<c:forEach var="option"
													items="${selectOptions[iCount.index]}" varStatus="status">
													<option value="${option.key}">${option.value}</option>
												</c:forEach>
											</select>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>

		<div class="box-footer">
			<a href="#" onclick="doAjaxGet('${controller.rootMapping}/new');"
				class="btn btn-primary">${language.getIconText("NEW")}</a> <a
				href="#" onclick="doAjaxPost('${postlink}');"
				class="btn btn-primary">${language.getIconText("SEARCH")}</a> <a
				href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
		</div>
	</div>

</form>

<script>
	$(document).ready(function() {
		$(".select2").select2();
		$('.date').datepicker({
			autoclose : true,
			format : 'dd-mm-yyyy'
		});
	});

	console.log(navigator.userAgent);
	$(".integer").inputmask( {
		alias: 'integer',		
		groupSeparator: ' ',
		autoGroup: true, 
		digits: 0, 
		digitsOptional: true,
		rightAlign: false
	});
	$(".decimal").inputmask({
		alias : 'decimal',
		groupSeparator : ' ',
		autoGroup : true,
		digits : 2,
		digitsOptional : true,
		rightAlign : false
	});
	$(".currency").inputmask({
		alias : 'decimal',
		groupSeparator : ' ',
		autoGroup : true,
		digits : 2,
		digitsOptional : true,
		rightAlign : false
	});
	$(".percentage").inputmask({
		alias : 'numeric',
		autoGroup : false,
		integerDigits : 3,
		digits : 2,
		min : 0,
		max : 100,
		digitsOptional : true,
		rightAlign : false
	});
</script>
