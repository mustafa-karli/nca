<%@ attribute name="records" type="java.util.List" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${!empty records}">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText("PRODUCT_PRICE_COMMITMENT")}</h3>
		</div>

		<div class="box-body table-responsive no-padding">

			<table class="table table-condensed" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>${language.getText("VENDOR")}</th>
						<th>${language.getText("PRODUCT")}</th>
						<th>${language.getText("PART_NUMBER")}</th>
						<th>${language.getText("ORDER_DEAD_LINE")}</th>
						<th>${language.getText("DELIVERY_PROMISE")}</th>
						<th>${language.getText("MIN_QUANTITY")}</th>
						<th>${language.getText("MAX_QUANTITY")}</th>
						<th>${language.getText("CURRENT_ORDER")}</th>
						<th>${language.getText("START_PRICE")}</th>
						<th>${language.getText("CURRENT_PRICE")}</th>
						<th>${language.getText("CURRENCY")}</th>
						<th>${language.getText("DISCOUNT_RATE")}%</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="record" items="${records}" varStatus="status">
						<tr>
							<td>
								<div class="btn-group pull-right">
									<a class="btn btn-primary btn-sm" href="#" onClick="doAjaxGet('productPriceCommitment/order?id=${record.materialId},${record.businessPartnerId},<fmt:formatDate value="${record.orderDeadLine}" pattern="dd-MM-yyyy" />');"> <i class="fa fa-shopping-cart"> </i>
									</a>
								</div>
							</td>
							<td>${record.vendorCaption}</td>
							<td>${record.caption}</td>
							<td>${record.partNumber}</td>
							<td><fmt:formatDate value="${record.orderDeadLine}" pattern="dd MMMM yyyy" /></td>
							<td><fmt:formatDate value="${record.deliveryPromise}" pattern="dd MMMM yyyy" /></td>
							<td>${record.minQuantity}</td>
							<td>${record.maxQuantity}</td>
							<td>${record.currentOrder}</td>
							<td>${record.startPrice}</td>
							<td>${record.currentPrice}</td>
							<td>${record.currency}</td>
							<td><fmt:formatNumber value="${record.discountPercent}" maxFractionDigits="2" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:if>
