<%@ attribute name="productGroups" type="java.util.TreeSet" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty productGroups}">
	<c:forEach var="productGroup" items="${productGroups}">
		<c:choose>
			<c:when test="${empty productGroup.groups}">
				<li><a href="#" onClick="document.f.materialGroupId.value='${productGroup.id}';$('#mgLink').html('${productGroup.caption}');"> ${productGroup.caption} </a></li>
			</c:when>

			<c:otherwise>
				<li class="dropdown-submenu">
					<a href="#" onClick="document.f.materialGroupId.value='${productGroup.id}';"> ${productGroup.caption} </a>
					<ul class="dropdown-menu">
						<ncaTags:productGroupOptions productGroups="${productGroup.groups}" language="${language}" />
					</ul>
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>