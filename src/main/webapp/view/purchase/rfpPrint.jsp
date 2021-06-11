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
						${language.getText("REQUEST_FOR_PROPOSAL")} ${record.description } <small class="pull-right"> ${language.getText("REQUEST_FOR_PROPOSAL")} <fmt:formatDate value="${now}" pattern="dd MMMM yyyy" /></small>
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
					<b>${language.getText("REQUEST_FOR_PROPOSAL")} ${rfp.id}</b><br>
					<br>
					<b> ${language.getText("REQUEST_DATE")} </b> <fmt:formatDate value="${rfp.requestDate}" pattern="dd MMMM yyyy" /> <br>
					<b> ${language.getText("END_OF_PROPOSAL")} </b> <fmt:formatDate value="${rfp.endOfProposal}" pattern="dd MMMM yyyy" /><br>
					<b> ${language.getText("DELIVERY_DATE")} </b> <fmt:formatDate value="${rfp.deliveryDate}" pattern="dd MMMM yyyy" /><br>
					<b> ${language.getText("DELIVERY_ADDRESS")} </b> ${rfp.deliveryAddress.street} ${rfp.deliveryAddress.city} ${rfp.deliveryAddress.state} ${rfp.deliveryAddress.country} ${rfp.deliveryAddress.phone} <br>
					<c:if test="${rfp.consortiumAllowed == 'Y'}"><br>${language.getText("CONSORTIUM_ALLOWED")}</c:if>
					<c:if test="${rfp.partialAllowed == 'Y'}"><br>${language.getText("PARTIAL_ALLOWED")}</c:if>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12 table-responsive">
					<table class="table table-stripe">
						<thead>
							<tr>
								<th>${language.getText("LINE")}</th>
								<th>${language.getText("MATERIAL_TYPE")}</th>
								<th>${language.getText("SPECIFICATION")}</th>
								<th>${language.getText("BRAND")}</th>
								<th>${language.getText("QUANTITY")}</th>
								<th>${language.getText("UNIT")}</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="item" items="${rfp.requestForProposalItems}" varStatus="lineStat">
								<tr>
									<td>${item.id.line}</td>
									<td>${item.materialType.caption}</td>
									<td>${item.specification}${item.material.partNumber}</td>
									<td>${item.manufacturer.caption}</td>
									<td>${item.quantity}</td>
									<td>${item.unit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
