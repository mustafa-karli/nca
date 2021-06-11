<%@ attribute name="productGroups" type="java.util.TreeSet" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty productGroups}">
	<c:forEach var="productGroup" items="${productGroups}">
		<c:choose>
			<c:when test="${empty productGroup.groups}">
				<li><a href="#"> ${productGroup.caption} </a></li>
			</c:when>

			<c:otherwise>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordian" href='#"${productGroup.id}"'> <span class="badge pull-right"><i class="fa fa-plus"></i></span> ${language.getText(productGroup.caption)} </a>
						</h4>
					</div>
					<div id="${productGroup.id}" class="panel-collapse collapse">
						<div class="panel-body">
							<ul>
								<ncaTags:motifProductGroup productGroups="${productGroup.groups}" language="${language}" />
							</ul>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>