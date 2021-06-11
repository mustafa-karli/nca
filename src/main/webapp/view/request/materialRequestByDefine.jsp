<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setLocale value="${language.localeStr}" scope="session" />
<div id="nonPrint">
	<div id="printArea">
		<table id="tableOrderbyDefine" class="" style="width: 100%">
			<thead>
				<tr>
					<th style="width: 30%"></th>
					<th style="width: 70%"></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="record" items="${records}" varStatus="rIndex">

					<tr class="trHeader" style="background: #87AFC7">
						<td><h3>
								<b>${record.organization.caption} ${language.getText("ORGANIZATION")}</b>
							</h3></td>
						<td><h3>
								<b>${record.id} Nu.lı ${language.getText("ORDER_ID")}</b>
							</h3></td>
					</tr>
					<tr>
						<td><b>${language.getText("ORDER_DATE")} :</b></td>
						<td><fmt:formatDate value="${record.requestDate}" pattern="dd MMMM yyyy" /></td>
					</tr>
					<tr>
						<td><b>${language.getText("DUE_DATE")}: </b></td>
						<td><fmt:formatDate value="${record.dueDate}" pattern="dd MMMM yyyy" /></td>
					</tr>
					<tr>
						<td><b>${language.getText("DESCRIPTION")} :</b></td>
						<td>${record.description}</td>
					</tr>

					<c:forEach var="line" items="${record.materialRequestItems}" varStatus="iIndex">

						<tr>
							<td><b>${language.getText("MATERIAL")} :</b></td>
							<td>${line.material.caption}</td>
						</tr>
						<c:forEach var="attr" items="${line.materialRequestItemAttrs}" varStatus="aIndex">
							<tr>
								<td><b> &nbsp; ${attr.materialAttributeGroup.caption}</b></td>
								<td>${attr.value}</td>
							</tr>
						</c:forEach>

						<tr>
							<td><b>${language.getText("QUANTITY")} :</b></td>
							<td>${line.quantity}${line.unit}</td>
						</tr>
					</c:forEach>

					<tr>
						<td><b> Resim : </b></td>
						<td><a href="contentRelation/firstData/MR/${record.id}" target="_blank"> <img src="contentRelation/firstThumb/MR/${record.id}">
						</a></td>
					</tr>



				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="box-footer">
		<a href="#" class="btn btn-warning" onClick="doAjaxGet('materialRequestReport/list');"> ${language.getText("CLOSE")} </a> <a id="btnPrint" href="#" class="btn btn-info"> ${language.getText("PRINT")} </a>
	</div>

</div>
<script>

$("#btnPrint").click(function () {
	var divToPrint = document.getElementById('tableOrderbyDefine');
    var htmlToPrint = '' +
        '<style type="text/css">' +
	        '.box {border:1px solid #000;'+
		        ' border-radius: 5px;'+
		        ' padding: 25px;}'+
	        'tr.trHeader {background: red}'+
	        'td {'+
		        'border: 1px solid #AAAAAA;'+
		        'padding: 5px 5px;}'+
		        'table { border-collapse: collapse;}' +   
        '</style>';
    htmlToPrint += divToPrint.outerHTML;
    newWin = window.open("");
    newWin.document.write("<h3 align='center'>Özel Pasta Siparişleri</h3>");
    newWin.document.write(htmlToPrint);
    newWin.print();
    newWin.close();
});

$(document).ready(function(){
	var title = "Özel Pasta Siparişi";
		$('#tableOrderbyDefine').DataTable( {
			'paging' : false,
			'ordering' : false,
			'responsive': {
		 		details : false
		 	} ,

		 	'scrollCollapse': false,
		 	dom: 'Bfrtip',
		    buttons: [
		        {
			        "extend": "copyHtml5",
		            "title"	: title	  
			     },
			     { 
				     "extend": "excelHtml5",
				     "title" : title},
	//		     { 
// 				     "extend": "csvHtml5",
// 				     "title" : title},
		        {
		            "extend": "pdfHtml5",
	//	            "orientation": "landscape",
		            "text" : "PDF",
		            "title"	: title	},
// 		        {
// 			        "extend": "print",
// 		            "title"	: title	 },
		    ],
		 	'language': {
		        'lengthMenu': 'Her sayfada _MENU_ kayıt görüntüleniyor.',
		        'zeroRecords': 'Kayıt bulunamadı',
		        'info': '_PAGES_ sayfanın _PAGE_. sayfası görüntüleniyor',
		        'infoEmpty': 'Kayıt bulunamadı',
		        'infoFiltered': '(filtered from _MAX_ total records)',
		        'search' : 'Arama',
		        'paginate': {
		            'previous': 'Önceki',
		            'next' : 'Sonraki'
		          }
		    },

			'columnDefs' : [
		         { "visible": false, "targets": 0 }
		     ]
		} );
} );



</script>
