<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>

	<div class="box-body">
		<form:form class="form-horizontal" method="post" modelAttribute="record" id="f" enctype="multipart/form-data">
			<input type="HIDDEN" name="nextpage" value="${prevpage}">

			<div class="col-md-12">
				<label for="materialTypeId">${language.getText("PRODUCT_TYPE")} </label>
				<div class="form-group">
					<select name="materialTypeId" id="materialTypeId" class="form-control required" style="width: 100%;" onchange="doAjaxGet('${getlink}?materialTypeId=' + document.getElementById('materialTypeId').value)">
						<c:forEach var="m" items="${MATERIAL_TYPE}">
							<c:set var="slct" value=""></c:set>
							<c:if test="${materialTypeId == m.key}">
								<c:set var="slct" value=" selected"></c:set>
							</c:if>
							<option value="${m.key}" ${slct}>${m.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<c:if test="${!empty productTypeByDefine}">
				<div class="col-md-12">
					<label for="materialId">${language.getText("PRODUCT_BY_DEFINE")} </label>
					<div class="form-group">
						<select name="materialId" id="materialId" class="form-control required" style="width: 100%;" onchange="doAjaxGet('${getlink}?materialTypeId=' + document.getElementById('materialTypeId').value + '&materialId=' + document.getElementById('materialId').value)">
							<option value=""></option>
							<c:forEach var="m" items="${productTypeByDefine.productByDefines}">
								<c:set var="slct" value=""></c:set>
								<c:if test="${materialId == m.material.id}">
									<c:set var="slct" value=" selected"></c:set>
								</c:if>
								<option value="${m.material.id}" ${slct}>${m.material.caption}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<c:if test="${!empty materialId}">
				
					<c:set var="defaultUnit" value="${material.defaultUnit}"></c:set>
					<c:if test="${empty defaultUnit}">
						<c:set var="defaultUnit" value="EA"></c:set>
					</c:if>

					<c:forEach var="g" items="${productTypeByDefine.materialType.materialTypeAttributes}" varStatus="gIndex">

						<div class="col-md-6">
							<label for="magVal${gIndex.index}">${language.getText(g.materialAttributeGroup.caption)} </label>
							<div class="form-group">
								<input type="hidden" name="magId${gIndex.index}" id="magId${gIndex.index}" value="${g.materialAttributeGroup.id}" /> <select name="magVal${gIndex.index}" id="magVal${gIndex.index}" class="form-control select2 required" style="width: 100%;">
									<c:forEach var="v" items="${g.materialAttributeGroup.materialAttributeOptions}">
										<option value="${v.id.low}">${v.caption}</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</c:forEach>

					<div class="col-md-3">
						<label for="quantity">${language.getText("DELIVERY_DATE")} </label>
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right required" placeholder="DD-MM-YYYY" name="dueDate" id="dueDate" />
						</div>
					</div>

					<div class="col-md-3">
						<label for="quantity">${language.getText("QUANTITY")} </label>
						<div class="form-group">
							<input type="text" name="quantity" id="quantity" class="form-control integer required" />
						</div>
					</div>

					<div class="col-md-3">
						<label for="unit">${language.getText("UNIT")} </label>
						<div class="form-group">
							<select name="unit" id="unit" class="form-control select2 required" style="width: 100%;">
								<c:forEach var="v" items="${MEASUREMENT_UNIT}">
									<c:if test="${v.key == defaultUnit}">
										<c:set var="slct" value=" selected"></c:set>
									</c:if>
									<option value="${v.key}" ${slct}>${v.value}</option>
									<c:set var="slct" value=""></c:set>
								</c:forEach>
							</select>
						</div>
					</div>

				</c:if>

				<div class="col-md-3">
					<label for="unit">${language.getText("PHOTO")} </label>
					<div class="form-group">
						<input type="file" name="binFile" id="binFile" class="${contentRequired}">
					</div>
				</div>

				<div class="col-md-12">
					<label for="description">${language.getText("DESCRIPTION")} </label>
					<div class="form-group">
						<input type="text" name="description" id="description" class="form-control" />
					</div>
				</div>

			</c:if>

		</form:form>

	</div>

	<div class="box-footer">
		<a href="#" onclick="dateControl()" class="btn btn-primary">${language.getIconText("SAVE")}</a> <a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CLOSE")}</a>
	</div>

</div>

<script>

function dateControl(){
	var TodayDate = new Date();
	var dueDate = new Date( $("#dueDate").val().replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );

	if ( dueDate  < TodayDate ){ alert("Bugünden önceki bir tarihe sipariş veremezsiniz!");}
	else {doAjaxMullPost('${postlink}', '#f');}
}


	function doAjaxMullPost(target, formName) {
		debugger;
 		var stop = 0;
		$('input.required').each(function(){
			if($(this).val() == '') {
				$(this).addClass('requiredFalse');
				stop=1;
			} else
				$(this).removeClass('requiredFalse');
		});

		if (stop == 0) {
			var formData = new FormData($(formName)[0]);
			$.ajax({
				type : "POST",
				url : target,
				data : formData,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(response) {
					$("#content").html(response);
				}
			});
		}
	}

	$(document).ready(function() {
		$('.required').attr({
			'placeholder' : '${REQUIRED}'
		});
		$(".select2").select2();
		$('.date').datepicker({
			autoclose : true,
			format : 'dd-mm-yyyy'
		});
	});
</script>

<script>
	console.log(navigator.userAgent);
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
