<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="javascript:checkvisible(0)">Open Search Dialog</a>


<div id="dialog">

	<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>


	<form class="form-horizontal" id="searchForm">

		<div class="box box-primary">
			<div class="box-header with-border">
				<h3 class="box-title">${PAGETITLE}</h3>
				<div class="btn-group pull-right">
					<button type="button" class="btn btn-primary" onclick="doAjaxLoad('${postlink}','#searchForm');">${language.getIconText("SEARCH")}</button>
					<button type="button" class="btn btn-warning" onclick="doAjaxGet('${prevpage}');">${language.getIconText("CANCEL")}</button>
				</div>
			</div>

			<div class="box-body">
				<c:forEach items="${controller.fields}" var="field" varStatus="iCount">
					<div class="col-md-12">
						<div class="form-group">
							<c:choose>
								<c:when test="${!empty filters[iCount.index]}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="hidden" name="${field.fieldName}" id="${field.fieldName}" value="${filters[iCount.index]}"/> ${filters[iCount.index]}
								</c:when>
								<c:when test="${field.editStyle == 'H'}">
								</c:when>
								<c:when test="${field.editStyle == 'G'}">
								</c:when>
								<c:when test="${field.editStyle == 'M'}">
								</c:when>
								<c:when test="${field.editStyle == 'T'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:when test="${field.editStyle == 'S'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control integer" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:when test="${field.editStyle == 'D'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<div class="input-group date">
										<div class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</div>
										<input type="text" class="form-control pull-right" placeholder="DD/MM/YYYY" name="${field.fieldName}" id="${field.fieldName}" />
									</div>
								</c:when>
								<c:when test="${field.editStyle == 'I1'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control integer" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:when test="${field.editStyle == 'I2'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control integer" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:when test="${field.editStyle == 'I4'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control integer" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:when test="${field.editStyle == 'I8'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control integer" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:when test="${field.editStyle == 'C'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control currency" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:when test="${field.editStyle == 'F'}">
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<input type="text" placeholder="${language.getText(field.fieldName)}" class="form-control decimal" name="${field.fieldName}" id="${field.fieldName}" />
								</c:when>
								<c:otherwise>
									<label for="${field.fieldName}"> ${language.getText(field.fieldName)} </label>
									<select name="${field.fieldName}" id="${field.fieldName}" class="form-control select2" style="width: 100%;">
										<option value=""></option>
										<c:forEach var="option" items="${selectOptions[iCount.index]}" varStatus="status">
											<option value="${option.key}">${option.value}</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="box-footer">
				<button type="button" class="btn btn-primary" onclick="doAjaxLoad('${postlink}','#searchForm');">${language.getIconText("SEARCH")}</button>
				<button type="button" class="btn btn-warning" onclick="doAjaxGet('${prevpage}');">${language.getIconText("CANCEL")}</button>
			</div>
		</div>

	</form>

</div>

<div id="searchResult">
</div>


<script>
	$(document).ready(function() {
		$(".select2").select2();
		$('.date').datepicker({
			autoclose : true,
			format : 'dd-mm-yyyy'
		});
	});
</script>

<script>
	console.log(navigator.userAgent);
	$(".integer").inputmask({
		alias : 'integer',
		groupSeparator : ' ',
		autoGroup : true,
		digits : 0,
		digitsOptional : true,
		rightAlign : false
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

	jQuery(function($) {
		var val = $('#dialog').dialog({
			autoOpen : false,
			buttons : {
				"OK" : function() {
					$(this).dialog("close");
					$(this).trigger('confirm');
				},
				"cancel" : function() {
					$(this).dialog("close");
					$(this).trigger('cancel');
				}
			}
		});
	})

	function openTo(Id_, width_, okCallback, cancelCallback) {
		var divId = $('#' + Id_);
		divId.dialog('option', 'width', parseInt(width_));
		divId.dialog('option', 'show', 'clip');
		divId.dialog('option', 'hide', 'clip');
		divId.dialog('option', 'zIndex', 1000);
		divId.dialog('open').off('confirm cancel').on('confirm', okCallback)
				.on('cancel', cancelCallback);
	}

	function showbox(okCallback, cancelCallback) {
		openTo('dialog', 200, okCallback, cancelCallback)
	}

	function checkvisible(notes) {
		if (notes) {
			// do something 
		} else {
			showbox(function() {
				alert('ok')
			}, function() {
				alert('cancelled')
			});
		}
	}
</script>

