<%@ attribute name="tagName" type="String" required="true"%>
<%@ attribute name="defvalue" type="String" required="true"%>
<%@ attribute name="tagOptions" type="java.util.Map" required="true"%>
<%@ attribute name="notnull" type="String" required="true"%>
<%@ attribute name="onChange" type="String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select name="${tagName}" id="${tagName}" class="form-control select2" style="width: 100%;" onChange="${onChange}">
	<c:if test="${!empty notnull}"><option value=""></option></c:if>
	<c:forEach var="opt" items="${tagOptions}">
		<c:choose>
			<c:when test="${opt.key == defvalue}">
				<option value="${opt.key}" selected>${opt.value}</option>
			</c:when>
			<c:otherwise>
				<option value="${opt.key}">${opt.value}</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
