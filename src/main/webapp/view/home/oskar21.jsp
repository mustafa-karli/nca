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
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>${PAGETITLE}</title>

<link rel="stylesheet" type="text/css" href="flags/allflags.css">
<link rel="stylesheet" type="text/css" href="css/tree.css" />
<link rel="stylesheet" type="text/css" href="css/nauticana.css" />
<link rel="stylesheet" type="text/css" href="css/comboTree.css" />

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.0/css/all.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="css/AdminLTE.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/bower_components/morris.js/morris.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/bower_components/jvectormap/jquery-jvectormap.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/bower_components/bootstrap-daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

</head>

<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper" style="height: auto;">
		<header id="header" class="main-header">
			<a href="." class="logo">
				<span class="logo-mini"><img src="${logomini}"/></span>
				<span class="logo-lg"><img src="${logo}"/></span>
			</a>

			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
					<i class="fa fa-bars"></i><span class="sr-only">Toggle navigation</span>
				</a>

				<div class="navbar-custom-menu">
					<input type="hidden" id="username" name="username" value="${userCaption}">

					<ul class="nav navbar-nav">
						<li class="dropdown notifications-menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-bell"></i> <span id="notifCount" class="notifCount label label-warning">${notifications.size()}</span></a>
							<ul class="dropdown-menu">
								<li class="header"><span class="notifCount">${notifications.size()}</span> tane bildiriminiz var.</li>
								<li>
									<ul class="menu">
										<c:forEach var="notify" items="${notifications}" varStatus="nCount">
											<li><span style="cursor: pointer;"><i class="fa fa-check complete pull-right" data-id="${notify.id}"> </i></span>
												<a href="#" onClick="doAjaxGet('${statics.get(notify.notificationType.tablename).rootMapping}/show?id=${notify.objectId}')">
													<i class="fa fa-search"> ${notify.description} - <fmt:formatDate value="${notify.dueDate}" pattern="dd-MM-yyyy hh:mm"/> </i>
												</a>
											</li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</li>
						
						<li><div class="btn-group btn">
								<a class="btn btn-info" data-toggle="dropdown"> <i class="${langClass}"></i></a>
								<ul class="dropdown-menu list-inline" role="menu">
									<c:forEach var="lang" items="${languageList}" varStatus="status">
										<li><span class="${lang.value}" onClick="document.location='?langcode=${lang.key}';"></span></li>
									</c:forEach>
								</ul>
							</div>
						</li>
						
						<li class="dropdown user user-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <span class="hidden-xs">${userCaption}</span>	</a>
						</li>
						
						<li>
							<a class="btn btn-success" onClick="document.location='userAccount/login';"> <i class="fa fa-user-circle"></i></a>
						</li>
						<li>
							<a class="btn btn-success" onClick="document.location='userAccount/setPassword';"> <i class="fa fa-key"></i></a>
						</li>
						<li>
							<a class="btn btn-danger" onClick="document.location='userAccount/logoff';"> <i class="fa fa-power-off"></i></a>
						</li>
					</ul>
				</div>
			</nav>
		</header>

		<aside class="main-sidebar">
			<section class="sidebar">
				<ul class="sidebar-menu tree" data-widget="tree">
					<c:forEach var="m" items="${usermenu}" varStatus="status">
						<li class="treeview active">
							<a href="#"> <i class="fa ${m.icon}"></i> <span> ${m.caption} </span> <span class="pull-right-container"> </span></a>
							<ul class="treeview-menu" style="display: none;">
								<c:forEach var="page" items="${m.captions}" varStatus="ps">
									<li>
										<a href="#" onClick="doAjaxGet('${m.urls[ps.index]}');"> <i class="fa ${m.icons[ps.index]}"> ${page} </i></a>
									</li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</section>
		</aside>

		<div class="content-wrapper" style="min-height: 916px;">

			<section class="content-header"></section>

			<section class="content">
				<div id="content">

					<c:forEach var="m" items="${usermenu}" varStatus="status">

						<div class="row">
							<div class="col-md-12">
								<div class="box collapsed-box">
									<div class="box-header with-border">
										<h3 class="box-title">
											<button type="button" class="btn btn-box-tool" data-widget="collapse">
												<i class="fa ${m.icon}"> ${m.caption}</i>
											</button>
										</h3>
										<div class="box-tools pull-right">
											<button type="button" class="btn btn-box-tool" data-widget="remove">
												<i class="fa fa-times"></i>
											</button>
										</div>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-md-12"></div>
											<!-- /.col -->
											<c:forEach var="page" items="${m.captions}" varStatus="ps">
												<div class="col-md-2 col-sm-4 col-xs-12">
													<a href="#" onClick="doAjaxGet('${m.urls[ps.index]}');">
														<div class="info-box bg-aqua">
															<span class="info-box-icon"><i class="fa ${m.icons[ps.index]}"></i></span>

															<div class="info-box-content">
																<span class="info-box-text"> ${m.captions[ps.index]} </span>
															</div>

															<!-- /.info-box-content -->
														</div>
													</a>
													<!-- /.info-box -->

												</div>
											</c:forEach>

										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- 						</div> -->
					</c:forEach>
				</div>
			</section>
		</div>
	</div>

</body>


<script src="https://adminlte.io/themes/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/jquery-ui/jquery-ui.min.js"></script>
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/raphael/raphael.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/morris.js/morris.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/moment/min/moment.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.5/jquery.inputmask.min.js"></script>
<script src="https://adminlte.io/themes/AdminLTE/bower_components/fastclick/lib/fastclick.js"></script>
<script src="js/adminlte.js"></script>

<script type="text/javascript" src="js/nauticana.js"></script>

<script type="text/javascript">

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

	if ($.trim($('#username').val())) {
		doAjaxGet('requestForProposal/allPublished');
//		doAjaxGet('requestForProposal/allPublishedMap');
	}

</script>

</html>
