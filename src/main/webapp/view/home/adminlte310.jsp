<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<fmt:setLocale value="en_US" scope="session" />
<!DOCTYPE HTML>
<html ${LANGUAGE_DIRECTION}>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${PAGETITLE}</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

<!-- Font Awesome -->
<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">
<link rel="stylesheet" type="text/css" href="flags/allflags.css">

<!-- Bootstrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

<!-- DataTables -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.7/css/responsive.bootstrap4.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.7.0/css/buttons.bootstrap4.min.css">


<!-- Form elements -->

<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/select2/css/select2.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/bs-stepper/css/bs-stepper.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/v3/plugins/dropzone/min/dropzone.min.css">

<!-- Theme style -->
<link rel="stylesheet" href="https://adminlte.io/themes/v3/dist/css/adminlte.min.css">
</head>

<body class="hold-transition sidebar-mini">

<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
  
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Home</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
    
      <!-- Navbar Search -->
      <li class="nav-item">
        <a class="nav-link" data-widget="navbar-search" href="#" role="button">
          <i class="fas fa-search"></i>
        </a>
        <div class="navbar-search-block">
          <form class="form-inline">
            <div class="input-group input-group-sm">
              <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
              <div class="input-group-append">
                <button class="btn btn-navbar" type="submit">
                  <i class="fas fa-search"></i>
                </button>
                <button class="btn btn-navbar" type="button" data-widget="navbar-search">
                  <i class="fas fa-times"></i>
                </button>
              </div>
            </div>
          </form>
        </div>
      </li>

      <!-- Notifications Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-bell"></i>
          <span class="badge badge-warning navbar-badge">${notifications.size()}</span>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
			<span class="dropdown-item dropdown-header">${notifications.size()} Notifications</span>
			<c:forEach var="notify" items="${notifications}" varStatus="nCount">
				<div class="dropdown-divider"></div>
				<span style="cursor: pointer;">
				  <i class="fa fa-check complete pull-right" data-id="${notify.id}"> </i>
				</span>
				<a href="#"  class="dropdown-item" onClick="doAjaxGet('${statics.get(notify.notificationType.tablename).rootMapping}/show?id=${notify.objectId}')">
					<i class="fa fa-search" > ${notify.description} - <fmt:formatDate value="${notify.dueDate}" pattern="dd-MM-yyyy hh:mm" />												</i>
				</a>
			</c:forEach>
        </div>
      </li>

		<!-- Language selection -->
		<li class="nav-item dropdown">
        	<a class="nav-link" data-toggle="dropdown" href="#">
          		<i class="${langClass}"></i>
        	</a>
        	<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
				<span class="dropdown-item dropdown-header">Interface Language</span>
				<c:forEach var="lang" items="${languageList}" varStatus="status">
				<a href="#" class="dropdown-item">
		            <span class="fas mr-2 ${lang.value}" onClick="document.location='?langcode=${lang.key}';">  </span>
				</a>
				</c:forEach>
			</div>
		</li>

		<!-- User Menu -->
		<li class="nav-item dropdown">
        	<a class="nav-link" data-toggle="dropdown" href="#">
          		<i class="fa fa-user"></i>
        	</a>
        	<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
				<span class="dropdown-item dropdown-header">${userCaption}</span>
				<div class="dropdown-divider"></div>
				<a class="btn btn-success" onClick="document.location='userAccount/login';"> Login </a>
				<div class="dropdown-divider"></div>
				<a class="btn btn-success" onClick="document.location='userAccount/setPassword';"> Change password </a>
				<div class="dropdown-divider"></div>
				<a class="btn btn-danger" onClick="document.location='userAccount/logoff';"> <i class="fa fa-power-off"></i> Logout	</a>
			</div>
		</li>


      <li class="nav-item">
        <a class="nav-link" data-widget="fullscreen" href="#" role="button">
          <i class="fas fa-expand-arrows-alt"></i>
        </a>
      </li>


      <li class="nav-item">
        <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
          <i class="fas fa-th-large"></i>
        </a>
      </li>
      
    </ul>
  </nav>
  <!-- /.navbar -->

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="#" class="brand-link">
		<span class="logo-mini"><img src="${logomini}" class="brand-image img-circle elevation-3" style="opacity: .8"/> </span>
		<span class="logo-lg"><img src="${logo}" /> </span>
		<span class="brand-text font-weight-light">Nauticana</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">

		<!-- SidebarSearch Form -->
		<div class="form-inline">
			<div class="input-group" data-widget="sidebar-search">
				<input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
				<div class="input-group-append">
					<button class="btn btn-sidebar">
						<i class="fas fa-search fa-fw"></i>
					</button>
				</div>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">

			<c:forEach var="m" items="${usermenu}" varStatus="status">
				<li class="nav-item">
					<a href="#" class="nav-link">
						<i class="nav-icon fas ${m.icon}"></i>
						${m.caption} <i class="right fas fa-angle-left"></i>
					</a>
					<ul class="nav nav-treeview">
					<c:forEach var="page" items="${m.captions}" varStatus="ps">
						<li class="nav-item">
							<a href="#" onClick="doAjaxGet('${m.urls[ps.index]}');" class="nav-link">
								<i class="nav-icon far ${m.icons[ps.index]}">${page}</i>
							</a>
						</li>
					</c:forEach>
					</ul>
				</li>
			</c:forEach>
        	</ul>
		</nav>
		<!-- /.sidebar-menu -->
    </div>
	<!-- /.sidebar -->
</aside>

  <!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">

	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>${PAGETITLE}</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">${PAGETITLE}</li>
					</ol>
				</div>
			</div>
		</div><!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<section class="content">
		<div id="content" class="container-fluid">
		
			<div class="row">
			<c:forEach var="m" items="${usermenu}" varStatus="status">
				<div class="col-6 col-sm-3 col-md-2">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title"><i class="fa ${m.icon}"> ${m.caption}</i></h3>
						</div>
						<div class="card-body">
						<c:forEach var="page" items="${m.captions}" varStatus="ps">
							<p><a href="#" onClick="doAjaxGet('${m.urls[ps.index]}');">
							<i class="fa ${m.icons[ps.index]}"></i>
							${m.captions[ps.index]}</a></p>
						</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<footer class="main-footer">
	<div class="float-right d-none d-sm-block">
		<b>Nauticana Cloud Applications. Copyright &copy; 2018-2021 <a href="https://www.nauticana.com">Nauticana</a></b> 
    </div>
    <div></div>
</footer>

<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
	<!-- Control sidebar content goes here -->
</aside>
<!-- /.control-sidebar -->

</div>
<!-- ./wrapper -->












<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<!-- Bootstrap 4 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- DataTables  & Plugins -->
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.7/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.7/js/responsive.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.7.0/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.7.0/js/buttons.bootstrap4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.7.0/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.7.0/js/buttons.print.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.7.0/js/buttons.colVis.min.js"></script>


<!-- Form Elements -->

<!-- 
<script src="https://adminlte.io/themes/v3/plugins/select2/js/select2.full.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/moment/moment.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/inputmask/jquery.inputmask.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/daterangepicker/daterangepicker.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/bs-stepper/js/bs-stepper.min.js"></script>
<script src="https://adminlte.io/themes/v3/plugins/dropzone/min/dropzone.min.js"></script>
 -->
 

<!-- AdminLTE App -->
<script src="https://adminlte.io/themes/v3/dist/js/adminlte.min.js"></script>

<!-- Nauticana scripts -->
<script type="text/javascript" src="js/nauticana.js"></script>

<script type="text/javascript">

/*  $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'copy', 'excel', 'pdf', 'colvis' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-md-6:eq(0)' );
  } );
*/

	$('body').on('expanded.pushMenu collapsed.pushMenu', function() {
		setTimeout(function() {
			$('table.table').DataTable({
				retrieve : true,
				visible : true,
				api : true
			}).columns.adjust();
		}, 350);
	});

	$('i.complete').on('click',function(){
		var id = $(this).data("id");
		var item=$(this).parent().parent(); url="userNotification/restComplete/" + id;
		reqType="POST" ;
		$.ajax({
	        type: reqType,
	        url: url,
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
			        $('span.notifCount').text( $('span#notifCount').text() - 1)  ;
			        item.remove();
      		  },
	        error: function (e) {
	         console.log("ERROR: ", e);

	        }
	    });
	});

//	if ($.trim($('#username').val())) {
//		doAjaxGet('requestForProposal/allPublished');
//		doAjaxGet('requestForProposal/allPublishedMap');
//	}
		
</script>

</body>
</html>
