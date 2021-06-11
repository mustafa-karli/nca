<%@ attribute name="productGroups" type="java.util.TreeSet"	required="true"%>
<%@ attribute name="groupId" type="Integer" required="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:if test="${!empty productGroups}">
	<c:forEach var="productGroup" items="${productGroups}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<c:choose>
						<c:when test="${empty groupId}">
							<c:set var="parent" value="#accordion"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="parent" value="#grp_${groupId}"></c:set>
						</c:otherwise>
					</c:choose>


					<a class="clps" data-toggle="collapse" data-parent="${parent}"
						href="#col_${productGroup.id}">${productGroup.caption} </a>
				</div>
			</div>

			<c:set var="x" scope="session" value="0" />
			<div id="col_${productGroup.id}" class="panel-collapse collapse">

				<div class="panel-body">
					<c:forEach var="product" items="${productGroup.items}"
						varStatus="pCount">

						<div class="col-md-3 col-sm-4 col-xs-12 kutu ${product.favorite}"
							data-group="${productGroup.id}" data-product="${product.id}">
							<div class="sc-product-item thumbnail sc-add-to-cart urun"
								style="cursor: pointer">
								<img class="card-img-top" src="contentRelation/firstData/MT/${product.id}"
									alt="${product.caption}">
								<div class="caption">
									<div class="product_caption" data-name="product_name">${product.caption}</div>
									<p data-name="product_desc">${product.description}</p>

									<div>
										<c:forEach var="attr" items="${product.materialAttributes}"
											varStatus="aCount">
											<div class="form-group">
												<label> ${attr.materialAttributeGroup.caption} : </label>
												${attr.value}
											</div>
										</c:forEach>
										<%-- 									<strong class="price pull-left"> ${product.price} </strong>  --%>
										<input name="product_price" value="${product.price}"
											type="hidden" /> <input name="product_id" id="product_id"
											value="${product.id}" type="hidden" />


									</div>

									<div class="clearfix"></div>
								</div>
							</div>
							<button id="btnAddtoFav" type="button" class="btn btn-box-tool">
								<i class="fa fa-heart-o renk2"></i>
							</button>
						</div>
					</c:forEach>
					<div class="panel-group" id="grp_${productGroup.id}">
						<ncaTags:productGroup productGroups="${productGroup.groups}"
							groupId="${productGroup.id}" />
					</div>
				</div>
			</div>

		</div>
	</c:forEach>
</c:if>