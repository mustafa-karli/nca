<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<link href="/s/nauticana.table.css" rel="stylesheet" type="text/css" />

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${language.getText('FAST_ENTRY')}</h3>
	</div>

	<div class="box-body">

		<form class="form-horizontal" method="post" name="f" id="f">
			<input type="HIDDEN" name="nextpage" value="${prevpage}">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">${language.getText('PRODUCT')}</h3>
				</div>

				<div class="box-body">
					<div class="col-md-6">
						<div class="form-group">
							<label for="caption" class="col-md-4 col-sm-4 control-label">${language.getText("CAPTION")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" class="form-control required" name="caption" id="caption" />
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label for="partNumber" class="col-md-4 col-sm-4 control-label">${language.getText("PART_NUMBER")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" class="form-control required" name="partNumber" id="partNumber" />
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label for="materialGroupId" class="col-md-4 col-sm-4 control-label">${language.getText("MATERIAL_GROUP")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="hidden" name="materialGroupId" value="" />
								<c:if test="${!empty productGroups}">
								<div class="dropdown">
									<a data-toggle="dropdown" href="#" id="mgLink"> Select Product Group <span class=caret></span> </a>
									<ul class="dropdown-menu">
										<ncaTags:productGroupOptions productGroups="${productGroups}" language="${language}" />
									</ul>
								</div>
								</c:if>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
					<c:set var="maxMag" value="0"></c:set>
					<c:forEach var="mag" items="${mags}" varStatus="magInd">
						<c:choose>
							<c:when test="${fn:length(mag.materialAttributeOptions) > 1}">
								<label for="mag${magInd.index}" class="col-md-4 col-sm-4 control-label">${mag.caption} </label>
								<div class="col-md-8  col-sm-8">
								<select class="form-control col-md-8  col-sm-8" name="mag${magInd.index}" id="mag${magInd.index}">
									<option> </option>
									<c:forEach var="opt" items="${mag.materialAttributeOptions}" varStatus="optInd">
										<option value="${opt.id.low}"> ${opt.caption} </option>
									</c:forEach>
								</select>
								</div>
							</c:when>
							
							<c:otherwise>
								<c:forEach var="opt" items="${mag.materialAttributeOptions}" varStatus="optInd">
									<label for="mag${magInd.index}" class="col-md-4 col-sm-4 control-label">${mag.caption} (${opt.id.low}..${opt.high}) </label>
									<div class="col-md-8  col-sm-8">
									<input type="text" class="form-control" name="mag${magInd.index}" id="mag${magInd.index}" />
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<c:set var="maxMag" value="${magInd.index+1}"></c:set>
						<input type="hidden" name="magId${magInd.index}" value="${mag.id}">
					</c:forEach>
					<input type="hidden" name="maxMag" value="${maxMag}">
					</div>

				</div>
			</div>

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">${language.getText('ORDER')}</h3>
				</div>

				<div class="box-body">
					<div class="col-md-6">
						<div class="form-group">
							<label for="orderDeadLine" class="col-md-4 col-sm-4 control-label">${language.getText("ORDER_DEAD_LINE")} </label>
							<div class="col-md-8  col-sm-8">
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input type="text" class="form-control pull-right required" placeholder="DD/MM/YYYY" name="orderDeadLine" id="orderDeadLine" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="deliveryPromise" class="col-md-4 col-sm-4 control-label">${language.getText("DELIVERY_PROMISE")} </label>
							<div class="col-md-8  col-sm-8">
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input type="text" class="form-control pull-right required" placeholder="DD/MM/YYYY" name="deliveryPromise" id="deliveryPromise" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="minQuantity" class="col-md-4 col-sm-4 control-label">${language.getText("MIN_QUANTITY")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="minQuantity" id="minQuantity" class="form-control required" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="maxQuantity" class="col-md-4 col-sm-4 control-label">${language.getText("MAX_QUANTITY")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="maxQuantity" id="maxQuantity" class="form-control required" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="boxQuantity" class="col-md-4 col-sm-4 control-label">${language.getText("BOX_QUANTITY")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="boxQuantity" id="boxQuantity" class="form-control required" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="unit" class="col-md-4 col-sm-4 control-label">${language.getText("UNIT")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="unit" id="unit" value="EA" class="form-control required readonly" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="startPrice" class="col-md-4 col-sm-4 control-label">${language.getText("START_PRICE")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="startPrice" id="startPrice" class="form-control required currency" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="currency" class="col-md-4 col-sm-4 control-label">${language.getText("CURRENCY")} </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="currency" id="currency" value="TRY" class="form-control required" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="earlyBirdPct" class="col-md-4 col-sm-4 control-label">${language.getText("EARLY_BIRD_PCT")} % </label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="earlyBirdPct" id="earlyBirdPct" class="form-control required" value="0.0" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">${language.getText('DISCOUNT_STEPS')}</h3>
				</div>

				<div class="box-body">
					<div class="col-md-6">
						<div class="form-group">
							<label for="quantity1" class="col-md-4 col-sm-4 control-label">${language.getText("QUANTITY")} 1</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="quantity1" id="quantity1" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="price1" class="col-md-4 col-sm-4 control-label">${language.getText("PRICE")} 1</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="price1" id="price1" class="form-control currency" />
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label for="quantity2" class="col-md-4 col-sm-4 control-label">${language.getText("QUANTITY")} 2</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="quantity2" id="quantity2" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="price2" class="col-md-4 col-sm-4 control-label">${language.getText("PRICE")} 2</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="price2" id="price2" class="form-control currency" />
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label for="quantity3" class="col-md-4 col-sm-4 control-label">${language.getText("QUANTITY")} 3</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="quantity3" id="quantity3" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="price3" class="col-md-4 col-sm-4 control-label">${language.getText("PRICE")} 3</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="price3" id="price3" class="form-control currency" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="quantity4" class="col-md-4 col-sm-4 control-label">${language.getText("QUANTITY")} 4</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="quantity4" id="quantity4" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="price4" class="col-md-4 col-sm-4 control-label">${language.getText("PRICE")} 4</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="price4" id="price4" class="form-control currency" />
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label for="quantity5" class="col-md-4 col-sm-4 control-label">${language.getText("QUANTITY")} 5</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="quantity5" id="quantity5" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="price5" class="col-md-4 col-sm-4 control-label">${language.getText("PRICE")} 5</label>
							<div class="col-md-8  col-sm-8">
								<input type="text" name="price5" id="price5" class="form-control currency" />
							</div>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>


	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a> <a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

</div>

<script type="text/javascript" src="js/dataTables.style.js">
</script>
<script>
function updateHtml(img,tmb) {
//	var btnTrash = $('a.btn-danger').get( -1 );
	var btnTrash =$('<a href="#" onclick="" class="btn btn-danger btn-md pull-left"><i class="fa fa-trash"> Sil </i></a>');
	var newDiv = $('<tr role="row" class="odd"> '+
			'<td><a href="#" onclick="" class="btn btn-danger btn-sm pull-left"><i class="fa fa-trash"> Sil </i></a></td> '+
			'<td>'+ $('#caption').val()+'</td> '+
			'<td><a target="_blank" class="pull-left" href="'+ img +'"><img src="' + tmb + '" alt="'+ $('#caption').val()+'"> </a></td></tr>');
			
	debugger;

	$('tbody').prepend(newDiv)	
}

function deleteImg(objType, id, contId){

	var row  = $('tr[data-id="' + contId + '"]');
	var response = 	doAjaxDelete('contentRelation/delWithObj/' + objType + '/'+ id + '/' + contId, row  ) ;

};

$(document).ready(function() {
	$('.required').attr({ 'placeholder' : '${REQUIRED}' });
	$(".select2").select2();
	$('.date').datepicker({
		autoclose : true,
		format : 'dd-mm-yyyy'
	});
});
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
