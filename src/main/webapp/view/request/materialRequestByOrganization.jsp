<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<ncaTags:organizationRequest topOrganization="${topOrganization}" language="${language}" />

<div class="box box-primary">
	<div class="box-footer">
		<a href="#" class="btn btn-warning" onClick="doAjaxGet('materialRequestReport/list');"> ${language.getText("CLOSE")} </a>
	</div>
</div>


<script>
$(document).ready(function(){
	var title = "";
	$('table').each(function(){
		title = $(this).data("caption") + " ${language.getText('REPORT')}";
		$(this).DataTable( {
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
//		    order: [[0, 'asc']],
// 		    'rowGroup' : {
// 		        'startRender': null,
// 		        'endRender': function ( rows, group ) {
// 		            return group +' ('+rows.count()+')';
// 		        },
// 		        'dataSrc': 0
// 		    }
		} );
	
});
});


</script>