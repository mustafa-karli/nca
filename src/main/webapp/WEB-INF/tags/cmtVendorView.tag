<%@ attribute name="records" type="java.util.List" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${!empty records}">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${language.getText("MY_COMMITMENTS")}</h3>
		</div>

		<div class="box-body table-responsive no-padding">

			<table class="table table-condensed wide-list" cellspacing="0" width="100%">
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
						<th>${language.getText("DISCOUNT_STEPS")} qty->prc</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="cmt" items="${records}" varStatus="status">
						<tr>
							<td>
								<div class="btn-group pull-right">
									<a class="btn btn-primary btn-sm" href="#" onClick="doAjaxGet('salesOrderCommitment/orders?materialId=${cmt.materialId}&orderDeadLine=<fmt:formatDate value="${cmt.orderDeadLine}" pattern="dd-MM-yyyy" />');"> <i class="fa fa-sitemap"> </i>
									</a>
								</div>
							</td>
							<td>${cmt.vendorCaption}</td>
							<td>${cmt.caption}</td>
							<td>${cmt.partNumber}</td>
							<td><fmt:formatDate value="${cmt.orderDeadLine}" pattern="dd MMMM yyyy" /></td>
							<td><fmt:formatDate value="${cmt.deliveryPromise}" pattern="dd MMMM yyyy" /></td>
							<td>${cmt.minQuantity}</td>
							<td>${cmt.maxQuantity}</td>
							<td>${cmt.currentOrder}</td>
							<td>${cmt.startPrice}</td>
							<td>${cmt.currentPrice}</td>
							<td>${cmt.currency}</td>
							<td><fmt:formatNumber value="${cmt.discountPercent}" maxFractionDigits="2" /></td>
							<td>${cmt.initialSequence}</td>
							<td>${cmt.salesQuantity}</td>
							<td>${cmt.unitPrice}</td>
							<td>${cmt.initialPrice}</td>
							<td>${cmt.status}</td>
							<td><c:forEach var="quantity" items="${cmt.quantities}" varStatus="qcount">
								${quantity} -> ${cmt.prices.get(qcount.index)} &nbsp; 
							</c:forEach></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:if>
