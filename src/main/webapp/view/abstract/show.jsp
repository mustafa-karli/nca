<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setLocale value="${language.localeStr}" scope="session" />

<div class="box">

	<div class="box-header with-border">
		<h3 class="box-title">
			<button type="button" class="btn btn-box-tool" data-widget="collapse">
				<i class="fa fa-minus"> ${PAGETITLE} ${language.getText('DETAIL')}</i>
			</button>
		</h3>
		<div class="box-tools pull-right">
			<c:forEach items="${controller.actions}" var="action" varStatus="aCount">
				<c:if test="${ACTIONS_ALLOWED[aCount.index] == 1}">
					<button class="btn btn-primary" onClick="doAjaxGet('${controller.rootMapping}/${action.method}?id=${record.id.toString()}');">${language.getIconText(action.caption)}</button>
				</c:if>
			</c:forEach>
			<c:if test="${siudAllowed[1]}">
				<button class="btn btn-info" onClick="doAjaxGet('${controller.rootMapping}/new');">${language.getIconText("NEW")}</button>
			</c:if>
			<c:if test="${siudAllowed[2]}">
				<button class="btn btn-primary" onClick="doAjaxGet('${controller.rootMapping}/edit?id=${record.id.toString()}');">${language.getIconText("EDIT")}</button>
			</c:if>
			<c:if test="${siudAllowed[3]}">
				<button class="btn btn-danger" id="delete_rc_0" onclick="confirmDelete($(this));">${language.getIconText("DELETE")}</button>
				<button class="btn btn-danger" id="yesDel_rc_0" style="display: none;" onClick="doAjaxGet('${controller.rootMapping}/delete?id=${record.id.toString()}');">${language.getIconText("CONFIRM_DELETE")}</button>
				<button class="btn btn-warning" id="notDel_rc_0" style="display: none;" onclick="confirmDelete($(this));">${language.getIconText("CANCEL_DELETE")}</button>
			</c:if>
			<button class="btn btn-warning" onclick="doAjaxGet('${prevpage}');">${language.getIconText("EXIT")}</button>
		</div>
	</div>

	<div class="box-body">

		<div class="col-md-8" class="form-group">
			<c:forEach items="${controller.fields}" var="field" varStatus="iCount">
				<c:set var="attr" value="${field.viewJstlPath}"></c:set>
				<c:set var="etype" value="${field.editStyle}"></c:set>
				<c:choose>
					<c:when test="${etype == 'G'}">
						<c:set var="atVal" value="<img src='contentRelation/firstThumb/${attr}/${record.id.toString()}'>"></c:set>
					</c:when>
					<c:when test="${etype == 'M'}">
						<%-- 						<c:set var="atVal" value="<div class=map> <div id='google-map' data-latitude='${record.latitude}' data-longitude='${record.longitude}'></div> </div>"></c:set> --%>
					</c:when>
					<c:when test="${etype == 'H'}">
					</c:when>
					<c:otherwise>
						<c:set var="attrs" value="${fn:split(attr, '.')}"></c:set>
						<c:choose>
							<c:when test="${fn:length(attrs) == 1}">
								<c:set var="atVal" value="${record[attr]}"></c:set>
							</c:when>
							<c:when test="${fn:length(attrs) == 2}">
								<c:set var="atVal" value="${record[attrs[0]][attrs[1]]}"></c:set>
							</c:when>
							<c:when test="${fn:length(attrs) == 3}">
								<c:set var="atVal" value="${record[attrs[0]][attrs[1]][attrs[2]]}"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="atVal" value="${record[attrs[0]][attrs[1]][attrs[2]][attrs[3]]}"></c:set>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<c:if test="${etype != 'H'}">
					<c:if test="${!empty DOMAIN_OPTIONS[iCount.index]}">
						<c:set var="aa" value="${DOMAIN_OPTIONS[iCount.index].get(atVal)}"></c:set>
						<c:if test="${!empty aa}">
							<c:set var="atVal" value="${aa}"></c:set>
						</c:if>
					</c:if>
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-4">
								<label for="${field.fieldName}" class="col-form-label">${language.getText(field.fieldName)}:</label> <span class="pull-right"></span>
							</div>
							<div class="col-md-8">
								<p class="form-control-static" id="${field.fieldName}">
									<c:choose>
										<c:when test="${field.editStyle == 'D'}">
											<fmt:formatDate value="${atVal}" pattern="dd MMMM yyyy" />
										</c:when>
										<c:otherwise>
											${atVal}
										</c:otherwise>
									</c:choose>
								</p>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="col-md-4">
			<c:if test="${!empty binaryContent}">
				<c:forEach items="${binaryContent}" var="cfile" varStatus="fCount">
					<%-- 					<img src="contentRelation/read/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}" alt="${cfile.caption}"> <br><br> --%>
					<a target="_blank" href="contentRelation/read/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}"> <img src="contentRelation/thumb/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}" alt="${cfile.caption}">
					</a>
					<br>
					<br>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>

<c:if test="${!empty controller.details}">
	<div class="nav-tabs-custom">
		<c:set var="ACT_TAB" value=" class=active"></c:set>
		<ul class="nav nav-tabs">
			<c:forEach items="${controller.details}" var="detail" varStatus="cCount">
				<c:if test="${!empty selectAllowed[cCount.index] && detail.enable == 'Y'}">
					<li ${ACT_TAB}><a href="#tab${cCount.index}" data-toggle="tab">${language.getIconText(detail.detailTable)}</a></li>
					<c:set var="ACT_TAB" value=""></c:set>
				</c:if>
			</c:forEach>
		</ul>

		<c:set var="ACT_TAB" value=" active"></c:set>

		<div class="tab-content">

			<c:forEach items="${controller.details}" var="detail" varStatus="cCount">
				<c:if test="${!empty DETAIL_OPTIONS[cCount.index]}">
					<c:set var="DETAIL_OPTION" value="${DETAIL_OPTIONS[cCount.index]}"></c:set>
				</c:if>
				<c:if test="${!empty selectAllowed[cCount.index] && detail.enable == 'Y'}">
					<div class="tab-pane${ACT_TAB}" id="tab${cCount.index}">
						<c:set var="ACT_TAB" value=""></c:set>
						<div class="box box-info">
							<div class="box-header">
								<div class="btn-group pull-right">
									<c:if test="${!empty insertAllowed[cCount.index]}">
										<button onClick="doAjaxGet(document.getElementById('NEWLINK${cCount.index}').value);" class="btn btn-primary">${language.getIconText("NEW")}</button>
									</c:if>
								</div>
							</div>
							<div class="box-body">
								<table id="dataTable${cCount.index}" style="width: '100%', cellspacing: '0'" class="table table-condensed showlist">
									<thead>
										<tr>
											<c:forEach var="field" items="${detail.detailStatic.fields}" varStatus="status">
												<c:if test="${field.editStyle != 'H'}">
													<th>${language.getText(field.fieldName)}</th>
												</c:if>
											</c:forEach>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="rVal" value="0"></c:set>
										<c:forEach var="child" items="${record[detail.detailAttribute]}" varStatus="status">
											<tr>
												<c:set var="linked" value="N"></c:set>
												<c:forEach var="field" items="${detail.detailStatic.fields}" varStatus="iCount">
													<c:if test="${field.editStyle != 'H'}">
														<c:set var="attrs" value="${fn:split(field.viewJstlPath, '.')}"></c:set>
														<c:choose>
															<c:when test="${fn:length(attrs) == 1}">
																<c:set var="chVal" value="${child[field.viewJstlPath]}"></c:set>
															</c:when>
															<c:when test="${fn:length(attrs) == 2}">
																<c:set var="chVal" value="${child[attrs[0]][attrs[1]]}"></c:set>
															</c:when>
															<c:when test="${fn:length(attrs) == 3}">
																<c:set var="chVal" value="${child[attrs[0]][attrs[1]][attrs[2]]}"></c:set>
															</c:when>
															<c:otherwise>
																<c:set var="chVal" value="${child[attrs[0]][attrs[1]][attrs[2]][attrs[3]]}"></c:set>
															</c:otherwise>
														</c:choose>
														<c:if test="${!empty DETAIL_OPTION[iCount.index]}">
															<c:set var="aa" value="${DETAIL_OPTION[iCount.index].get(chVal)}"></c:set>
															<c:if test="${!empty aa}">
																<c:set var="chVal" value="${aa}"></c:set>
															</c:if>
														</c:if>
														<c:choose>
															<c:when test="${linked == 'N'}">
																<c:set var="linked" value="Y"></c:set>
																<td><a href="#" onClick="doAjaxGet('${detail.detailStatic.rootMapping}/show?id=${child.id.toString()}');"> ${chVal} </a></td>
															</c:when>
															<c:when test="${field.editStyle == 'D'}">
																<td><fmt:formatDate value="${chVal}" pattern="dd MMMM yyyy" /></td>
															</c:when>
															<c:otherwise>
																<td>${chVal}</td>
															</c:otherwise>
														</c:choose>
														<c:if test="${field.editStyle == 'SP'}">
															<c:choose>
																<c:when test="${rVal <= chVal}">
																	<c:set var="rVal" value="${chVal + 1}"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="rVal" value="1"></c:set>
																</c:otherwise>
															</c:choose>
														</c:if>
													</c:if>
												</c:forEach>
												<td>
													<div class="btn-group pull-right">
														<c:if test="${!empty updateAllowed[cCount.index]}">
															<button onClick="doAjaxGet('${detail.detailStatic.rootMapping}/edit?id=${child.id.toString()}');" id="edit_c${cCount.index}_${status.index}" class="btn btn-primary btn-sm">${language.getIconText("EDIT")}</button>
														</c:if>
														<c:if test="${!empty deleteAllowed[cCount.index]}">
															<button class="btn btn-danger btn-sm" id="delete_c${cCount.index}_${status.index}">${language.getIconText("DELETE")}</button>
															<button class="btn btn-danger btn-sm" id="yesDel_c${cCount.index}_${status.index}" style="display: none;" onClick="doAjaxGet('${detail.detailStatic.rootMapping}/delete?id=${child.id.toString()}');">${language.getIconText("CONFIRM_DELETE")}</button>
															<button class="btn btn-warning btn-sm" id="notDel_c${cCount.index}_${status.index}" style="display: none;">${language.getIconText("CANCEL")}</button>
														</c:if>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<c:choose>
									<c:when test="${rVal > 0}">
										<input type="hidden" name="NEWLINK${cCount.index}" id="NEWLINK${cCount.index}" value="${detail.detailStatic.rootMapping}/new?id=${record.id.toString()},${rVal}">
									</c:when>
									<c:otherwise>
										<input type="hidden" name="NEWLINK${cCount.index}" id="NEWLINK${cCount.index}" value="${detail.detailStatic.rootMapping}/new?parentKey=${record.id.toString()}">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</c:if>

<script type="text/javascript" src="js/dataTables.style.js"></script>
<script>
	$(document).ready(function() {
		$("button.btn-sm").click(function() {
			var data = $(this).attr('id');
			var buttonId = data.split('_');
			if (buttonId[0] == 'delete') {
				$("#yesDel_" + buttonId[1] + "_" + buttonId[2]).show();
				$("#notDel_" + buttonId[1] + "_" + buttonId[2]).show();
				$("#delete_" + buttonId[1] + "_" + buttonId[2]).hide();
				//			$("#edit_" + buttonId[1] + "_" + buttonId[2]).hide();
			} else if (buttonId[0] == 'notDel') {
				$("#yesDel_" + buttonId[1] + "_" + buttonId[2]).hide();
				$("#notDel_" + buttonId[1] + "_" + buttonId[2]).hide();
				$("#delete_" + buttonId[1] + "_" + buttonId[2]).show();
				//			$("#edit_" + buttonId[1] + "_" + buttonId[2]).show();
			}
		})
	});

	function confirmDelete(mi) {
		var data = mi.attr('id');
		var buttonId = data.split('_');

		if (buttonId[0] == 'delete') {
			$("#yesDel_" + buttonId[1] + "_" + buttonId[2]).show();
			$("#notDel_" + buttonId[1] + "_" + buttonId[2]).show();
			$("#delete_" + buttonId[1] + "_" + buttonId[2]).hide();
			//		$("#edit_" + buttonId[1] + "_" + buttonId[2]).hide();
		} else if (buttonId[0] == 'notDel') {
			$("#yesDel_" + buttonId[1] + "_" + buttonId[2]).hide();
			$("#notDel_" + buttonId[1] + "_" + buttonId[2]).hide();
			$("#delete_" + buttonId[1] + "_" + buttonId[2]).show();
			//		$("#edit_" + buttonId[1] + "_" + buttonId[2]).show();
		}
	};
</script>

