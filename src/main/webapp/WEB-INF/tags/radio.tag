<%@ attribute name="tagName" type="String" required="true"%>
<%@ attribute name="defvalue" type="String" required="true"%>
<%@ attribute name="tagOptions" type="java.util.Map" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="opt" items="${tagOptions}">
<c:if test="${!empty opt.key}">
	<c:choose>
		<c:when test="${opt.key == defvalue}">
			<label> <input type="radio" name="${tagName}" id="${tagName}${opt.key}" value="${opt.key}" class="minimal" checked> ${language.getText(opt.value)} </label>
		</c:when>
		<c:otherwise>
			<label> <input type="radio" name="${tagName}" id="${tagName}${opt.key}" value="${opt.key}" class="minimal"> ${language.getText(opt.value)} </label>
		</c:otherwise>
	</c:choose>
</c:if>
</c:forEach>
