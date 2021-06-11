<%@ attribute name="topOrganization" type="com.nauticana.personnel.model.ViewOrganizationContainer" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${!empty topOrganization}">
	<c:if test="${!empty topOrganization.items}">
		<div class="box box-primary">
			<div class="box-body">
				<table class="display nowrap report" style="cellspacing:'0', width:'100%'">
					<thead>
						<tr>
							<th align="center">${language.getText("ORDER_ID")}</th>
							<th align="center">${language.getText("LINE")}</th>
							<th align="center">${language.getText("MATERIAL")}</th>
							<th align="center">${language.getText("QUANTITY")}</th>
							<th align="center">${language.getText("UNIT")}</th>
							<th align="center">&nbsp;</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="record" items="${topOrganization.items}" varStatus="rIndex">
							<tr>
								<td>${record.id}</td>
								<td colspan=4>${language.getText("ORDER_DATE")}: ${fn:substring(record.requestDate, 0, 10)} - ${language.getText("DUE_DATE")}: ${fn:substring(record.dueDate, 0, 10)}</td>
								<c:choose>
									<c:when test="${record.status == 'G'}">
										<td rowspan="${record.getMaterialRequestItems().size()+1}"><img src="contentRelation/firstData/MR/${record.id}"></td>
									</c:when>
									<c:otherwise>
										<td>&nbsp;</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<c:forEach var="line" items="${record.materialRequestItems}" varStatus="iIndex">
								<tr>
									<td>&nbsp;</td>
									<td>${line.id.line}</td>
									<td>${line.material.caption}
										<c:forEach var="attr" items="${line.materialRequestItemAttrs}" varStatus="aIndex">
											<br> &nbsp; ${attr.materialAttributeGroup.caption} ${attr.value} 
										</c:forEach>
									</td>
									<td>${line.quantity}</td>
									<td>${line.unit}</td>
									<c:if test="${record.status != 'G'}">
										<td>&nbsp;</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>

	<c:if test="${!empty topOrganization.children}">
		<div class="nav-tabs-custom">
			<c:set var="ACT_TAB" value=" class=active"></c:set>
			<ul class="nav nav-tabs">
				<c:forEach var="child" items="${topOrganization.children}" varStatus="cCount">
					<li ${ACT_TAB}><a href="#tab${cCount.index}" data-toggle="tab">${child.caption}</a></li>
					<c:set var="ACT_TAB" value=""></c:set>
				</c:forEach>
			</ul>

			<c:set var="ACT_TAB" value=" active"></c:set>
			<div class="tab-content">
				<c:forEach var="child" items="${topOrganization.children}" varStatus="cCount">
					<div class="tab-pane${ACT_TAB}" id="tab${cCount.index}">
						<c:set var="ACT_TAB" value=""></c:set>
						<ncaTags:organizationRequest topOrganization="${child}" language="${language}" />
					</div>
				</c:forEach>
			</div>
		</div>
	</c:if>
</c:if>
