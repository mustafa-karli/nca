<%@ attribute name="topOrganization" type="com.nauticana.personnel.model.ViewOrganizationContainer" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${!empty topOrganization}">
	<c:if test="${!empty topOrganization.items}">
		<div class="box box-primary">
			<div class="box-header">
				<h2>${topOrganization.caption}</h2>
			</div>

			<div class="box-body">
				<table class="display nowrap report-group" data-caption="${topOrganization.caption}" cellspacing="0" width="100%">	
					<thead>
						<tr>
							<th align="center">${language.getText("ORDER_ID")}</th>
							<th align="center">${language.getText("ORDER_DATE")}</th>
							<th align="center">${language.getText("DUE_DATE")}</th>
							<th align="center">${language.getText("LINE")}</th>
							<th align="center">${language.getText("MATERIAL")}</th>
							<th align="center">${language.getText("QUANTITY")}</th>
							<th align="center">${language.getText("UNIT")}</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="record" items="${topOrganization.items}" varStatus="rIndex">
<!-- 							<tr> -->
<%-- 								<td>${record.id}</td> --%>
<%-- 								<td colspan=2>${language.getText("ORDER_DATE")} ${fn:substring(record.requestDate, 0, 10)}</td> --%>
<%-- 								<td colspan=2>${language.getText("DUE_DATE")} ${fn:substring(record.dueDate, 0, 10)}</td> --%>
<!-- 							</tr> -->
							<c:forEach var="line" items="${record.materialRequestItems}" varStatus="iIndex">
								<tr>
									<td>${record.id}</td>
									<td >${fn:substring(record.requestDate, 0, 10)}</td>
									<td >${fn:substring(record.dueDate, 0, 10)}</td>
									<td>${line.id.line}</td>
									<td>${line.material.caption}
										<c:forEach var="attr" items="${line.materialRequestItemAttrs}" varStatus="aIndex">
											<br> &nbsp; ${attr.materialAttributeGroup.caption} ${attr.value} 
										</c:forEach>
									</td>
									<td>${line.quantity}</td>
									<td>${line.unit}</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>

	<c:if test="${!empty topOrganization.children}">
		<c:forEach var="child" items="${topOrganization.children}" varStatus="cCount">
			<ncaTags:organizationRequest topOrganization="${child}" language="${language}" />
		</c:forEach>
	</c:if>
</c:if>
