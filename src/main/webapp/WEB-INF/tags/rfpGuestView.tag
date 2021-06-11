<%@ attribute name="records" type="java.util.List" required="true"%>
<%@ attribute name="vendors" type="java.util.List" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ attribute name="client" type="Integer" required="true"%>
<%@ attribute name="updateAllowed" type="String" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${!empty records}">
<form class="form-horizontal" method="post" name="f" id="f">

<c:forEach var="rfp" items="${records}" varStatus="status">

	<c:set var="partner" value="${partners[status.index]}"></c:set>
	<c:set var="binaryContent" value="${binaryContents[status.index]}"></c:set>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h2>${rfp.caption}</h2>
			<div class="col-md-6 col-sm-6">
				<table class="table">
					<tr> <th> ${language.getText("CUSTOMER")} </th> <td> <a href="http://${partner.webAddress}" target="_blank"> ${partner.caption} ${partner.hqCountry} </a> </td> </tr>
					<tr> <th> ${language.getText("TAX_CENTER")} </th> <td> ${partner.taxCenter} / ${partner.taxNumber} </td> </tr>
					<tr> <th> ${language.getText("REQUEST_DATE")} </th> <td> <fmt:formatDate value="${rfp.requestDate}" pattern="dd MMMM yyyy" /> </td> </tr>
					<tr> <th> ${language.getText("END_OF_PROPOSAL")} </th> <td> <fmt:formatDate value="${rfp.endOfProposal}" pattern="dd MMMM yyyy" /></td> </tr>
					<tr> <th> ${language.getText("DELIVERY_DATE")} </th> <td> <fmt:formatDate value="${rfp.deliveryDate}" pattern="dd MMMM yyyy" /></td> </tr>
					<tr> <th> ${language.getText("DELIVERY_ADDRESS")} </th> <td> ${rfp.deliveryAddress.street} ${rfp.deliveryAddress.city} ${rfp.deliveryAddress.state} ${rfp.deliveryAddress.country} ${rfp.deliveryAddress.phone} </td> </tr>
					<c:if test="${rfp.consortiumAllowed == 'Y'}"> <tr> <th> </th> <td> ${language.getText("CONSORTIUM_ALLOWED")} </td> </tr> </c:if>
					<c:if test="${rfp.partialAllowed == 'Y'}"> <tr> <th> </th> <td> ${language.getText("PARTIAL_ALLOWED")} </td> </tr> </c:if>
				</table>
				<c:if test="${!empty binaryContent}">
					<c:forEach items="${binaryContent}" var="cfile" varStatus="fCount">
						<a target="_blank" href="contentRelation/read/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}"> <img src="contentRelation/thumb/${cfile.id.objectType}/${cfile.id.objectId}/${cfile.id.contentId}" alt="${cfile.caption}"> </a> <br> <br>
					</c:forEach>
				</c:if>
			</div>

			<div class="col-md-6 col-sm-6">
				<a href="requestForProposal/print?id=${rfp.id}" class="btn btn-primary" target="_blank"> ${language.getText("PRINT_PREVIEW")} </a> &nbsp;
				<c:if test="${rfp.status != 'C' && client == rfp.deliveryAddress.id.businessPartnerId && updateAllowed == 'X'}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('requestForProposal/edit?id=${rfp.id}');"> <i class="fa fa-edit"> ${language.getText("EDIT")} </i> </a> <br> <br>
				</c:if>
				<c:choose>
					<c:when test="${rfp.status == 'I' && client == rfp.deliveryAddress.id.businessPartnerId && updateAllowed == 'X'}">
						<a class="btn btn-primary" href="#" onClick="doAjaxPost('requestForProposal/publish?id=${rfp.id}');"> ${language.getText("PUBLISH")} </a> &nbsp;
						<label> <input type="checkbox" name="vendor${rfp.id}" id="r${rfp.id}v0" value="0"> Global </label> <br>
						<c:forEach var="vendor" items="${vendors}" varStatus="vind">
							<c:forEach var="vb" items="${vendor.vendorBusinesses}" varStatus="bind">
								<c:if test="${vb.id.nodeId == rfp.purchaseArea}">
									<label class="col-md-6  col-sm-6"> <input type="checkbox" name="vendor${rfp.id}" id="r${rfp.id}v${vendor.id}" value="${vendor.id}"> ${vendor.businessPartner.caption} </label>
								</c:if>
							</c:forEach>
						</c:forEach>
					</c:when>
					<c:when test="${rfp.status == 'P' || rfp.status == 'G' || rfp.status == 'C'}">
						<c:choose>
							<c:when test="${client == rfp.deliveryAddress.id.businessPartnerId}">
								<a class="btn btn-primary" href="#" onClick="doAjaxGet('requestForProposal/proposals?id=${rfp.id}');"> ${language.getText("PROPOSALS")} </a>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${empty proposals[status.index]}">
										<a class="btn btn-primary" href="#" onClick="doAjaxGet('requestForProposal/propose?id=${rfp.id}');"> ${language.getText("PROPOSE")} </a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-primary" href="#" onClick="doAjaxGet('proposalToRfp/edit?id=${proposals[status.index].id}');"> ${language.getText("MY_PROPOSAL")} </a> &nbsp; <b> ${language.getText("MESSAGES")} : </b> <br> <br>						
										<c:forEach var="dialog" items="${proposals[status.index].proposalToRfpDialogs}" varStatus="ls">
											<fmt:formatDate value="${dialog.id.dtime}" pattern="dd MMMM yyyy hh:mm:ss" />${dialog.username}<br>${dialog.dtext} <hr>
										</c:forEach>
										<input type="TEXT" class="form-control" name="dtext${proposals[status.index].id}" id="dtext${proposals[status.index].id}" /> <br>
										<a href="#" onclick="doAjaxPost('proposalToRfpDialog/addMessage?id=${proposals[status.index].id}&nextpage=?home=conspro');" class="btn btn-primary">${language.getIconText("SEND")}</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>

			</div>
		</div>

		<div class="box-body table-responsive no-padding">

			<table class="table table-condensed">
				<thead>
					<tr>
						<th> &nbsp; </th>
						<th>${language.getText("LINE")}</th>
						<th>${language.getText("MATERIAL_TYPE")}</th>
						<th>${language.getText("SPECIFICATION")}</th>
						<th>${language.getText("BRAND")}</th>
						<th>${language.getText("QUANTITY")}</th>
						<th>${language.getText("UNIT")}</th>
						<th>${language.getText("PURCHASE_ORDER")}</th>
					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty purchaseReasons}">
						<c:set var="reason" value="${purchaseReasons[status.index]}"></c:set>
					</c:if>
					<c:forEach var="item" items="${rfp.requestForProposalItems}" varStatus="lineStat">
						<tr>
							<td>
								<c:if test="${rfp.status == 'P' && rfp.partialAllowed=='Y' }">
									<a class="btn btn-primary btn-sm" href="#" onClick="doAjaxGet('requestForProposalItem/propose?id=${item.id.toString()}');"> <i class="fa fa-circle"> Propose </i> </a>
								</c:if>
							</td>
							<td>${item.id.line}</td>
							<td>${item.materialType.caption}</td>
							<td>${item.specification} ${item.material.partNumber}</td>
							<td>${item.manufacturer.caption}</td>
							<td>${item.quantity}</td>
							<td>${item.unit}</td>
							<td><c:if test="${!empty reason}">
									<c:forEach var="prop" items="${item.proposalToRfpItems}" varStatus="propStat">
										&nbsp; <a href="#" onClick="doAjaxGet('proposalToRfp/order?id=${prop.id.proposalId}&rfpId=${rfp.id}&orderId=${reason.get(lineStat.index)[0]}');">${reason.get(lineStat.index)[0]}</a>
									</c:forEach>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:forEach>
</form>
</c:if>
