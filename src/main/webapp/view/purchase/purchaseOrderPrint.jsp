<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="en_US" scope="session" />
<!DOCTYPE HTML>
<html ${LANGUAGE_DIRECTION}>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<title>${PAGETITLE}</title>

<link rel="stylesheet" type="text/css" href="../css/AdminLTE.min.css">
<link rel="stylesheet" type="text/css" href="../css/skins/_all-skins.min.css">
<link rel="stylesheet" type="text/css" href="../css/tree.css" />
<link rel="stylesheet" type="text/css" href="../css/nauticana.css" />
<link rel="stylesheet" type="text/css" href="../css/nauticana.table.css" />

<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.6.0/css/all.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs-3.3.7/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-flash-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />

</head>

<body>
	<div class="wrapper">
		<section class="invoice">

			<div class="row">
				<div class="col-xs-12">
					<h2 class="page-header">
						${language.getText("PURCHASE_ORDER")} ${record.description } <small class="pull-right"> ${language.getText("PURCHASE_ORDER")} <fmt:formatDate value="${now}" pattern="dd MMMM yyyy" /></small>
					</h2>
				</div>
			</div>

			<div class="row invoice-info">
				<div class="col-sm-4 invoice-col">
					${language.getText("FROM_WHO")}
					<address>
						<strong>${customer.caption}</strong><br>
						${customerAddress.street}<br>
						${customerAddress.city}, ${customerAddress.state} ${customerAddress.country}<br>
						${language.getText("PHONE")} : ${customerAddress.phone}<br>
						${language.getText("TAX_NUMBER")} : ${customer.taxCenter} ${customer.taxNumber}
					</address>
				</div>

				<div class="col-sm-4 invoice-col">
					${language.getText("TO_WHO")}
					<address>
						<strong>${vendor.caption}</strong><br>
						${vendorAddress.street}<br>
						${vendorAddress.city}, ${vendorAddress.state} ${vendorAddress.country}<br>
						${language.getText("PHONE")} : ${customerAddress.phone}<br>
						${language.getText("TAX_NUMBER")} : ${customer.taxCenter} ${customer.taxNumber}
					</address>
				</div>

				<div class="col-sm-4 invoice-col">
					<b>${language.getText("PURCHASE_ORDER")} ${po.id}</b><br>
					<br><b>${language.getText("ORGANIZATION")}:</b> ${po.organization.caption}
					<br><b>${language.getText("ORDER_DATE")}:</b> <fmt:formatDate value="${po.orderDate}" pattern="dd MMMM yyyy" />
					<br><b>${language.getText("DUE_DATE")}:</b> <fmt:formatDate value="${po.dueDate}" pattern="dd MMMM yyyy" />
					<br><b>${language.getText("DESCRIPTION")}:</b> ${po.description}
					<br><b>${language.getText("USERNAME")}:</b> ${po.username}
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12 table-responsive">
					<table class="table table-stripe">
						<thead>
							<tr>
								<th>${language.getText("LINE")}</th>
								<th>${language.getText("MATERIAL")}</th>
								<th>${language.getText("BRAND")}</th>
								<th>${language.getText("QUANTITY")}</th>
								<th>${language.getText("UNIT")}</th>
								<th>${language.getText("UNIT_PRICE")}</th>
								<th>${language.getText("TAX")}</th>
								<th>${language.getText("ITEM_PRICE")}</th>
								<th>${language.getText("DISCOUNT")}</th>
								<th>${language.getText("TOTAL")}</th>
								<th>${language.getText("CURRENCY")}</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="maxline" value="0"></c:set>
							<c:forEach var="item" items="${po.purchaseOrderItems}" varStatus="status">
								<c:set var="maxline" value="${status.index+1}"></c:set>
								<c:set var="itemPrice" value="${item.quantity * item.unitPrice - item.discount}"></c:set>
								<c:set var="taxPrice" value="0"></c:set>
								<c:forEach var="tax" items="${item.purchaseOrderTaxes}" varStatus="stax">
									<c:set var="taxPrice" value="${taxPrice + tax.amount}"></c:set>
								</c:forEach>

								<tr>
									<td>${item.id.line}
										<input type="hidden" name="price${item.id.line}" id="price${item.id.line}" value="${itemPrice}" />
										<input type="hidden" name="tax${item.id.line}" id="tax${item.id.line}" value="${taxPrice}" />
										<input type="hidden" name="total${item.id.line}" id="total${item.id.line}" value="${itemPrice+taxPrice}" />
										<input type="hidden" name="currency${item.id.line}" id="currency${item.id.line}" value="${item.currency}" />
									</td>
									<td>${item.material.caption}</td>
									<td><c:if test="${empty item.material.manufacturer}"> ${item.material.manufacturer.caption} </c:if></td>
									<td>${item.quantity}</td>
									<td>${item.unit}</td>
									<td class="rightalign"><fmt:formatNumber type="number" pattern="#,###.00" value="${item.unitPrice}" /></td>
									<td class="rightalign"><fmt:formatNumber type="number" pattern="#,###.00" value="${taxPrice}" /></td>
									<td class="rightalign"><fmt:formatNumber type="number" pattern="#,###.00" value="${itemPrice}" /></td>
									<td class="rightalign"><fmt:formatNumber type="number" pattern="#,###.00" value="${item.discount}" /></td>
									<td class="rightalign"><fmt:formatNumber type="number" pattern="#,###.00" value="${itemPrice + taxPrice}" /></td>
									<td>${item.currency}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" name="maxline" id="maxline" value="${maxline}" />
				</div>
			</div>

			<div class="row">
				<div class="col-xs-6">
					<p class="lead">${language.getText("DELIVERY_ADDRESS")} :</p>
					<address>
						${deliveryAddress.street}<br> ${deliveryAddress.city}, ${deliveryAddress.state} ${deliveryAddress.country}<br> ${language.getText("PHONE")} : ${deliveryAddress.phone}<br>
					</address>
				</div>

				<div class="col-xs-6">
					<p class="lead">${language.getText("TOTAL_PAYMENT")}</p>

					<div class="table-responsive">
						<table class="table">
							<tr>
								<th style="width: 50%">${language.getText("ITEM_TOTAL")}</th>
								<td><span id="subtotal">0</span></td>
							</tr>
							<tr>
								<th style="width: 50%">${language.getText("TAX")}</th>
								<td><span id="taxtotal">0</span></td>
							</tr>
							<tr>
								<th style="width: 50%">${language.getText("TOTAL")}</th>
								<td><span id="alltotal">0</span></td>
							</tr>
						</table>
					</div>
				</div>

			</div>
		</section>
	</div>
</body>

<script type="text/javascript" src="https://unpkg.com/popper.js@1.14.6/dist/umd/popper.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs-3.3.7/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-flash-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="../js/adminlte.min.js"></script>
<script type="text/javascript" src="../js/nauticana.js"></script>
<script type="text/javascript" src="../js/nauticana.style.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	var total = 0;
	var curr = new Array("TRY","EUR","USD","GBP","AUD","CAD");
	var tots = [0,0,0,0,0,0];
	var fins = [0,0,0,0,0,0];
	var taxs = [0,0,0,0,0,0];
	var itemTotal  = '';
	var taxTotal   = '';
	var totalPrice = '';
	var maxline = document.getElementById('maxline').value;
	
	for(i=1;i<=maxline;i++) {
		var p=Number(document.getElementById('price'+i).value.replace(' ',''));
		var t=Number(document.getElementById('tax'+i).value.replace(' ',''));
		var o=Number(document.getElementById('total'+i).value.replace(' ',''));
		var c=document.getElementById('currency'+i).value;
		for(j=0;j<6;j++) {
			if (curr[j] == c) {
				tots[j] = tots[j] + p;
				taxs[j] = taxs[j] + t;
				fins[j] = fins[j] + o;
			}
		}
	}
	for(j=0;j<6;j++) {
		if (tots[j] > 0) {
			var formatter = new Intl.NumberFormat('tr-TR', {
			  style: 'currency',
			  currency: curr[j],
			  minimumFractionDigits: 2
			});
			var fmt1 = formatter.format(tots[j]);
			var fmt2 = formatter.format(fins[j]-tots[j]);
			var fmt3 = formatter.format(fins[j]);
			
			itemTotal  = itemTotal  + fmt1 + ' ';
			taxTotal   = taxTotal   + fmt2 + ' ';
			totalPrice = totalPrice + fmt3 + ' ';
		}
	}
	$("#subtotal").text(itemTotal);
	$("#taxtotal").text(taxTotal);
	$("#alltotal").text(totalPrice);
});

</script>
</html>
