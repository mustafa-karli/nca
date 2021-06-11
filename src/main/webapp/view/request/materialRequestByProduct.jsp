<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>

	<div class="box-body">

		<table id="tblProductReport" class="display nowrap"
			style="cellspacing: '0', width:'100%'">
			<thead>
				<tr>
					<th align="center">${language.getText("MATERIAL_ID")}</th>
					<th align="center">${language.getText("CAPTION")}</th>
					<th align="center">${language.getText("QUANTITY")}</th>
					<th align="center">${language.getText("UNIT")}</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="record" items="${totals}" varStatus="rIndex">
					<tr>
						<td>${record.materialId}</td>
						<td>${record.caption}</td>
						<td>${record.quantity}</td>
						<td>${record.unit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<div class="box-footer">
		<a href="#" class="btn btn-warning"
			onClick="doAjaxGet('materialRequestReport/list');">
			${language.getText("CLOSE")} </a>
	</div>
</div>

<script type="text/javascript" src="js/dataTables.style.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var title = "Sipariş Edilen Tüm Malzemeler"	;
$('#tblProductReport').DataTable( {
	'responsive': {
 		details : false
 	} ,

 	'scrollCollapse': false,
 	dom: 'Bfrtip',
    buttons: [
        {
	        "extend": "copyHtml5",
            "title"	:   title
	     },
	     { 
		     "extend": "excelHtml5",
		     "title" : title},
//		     { 
//			     "extend": "csvHtml5",
//			     "title" : title},
        {
            "extend": "pdfHtml5",
//	            "orientation": "landscape",
            "text" : "PDF",
            "title"	: title	},
        {
	        "extend": "print",
            "title"	: title	 },
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
//    order: [[0, 'asc']],
//	    'rowGroup' : {
//	        'startRender': null,
//	        'endRender': function ( rows, group ) {
//	            return group +' ('+rows.count()+')';
//	        },
//	        'dataSrc': 0
//	    }
} );

});
</script>


