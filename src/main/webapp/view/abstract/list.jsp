<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language.localeStr}" scope="session" />
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE} ${language.getText('LIST')}</h3>
		<c:if test="${!empty INSERT_ALLOWED}">
			<a class="btn btn-primary pull-right" href="#" onClick="doAjaxGet('${controller.rootMapping}/new');"> ${language.getIconText("NEW")} </a>
		</c:if>
		<c:forEach var="action" items="${controller.actions}" varStatus="status">
			<c:if test="${action.recordSpecific == 'N' && ACTIONS_ALLOWED[status.index] == 1}">
				<a class="btn btn-primary pull-right" href="#" onClick="doAjaxGet('${controller.rootMapping}/${action.method}');"> ${language.getIconText(action.caption)} </a>
			</c:if>
		</c:forEach>
	</div>

	<div class="box-body">

		<table id="dataTable5" class="display nowrap list" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>&nbsp;</th>
					<c:forEach var="field" items="${controller.fields}" varStatus="status">
						<c:if test="${controller.fields[status.index].editStyle != 'H'}">
							<th>${language.getText(field.fieldName)}</th>
						</c:if>
					</c:forEach>
					<th data-priority="1">&nbsp;</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="record" items="${records}" varStatus="status">
					<c:set var="linked" value="N"></c:set>
					<tr>
						<td>&nbsp;</td>
						<c:forEach items="${controller.fields}" var="field" varStatus="iCount">
							<c:set var="attr" value="${field.viewJstlPath}"></c:set>
							<c:set var="etype" value="${field.editStyle}"></c:set>
							<c:choose>
								<c:when test="${etype == 'G'}">
									<c:set var="atVal" value="<img src='contentRelation/firstThumb/${attr}/${record.id.toString()}'>"></c:set>
								</c:when>
								<c:when test="${etype == 'M'}">
									<c:set var="atVal" value="<div class=map> <div id='google-map' data-latitude='${record.latitude}' data-longitude='${record.longitude}'></div> </div>"></c:set>
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
								<c:choose>
									<c:when test="${linked == 'N'}">
										<c:set var="linked" value="Y"></c:set>
										<td><a href="#" onClick="doAjaxGet('${controller.rootMapping}/show?id=${record.id.toString()}');"> ${atVal} </a></td>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${field.editStyle == 'D'}">
												<td><fmt:formatDate value="${atVal}" pattern="dd MMMM yyyy" /></td>
											</c:when>
											<c:otherwise>
												<td>${atVal}</td>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
						<td>
							<div class="btn-group pull-right">
								<c:if test="${!empty UPDATE_ALLOWED}">
									<button onClick="doAjaxGet('${controller.rootMapping}/edit?id=${record.id.toString()}');" id="edit_rc_${status.index}" class="btn btn-primary btn-sm">${language.getIconText("EDIT")}</button>
								</c:if>
								<c:if test="${!empty DELETE_ALLOWED}">
									<button class="btn btn-danger btn-sm" id="delete_rc_${status.index}" onclick="confirmDelete($(this));">${language.getIconText("DELETE")}</button>
									<button class="btn btn-danger btn-sm" id="yesDel_rc_${status.index}" style="display: none;" onClick="doAjaxGet('${controller.rootMapping}/delete?id=${record.id.toString()}');">${language.getIconText("CONFIRM_DELETE")}</button>
									<button class="btn btn-warning btn-sm" id="notDel_rc_${status.index}" style="display: none;" onclick="confirmDelete($(this));">${language.getIconText("CANCEL")}</button>
								</c:if>
								<c:forEach var="action" items="${controller.actions}" varStatus="status">
									<c:if test="${action.recordSpecific == 'Y' && ACTIONS_ALLOWED[status.index] == 1}">
										<a class="btn btn-primary btn-sm" href="#" onClick="doAjaxGet('${controller.rootMapping}/${action.method}?id=${record.id.toString()}');"> ${language.getIconText(action.caption)} </a>
									</c:if>
								</c:forEach>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript" src="js/dataTables.style.js"></script>

<script>
	function confirmDelete(mi) {
		debugger;
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

	$("td.control").on("click", function() {
		debugger;

		$("#delete_rc_0").click(function() {
			alert("yup");
		})

	});
</script>

<!-- <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDGcYtseFAb7f3Qd2hBPjTbgZuZZbWB0Yk&callback=initMap" type="text/javascript"></script>  -->
