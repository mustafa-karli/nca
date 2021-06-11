<%@ attribute name="records" type="java.util.List" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${!empty records}">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText("MY_ORDERS")}</h3>
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
						<th>${language.getText("INITIAL_SEQUENCE")}</th>
						<th>${language.getText("SALES_QUANTITY")}</th>
						<th>${language.getText("UNIT_PRICE")}</th>
						<th>${language.getText("INITIAL_PRICE")}</th>
						<th>${language.getText("SALES_STATUS")}</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="sale" items="${records}" varStatus="status">
						<tr>
							<td>
								<div class="btn-group pull-right">
									<a class="btn btn-primary btn-sm" href="#" onClick="doAjaxGet('salesOrder/show?id=${sale.salesOrderId}');"> <i class="fa fa-edit"> </i>
									</a>
								</div>
							</td>
							<td>${sale.vendorCaption}</td>
							<td>${sale.caption}</td>
							<td>${sale.partNumber}</td>
							<td><fmt:formatDate value="${sale.orderDeadLine}" pattern="dd MMMM yyyy" /></td>
							<td><fmt:formatDate value="${sale.deliveryPromise}" pattern="dd MMMM yyyy" /></td>
							<td>${sale.minQuantity}</td>
							<td>${sale.maxQuantity}</td>
							<td>${sale.currentOrder}</td>
							<td>${sale.startPrice}</td>
							<td>${sale.currentPrice}</td>
							<td>${sale.currency}</td>
							<td><fmt:formatNumber value="${sale.discountPercent}" maxFractionDigits="2" /></td>
							<td>${sale.initialSequence}</td>
							<td>${sale.salesQuantity}</td>
							<td>${sale.unitPrice}</td>
							<td>${sale.initialPrice}</td>
							<td>${sale.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:if>
