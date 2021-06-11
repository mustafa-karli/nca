<%@ attribute name="tree" type="java.util.List" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="bs" value="["></c:set>
<c:if test="${!empty tree}">
	<c:set var="es" value="]"></c:set>
	<c:forEach var="item" items="${tree}">
		${bs}<c:set var="bs" value=","></c:set>{id:${item.id},title:"${item.caption}"<c:if test="${!empty item.children}">,subs:<ncaTags:comboTree tree="${item.children}" /></c:if>}
	</c:forEach>
	${es}
</c:if>