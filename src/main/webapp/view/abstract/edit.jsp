<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<link href="css/nauticana.table.css" rel="stylesheet" type="text/css" />
<!-- 
H  Hidden
R  Readonly
In Integer
S  Sequence (like hidden)
T  Text
P  Password
D  Date
F  Float-Decimal
C  Currency
M  Map from google
G  Graphic  contentRelation/firstThumb/otype/objid
		 or contentRelation/firstData/otype/objid
		 or contentRelation/thumb/otype/objid/contentid
		 or contentRelation/read/otype/objid/contentid
 -->

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE} ${language.getText('EDIT')}</h3>
	</div>

	<div class="box-body">

		<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
			<input type="HIDDEN" name="nextpage" value="${prevpage}">

			<c:forEach items="${controller.fields}" var="field" varStatus="iCount">
				<c:choose>
					<c:when test="${field.editStyle == 'H'}">
						<form:input type="hidden" path="${field.editJstlPath}" />
					</c:when>
					<c:otherwise>
						<div class="col-md-6">
							<div class="form-group">
								<c:set var="nullPtr" value="N"></c:set>
								<c:set var="attrs" value="${fn:split(field.editJstlPath, '.')}"></c:set>

								<c:choose>
									<c:when test="${fn:length(attrs) == 1}">
										<c:set var="atVal" value="${record[field.editJstlPath]}"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="atVal" value="${record[attrs[0]][attrs[1]]}"></c:set>
										<c:if test="${empty atVal}">
											<c:set var="nullPtr" value="Y"></c:set>
										</c:if>
									</c:otherwise>
								</c:choose>

								<label for="${field.editJstlPath}" class="col-md-4 col-sm-4 control-label">${language.getText(field.fieldName)} </label>
								<div class="col-md-8  col-sm-8">

									<c:choose>
										<c:when test="${nullPtr == 'N'}">
											<c:choose>
												<c:when test="${field.editStyle == 'R'}">
													<form:input type="hidden" path="${field.editJstlPath}" /> ${atVal}
												</c:when>
												<c:when test="${field.editStyle == 'S'}">
													<form:input type="hidden" path="${field.editJstlPath}" /> ${atVal}
												</c:when>
												<c:when test="${field.editStyle == 'SP'}">
													<form:input type="hidden" path="${field.editJstlPath}" /> ${atVal}
												</c:when>
												<c:when test="${field.editStyle == 'T'}">
													<form:input class="form-control ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'I1'}">
													<form:input class="form-control ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'I2'}">
													<form:input class="form-control ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'I4'}">
													<form:input class="form-control ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'I8'}">
													<form:input class="form-control ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'P'}">
													<form:password class="form-control" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'D'}">
													<div class="input-group date">
														<div class="input-group-addon">
															<i class="fa fa-calendar"></i>
														</div>
														<form:input type="text" class="form-control pull-right" placeholder="DD/MM/YYYY" path="${field.editJstlPath}" />
													</div>
												</c:when>
												<c:when test="${field.editStyle == 'DT'}">
													<form:input class="form-control ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'F'}">
													<form:input class="form-control decimal ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'C'}">
													<form:input class="form-control currency ${field.required}" path="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'M'}">
													<form:input type="hidden" path="${field.editJstlPath}" />
													<div class=map>
														<div id='google-map' data-latitude='${record.latitude}' data-longitude='${record.longitude}'></div>
													</div>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${field.lookupStyle == 'C'}">
															<form:select path="${field.editJstlPath}" items="${selectOptions[iCount.index]}" class="form-control select2" style="width: 100%;" />
														</c:when>
														<c:when test="${field.lookupStyle == 'R'}">
															<ncaTags:radio tagName="${field.editJstlPath}" tagOptions="${selectOptions[iCount.index]}" defvalue="${atVal}" />
														</c:when>
														<c:when test="${field.lookupStyle == 'T'}">
															<div class="dropdown col-md-3  col-sm-3">
																<form:input class="form-control ${field.required}" path="${field.editJstlPath}" readonly="true" />
															</div>
															<div class="dropdown col-md-5  col-sm-5">
																<a data-toggle="dropdown" href="#" id="${field.editJstlPath}Tx"> ${purchaseAreaTx} <span class=caret></span> </a>
																<ul class="dropdown-menu">
																	<ncaTags:selectTree tree="${field.editStyle}" elemId="document.f.${field.editJstlPath}" elemTx="document.f.${field.editJstlPath}Tx" language="${language}" />
																</ul>
															</div>
														</c:when>
														<c:otherwise>
															<form:input class="form-control ${field.required}" path="${field.editJstlPath}" />
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${field.editStyle == 'R'}">
													<input type="hidden" name="${field.editJstlPath}" id="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'S'}">
													<input type="hidden" name="${field.editJstlPath}" id="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'SP'}">
													<input type="hidden" name="${field.editJstlPath}" id="${field.editJstlPath}" />
												</c:when>
												<c:when test="${field.editStyle == 'T'}">
													<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'I1'}">
													<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'I2'}">
													<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'I4'}">
													<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'I8'}">
													<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'P'}">
													<input type="password" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'D'}">
													<div class="input-group date">
														<div class="input-group-addon">
															<i class="fa fa-calendar"></i>
														</div>
														<input type="text" class="form-control pull-right ${field.required}" placeholder="DD/MM/YYYY" name="${field.editJstlPath}" id="${field.editJstlPath}" />
													</div>
												</c:when>
												<c:when test="${field.editStyle == 'DT'}">
													<div class="input-group datetime">
														<div class="input-group-addon">
															<i class="fa fa-calendar"></i>
														</div>
														<input type="text" class="form-control pull-right ${field.required}" placeholder="DD/MM/YYYY hh:mm:ss" name="${field.editJstlPath}" id="${field.editJstlPath}" />
													</div>
												</c:when>
												<c:when test="${field.editStyle == 'F'}">
													<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control decimal ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'M'}">
													<input type="hidden" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
												</c:when>
												<c:when test="${field.editStyle == 'C'}">
													<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control currency ${field.required}" />
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${field.lookupStyle == 'C'}">
															<ncaTags:select tagName="${field.editJstlPath}" tagOptions="${selectOptions[iCount.index]}" defvalue="${atVal}" notnull="${field.required}" />
														</c:when>
														<c:when test="${field.lookupStyle == 'R'}">
															<ncaTags:radio tagName="${field.editJstlPath}" tagOptions="${selectOptions[iCount.index]}" defvalue="${atVal}" />
														</c:when>
														<c:otherwise>
															<input type="text" name="${field.editJstlPath}" id="${field.editJstlPath}" class="form-control ${field.required}" />
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</form:form>
		<div class="col-md-12">
			<div class="box box-info">
				<div class="box-header">
					<h3 class="box-title">${language.getText(CONTENT_RELATION)}</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">

					<!-- giriş alanı yukarı taşındı -->

					<!-- 						<span id="binContent"></span> -->

					<c:forEach items="${controller.contentTypes}" var="content" varStatus="iCount">
						<form:form class="form-horizontal" method="post" modelAttribute="record" id="binData${iCount.index}" enctype="multipart/form-data">

							<input type="HIDDEN" name="purpose" value="${content.objectType}">
							<div class="col-sm-12 col-md-12 form-group" style="margin-right: ''; margin-left: ''">

								<div class="col-md-5 form-group">
									<label for="binFile" class="col-md-2 col-sm-2 control-label">${language.getText(content.caption)} </label>
									<div class="col-sm-10 col-md-10">
										<input type="file" name="binFile" id="binFile" />
									</div>
								</div>
								<div class="col-md-5 form-group">
									<label for="caption" class="col-md-4 col-sm-4 control-label">${language.getText("CAPTION")} </label>
									<div class="col-sm-8 col-md-8">
										<input type="TEXT" name="caption" id="caption" class="form-control">
									</div>
								</div>
								<div class="col-sm-2 col-md-2">
									<a href="#" onclick="doAjaxBinPost('contentRelation/postBinaryData/${content.objectType}/${record.id.toString()}', '#binData${iCount.index}', 'POST');" class="btn btn-primary btn-sm pull-left">${language.getIconText("UPLOAD")}</a>
								</div>
							</div>
						</form:form>
					</c:forEach>

					<c:if test="${INPUTMODE == 'EDIT'}">
						<table class="simple-table" style="width: 100%">
							<thead>
								<tr>
									<th>${language.getText("ACTION")}</th>
									<th>${language.getText("DESCRIPTION")}</th>
									<th>${language.getText("IMAGE")}</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty binaryContent}">

									<c:forEach items="${binaryContent}" var="cfile" varStatus="fCount">
										<tr data-id="${cfile.id.contentId}">
											<td><a href="#" onclick="deleteImg('${cfile.id.objectType}','${cfile.id.objectId}','${cfile.id.contentId}');" class="btn btn-danger btn-sm pull-left">${language.getIconText("DELETE")}</a></td>
											<td>${cfile.caption}</td>
											<td><a target="_blank" class="pull-left" href="contentRelation/read/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}"><img src="contentRelation/thumb/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}" alt="${cfile.caption}"> </a></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>


	<div class="box-footer">
		<c:if test="${!empty errorText}">
			<p> <a href="#" class="btn btn-danger"> ERROR from server: ${errorText} </a> </p>
		</c:if>
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>

</div>

<script type="text/javascript" src="js/dataTables.style.js"></script>
<script>
function updateHtml(img,tmb) {
	var btnTrash =$('<a href="#" onclick="" class="btn btn-danger btn-md pull-left"><i class="fa fa-trash"> Sil </i></a>');
	var newDiv = $('<tr role="row" class="odd"> '+
			'<td><a href="#" onclick="" class="btn btn-danger btn-sm pull-left"><i class="fa fa-trash"> Sil </i></a></td> '+
			'<td>'+ $('#caption').val()+'</td> '+
			'<td><a target="_blank" class="pull-left" href="'+ img +'"><img src="' + tmb + '" alt="'+ $('#caption').val()+'"> </a></td></tr>');
	$('tbody').prepend(newDiv)	
}

function deleteImg(objType, id, contId){
	var row  = $('tr[data-id="' + contId + '"]');
	var response = 	doAjaxDelete('contentRelation/delWithObj/' + objType + '/'+ id + '/' + contId, row  ) ;
};

$(document).ready(function() {
		$('.required').attr({
			'placeholder' : '${REQUIRED}'
		});
		$(".select2").select2();
		$('.date').datepicker({
			autoclose : true,
			format : 'dd-mm-yyyy'
		});
		$('.datetime').datepicker({
			autoclose : true,
			format : 'dd-mm-yyyy hh:mm:ss'
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
