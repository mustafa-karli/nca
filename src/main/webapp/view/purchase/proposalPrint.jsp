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
<link rel="stylesheet" type="text/css" href="../css/comboTree.css" />

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
						${language.getText("PROPOSAL")} ${record.description } <small class="pull-right"> ${language.getText("PROPOSAL")} <fmt:formatDate value="${now}" pattern="dd MMMM yyyy" /></small>
					</h2>
				</div>
			</div>

			<div class="row invoice-info">
				<div class="col-sm-4 invoice-col">
					${language.getText("FROM_WHO")}
					<address>
						<strong>${vendor.caption}</strong><br>
						${vendorAddress.street}<br>
						${vendorAddress.city}, ${vendorAddress.state} ${vendorAddress.country}<br>
						${language.getText("PHONE")} : ${customerAddress.phone}<br>
						${language.getText("TAX_NUMBER")} : ${customer.taxCenter} ${customer.taxNumber}
					</address>
				</div>

				<div class="col-sm-4 invoice-col">
					${language.getText("TO_WHO")}
					<address>
						<strong>${customer.caption}</strong><br>
						${customerAddress.street}<br>
						${customerAddress.city}, ${customerAddress.state} ${customerAddress.country}<br>
						${language.getText("PHONE")} : ${customerAddress.phone}<br>
						${language.getText("TAX_NUMBER")} : ${customer.taxCenter} ${customer.taxNumber}
					</address>
				</div>

				<div class="col-sm-4 invoice-col">
					<b>${language.getText("PROPOSAL")} ${prfp.id}</b><br>
					<br>
					<b>${language.getText("VALID_UNTIL")}:</b> <fmt:formatDate value="${prfp.validUntil}" pattern="dd MMMM yyyy" /> <br>
					<b>${language.getText("SHIPMENT_BY")}:</b> ${prfp.shipmentBy} <br>
					<b>${language.getText("PAYMENT_TYPE")}:</b> ${prfp.paymentType}
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12 table-responsive">
					<table class="table table-stripe" >
						<thead>
							<tr>
								<th>${language.getText("LINE")}</th>
								<th>${language.getText("MATERIAL")}</th>
								<th>${language.getText("BRAND")}</th>
								<th>${language.getText("QUANTITY")}</th>
								<th>${language.getText("UNIT")}</th>
								<th>${language.getText("UNIT_PRICE")}</th>
								<th>${language.getText("DISCOUNT_PCT")}</th>
								<th>${language.getText("TAX_PCT")}</th>
								<th>${language.getText("TAX_PRICE")}</th>
								<th>${language.getText("ITEM_PRICE")}</th>
								<th>${language.getText("TOTAL")}</th>
								<th>${language.getText("CURRENCY")}</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="itemTotal" value="0"> </c:set>
							<c:forEach var="item" items="${prfp.proposalToRfpItems}" varStatus="status">
								<tr>
									<c:set var="itemPrice" value="${item.quantity * item.unitPrice * (100-item.discountPct)/100}"> </c:set>
									<c:set var="taxPrice" value="${itemPrice * item.taxPct / 100}"> </c:set>
									<td>${item.id.line}</td>
									<td>${item.material.caption} </td>
									<td><c:if test="${empty item.material.manufacturer}"> ${item.material.manufacturer.caption} </c:if> </td>
									<td> ${item.quantity}</td>
									<td>${item.unit}</td>
									<td class="rightalign"> <fmt:formatNumber type = "number" pattern = "#,###.00" value = "${item.unitPrice}" /> </td>
									<td class="rightalign"> ${item.discountPct}</td>
									<td class="rightalign"> ${item.taxPct}</td>
									<td class="rightalign"> <fmt:formatNumber type = "number" pattern = "#,###.00" value = "${taxPrice}" /></td>
									<td class="rightalign"> <fmt:formatNumber type = "number" pattern = "#,###.00" value = "${itemPrice}" /></td>
									<td class="rightalign"> <fmt:formatNumber type = "number" pattern = "#,###.00" value = "${itemPrice + taxPrice}" /></td>
									<td>${item.currency}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-6">
					<p class="lead">${language.getText("NOTES")}</p>
					<p><b> ${language.getText("PAYMENT_NOTE")} : </b> ${prfp.paymentNote}</p>
					<p><b> ${language.getText("DELIVERY_NOTE")} : </b> ${prfp.deliveryNote}</p>
					<p><b> ${language.getText("DELIVERY_ADDRESS")} : </b> </p>
					<address>
						${deliveryAddress.street}<br>
						${deliveryAddress.city}, ${deliveryAddress.state} ${deliveryAddress.country}<br>
						${language.getText("PHONE")} : ${deliveryAddress.phone}<br>
					</address>
				</div>

				<div class="col-xs-6">
					<p class="lead">${language.getText("TOTAL_PRICE")}</p>
					${prfp.totalPrice}
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


</html>
