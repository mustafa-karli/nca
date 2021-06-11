<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h2>${rfp.caption}</h2>
		<p>
			<b>${language.getText("REQUEST_DATE")}:</b> <fmt:formatDate value="${rfp.requestDate}" pattern="dd MMMM yyyy" /> &nbsp;
			<b>${language.getText("END_OF_PROPOSAL")}:</b> <fmt:formatDate value="${rfp.endOfProposal}" pattern="dd MMMM yyyy" /> &nbsp;
			<b>${language.getText("DELIVERY_DATE")}:</b> <fmt:formatDate value="${rfp.deliveryDate}" pattern="dd MMMM yyyy" /> &nbsp;
			<c:if test="${rfp.consortiumAllowed == 'Y'}"> &nbsp; ${language.getText("CONSORTIUM_ALLOWED")} &nbsp; </c:if>
			<c:if test="${rfp.partialAllowed == 'Y'}"> &nbsp; ${language.getText("PARTIAL_ALLOWED")} &nbsp; </c:if>
		</p>
	</div>

	<div class="box-body">
	
		<form class="form-horizontal" method="post" name="f" id="f">
		<input type="HIDDEN" name="nextpage" value="requestForProposal/proposals?id=${rfp.id}">
		<table class="table table-condensed rfp-list">
		<tr>
			<th colspan=5> ${language.getText("VENDOR")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<c:set var="partner" value="${partners[pind.index]}"></c:set>
				<td colspan=3><a href="http://${partner.webAddress}" target="_blank">${partner.caption}</a></td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th colspan=5> ${language.getText("TAX_NUMBER")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<c:set var="partner" value="${partners[pind.index]}"></c:set>
				<td colspan=3>${partner.taxCenter} ${partner.taxNumber}</td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th colspan=5> ${language.getText("PROPOSED_BY")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<c:set var="user" value="${users[pind.index]}"></c:set>
				<td colspan=3>${user.firstName} ${user.lastName} ${user.emailAddress}</td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th colspan=5> ${language.getText("DESCRIPTION")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<td colspan=3>${proposal.description}</td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th colspan=5> ${language.getText("VALID_UNTIL")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<td colspan=3><fmt:formatDate value="${proposal.validUntil}" pattern="dd MMMM yyyy" /></td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th colspan=5> ${language.getText("TOTAL_PRICE")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<td colspan=3>${proposal.totalPrice}</td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th colspan=5> ${language.getText("PAYMENT")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<td colspan=3>(${proposal.paymentType}) : ${proposal.paymentNote}</td>
				</c:if>
			</c:forEach>
		</tr>
		<c:if test="${rfp.status == 'G' || rfp.status == 'P'}">
		<tr>
			<th colspan=5> ${language.getText("ACTIONS")} </th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
				<c:if test="${!empty proposal}">
				<td colspan=3>
				<a href="proposalToRfp/print?id=${proposal.id}" class="btn btn-primary" target="_blank"> ${language.getText("PRINT_PREVIEW")} </a>
				<c:if test="${proposal.validUntil >= now}">
					<br> <a href="#" class="btn btn-primary" onClick="doAjaxGet('proposalToRfp/order?id=${proposal.id}&dueDate=<fmt:formatDate value="${rfp.deliveryDate}" pattern="dd-MM-yyyy" />&prevpage=requestForProposal/proposals?id=${rfp.id}&nextpage=/');"> ${language.getText("CREATE_PURCHASE_ORDER")} </a>
				</c:if>
				</td>
				</c:if>
			</c:forEach>
		</tr>
		</c:if>

		<c:forEach var="line" items="${lines}" varStatus="ln">
		<tr>
			<c:forEach var="str" items="${line}" varStatus="ls">
			<c:choose>
				<c:when test="${ln.index == 0}">
					<th>${language.getText(str)}</th>
				</c:when>
				<c:when test="${ls.index < 5}">
					<th>${str}</th>
				</c:when>
				<c:otherwise>
					<td>${str}</td>
				</c:otherwise>
			</c:choose>
			</c:forEach>
		</tr>
		</c:forEach>

		<c:if test="${rfp.status == 'G' || rfp.status == 'P'}">
		<tr>
			<th colspan=5>&nbsp;</th>
			<c:forEach var="proposal" items="${proposals}" varStatus="pind">
			<c:if test="${!empty proposal}">
			<td colspan=3>
				<c:forEach var="dialog" items="${proposal.proposalToRfpDialogs}" varStatus="ls">
				<fmt:formatDate value="${dialog.id.dtime}" pattern="dd MMMM yyyy hh:mm:ss" /> ${dialog.username} <br>
				${dialog.dtext} <hr>
				</c:forEach>
				<input type="TEXT" class="form-control" name="dtext${proposal.id}" id="dtext${proposal.id}" /> <br>
				<a href="#" onclick="doAjaxPost('proposalToRfpDialog/addMessage?id=${proposal.id}');" class="btn btn-primary">${language.getIconText("SEND")}</a>
			</td>
			</c:if>
			</c:forEach>
		</tr>
		</c:if>
		</table>
		</form>
	</div>
</div>
<script src="js/nauticana.style.js"></script>
