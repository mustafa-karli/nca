<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"     uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ncaTags" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${language.localeStr}" scope="session" />
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>

	<div class="box-body">

		<table id="dataTable5" class="table" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th colspan=10>${language.getText("PERSON")}</th>
					<th colspan=4>${language.getText("EMPLOYEE")}</th>
					<th colspan=5>${language.getText("USER_ACCOUNT")}</th>
				</tr>
				<tr>
					<th>${language.getText("PERSON_ID")}</th>
					<th>${language.getText("FIRST_NAME")}</th>
					<th>${language.getText("MIDDLE_NAME")}</th>
					<th>${language.getText("LAST_NAME")}</th>
					<th>${language.getText("BIRTHDAY")}</th>
					<th>${language.getText("GENDER")}</th>
					<th>${language.getText("NATIONALITY")}</th>
					<th>${language.getText("GOVERNMENT_ID")}</th>
					<th>${language.getText("PERSONAL_MAIL")}</th>
					<th>${language.getText("CELL_PHONE")}</th>
					<th>${language.getText("EMPLOYMENT")}</th>
					<th>${language.getText("POSITION")}</th>
					<th>${language.getText("EMAIL_ADDRESS")}</th>
					<th>${language.getText("WORK_PHONE")}</th>
					<th>${language.getText("USERNAME")}</th>
					<th>${language.getText("USER_MAIL")}</th>
					<th>${language.getText("STATUS")}</th>
					<th>${language.getText("PASSDATE")}</th>
					<th>${language.getText("LASTLOGON")}</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="record" items="${records}" varStatus="status">
					<tr>
						<td>${record.personId}</td>
						<td>${record.firstName}</td>
						<td>${record.middleName}</td>
						<td>${record.lastName}</td>
						<td><fmt:formatDate value="${record.birthday}" pattern="dd MMMM yyyy" /></td>
						<td>${record.gender}</td>
						<td>${record.nationality}</td>
						<td>${record.governmentId}</td>
						<td>${record.personelMail}</td>
						<td>${record.cellPhone}</td>
						<td><fmt:formatDate value="${record.employment}" pattern="dd MMMM yyyy" /></td>
						<td>${record.position}</td>
						<td>${record.emailAddress}</td>
						<td>${record.workPhone}</td>
						<td>${record.username}</td>
						<td>${record.userMail}</td>
						<td>${record.status}</td>
						<td><fmt:formatDate value="${record.passdate}" pattern="dd MMMM yyyy" /></td>
						<td><fmt:formatDate value="${record.lastlogon}" pattern="dd MMMM yyyy" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>

<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
<input type="HIDDEN" name="nextpage" value="${prevpage}">
<input type="HIDDEN" name="partnerId" value="${partnerId}">

<div class="box box-primary">
	<ncaTags:personElements title="${language.getText('NEW')} ${language.getText('RECORD')}" veu="${veu}" language="${language}" />

	<div class="box-footer">
		<c:if test="${!empty errorText}">
			<p> <a href="#" class="btn btn-danger"> ERROR from server: ${errorText} </a> </p>
		</c:if>
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${language.getIconText("SAVE")}</a> <a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${language.getIconText("CANCEL")}</a>
	</div>
</div>
</form:form>

<script type="text/javascript" src="js/dataTables.style.js"></script>
