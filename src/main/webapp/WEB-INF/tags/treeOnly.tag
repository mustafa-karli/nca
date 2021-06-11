<%@ attribute name="tree" type="com.nauticana.basis.model.ViewTreeData" required="true"%>
<%@ attribute name="linkFunc" type="String" required="true"%>
<%@ attribute name="linkAddr" type="String" required="true"%>
<%@ attribute name="linkText" type="String" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty tree}">
	<c:forEach var="t" items="${tree}">
	<li>
		<c:if test="${!empty t.children}">
			<input type=checkbox name="Arrow" id="${t.id}_" checked="checked" />
		</c:if>
			<label> <a href="#" onClick="${linkFunc}('${linkAddr}${t.id}');"> ${t.caption} </a> </label>
			
		<c:if test="${!empty t.children}">
			<ul>
				<ncaTags:treeCheck tree="${t.children}"  linkText="${linkText}" linkAddr="${linkAddr}" linkFunc="${linkFunc}"/>
			</ul>
		</c:if>
	</li>
	</c:forEach>
</c:if>