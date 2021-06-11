<%@ attribute name="tree" type="java.util.List" required="true"%>
<%@ attribute name="elemId" type="String" required="true"%>
<%@ attribute name="elemTx" type="String" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty tree}">
	<c:forEach var="item" items="${tree}">
		<c:choose>
			<c:when test="${empty item.children}">
				<li><a href="#" onClick="${elemId}.value='${item.id}';$('#${elemTx}').html('${item.caption}');${elemId}.onchange();">${item.caption}</a></li>
			</c:when>

			<c:otherwise>
				<li class="dropdown-submenu">
					<a href="#" onClick="${elemId}.value='${item.id}';$('#${elemTx}').html('${item.caption}');${elemId}.onchange();">${item.caption}</a>
					<ul class="dropdown-menu">
						<ncaTags:selectTree tree="${item.children}" elemId="${elemId}" elemTx="${elemTx}" language="${language}" />
					</ul>
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>