$(document).ready(function () {
$('.report').DataTable({
 	'responsive': {
 		details : false
 	} ,

 	'scrollCollapse': false,
 	dom: 'Bfrtip',
    buttons: [
        'copyHtml5',
//        'excelHtml5',
//        'csvHtml5',
        {
            "extend": "pdfHtml5",
            "orientation": "landscape",
            "text" : "PDF"		                    
        },
        'print'
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
    }
}).columns.adjust().draw();



$('.wide-list').DataTable({
 	'paging':false, 
 	'scrollY':'50vh',
	'scrollX': true,
  	'lengthChange':false, 
 	'searching':false, 
 	'ordering':false, 
 	'info':true, 
 	'autoWidth': true ,
 	'responsive': true,
 	'scrollCollapse': false,
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
    }
}).columns.adjust().draw();

$('.thin-list').DataTable({
	'paging':false, 
	'scrollY': '50vh', 
	'lengthChange':false, 
	'searching':true, 
	'ordering':true, 
	'info':true, 
	'autoWidth': true, 
	'responsive': true, 
	'scrollCollapse': true ,
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
    }
}).columns.adjust().draw();


$('.list').DataTable({
	"responsive" :{
        details: {
            type: 'column'
        }
    },
    "columnDefs" : [ { responsivePriority: 1, orderable: false, targets: -1 },
    				{ className: 'control', orderable: false, targets:   0 }],
    "order" : [ 1, 'asc' ] ,
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
    }
}).columns.adjust().draw();


$('.showlist').DataTable({
	'paging':false,
	'scrollY': '50vh',
	'lengthChange':false,
	'searching':true,
	'ordering':true,
	'info':true,
	'autoWidth': true,
	'responsive': true,
	'scrollCollapse': true,
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
    }
}).columns.adjust().draw();

$('.simple-table').DataTable({
	'scrollX'   : false,
	'responsive': true,
	'ordering'  : false,
	'paging'    : false,
	'info'      : false,
 	'searching' : false
}).columns.adjust().draw();

}) 