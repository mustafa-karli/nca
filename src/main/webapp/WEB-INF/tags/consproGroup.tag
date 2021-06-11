<%@ attribute name="purchaseGroups" type="java.util.TreeSet" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty purchseGroups}">
	<c:forEach var="purchseGroup" items="${purchseGroups}">
		<c:choose>
			<c:when test="${empty purchseGroup.groups}">
				<li><a href="#" onClick="doAjaxGet('requestForProposal/published?purchseGroup=${purchseGroup.id}');"> ${purchseGroup.caption} </a></li>
			</c:when>

			<c:otherwise>
				<li class="dropdown-submenu">
					<a href="#" onClick="doAjaxGet('requestForProposal/published?purchseGroup=${purchseGroup.id}');"> ${purchseGroup.caption} </a>
					<ul class="dropdown-menu">
						<ncaTags:consproGroup purchaseGroups="${purchseGroup.groups}" language="${language}" />
					</ul>
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>