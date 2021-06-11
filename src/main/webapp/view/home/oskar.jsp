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
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<title>${PAGETITLE}</title>

<link rel="stylesheet" type="text/css" href="flags/allflags.css">
<link rel="stylesheet" type="text/css" href="css/AdminLTE.min.css">
<link rel="stylesheet" type="text/css" href="css/skins/_all-skins.min.css">
<link rel="stylesheet" type="text/css" href="css/tree.css" />
<link rel="stylesheet" type="text/css" href="css/nauticana.css" />
<link rel="stylesheet" type="text/css" href="css/comboTree.css" />

<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.6.0/css/all.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs-3.3.7/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-flash-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />

</head>

<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper" style="height: auto;">
		<header id="header" class="main-header">
			<a href="." class="logo"> <span class="logo-mini"><img src="${logomini}" /></span> <span class="logo-lg"><img src="${logo}" /></span>
			</a>

			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<div class="navbar-custom-menu">
					<input type="hidden" id="username" name="username" value="${userCaption}">

					<ul class="nav navbar-nav">
						<li class="dropdown notifications-menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span id="notifCount" class="notifCount label label-warning">${notifications.size()}</span></a>
							<ul class="dropdown-menu">
								<li class="header"><span class="notifCount">${notifications.size()}</span> tane bildiriminiz var.</li>
								<li>
									<ul class="menu">
										<c:forEach var="notify" items="${notifications}" varStatus="nCount">
											<li><span style="cursor: pointer;"><i class="fa fa-check complete pull-right" data-id="${notify.id}"> </i></span><a href="#" onClick="doAjaxGet('${statics.get(notify.notificationType.tablename).rootMapping}/show?id=${notify.objectId}')"> <i class="fa fa-search"> ${notify.description} - <fmt:formatDate value="${notify.dueDate}"
															pattern="dd-MM-yyyy hh:mm" />
												</i>
											</a></li>
										</c:forEach>
									</ul>
								</li>
							</ul></li>
						<li><div class="btn-group btn">
								<a class="btn btn-info" data-toggle="dropdown"> <i class="${langClass}"></i></a>
								<ul class="dropdown-menu list-inline" role="menu">
									<c:forEach var="lang" items="${languageList}" varStatus="status">
										<li><span class="${lang.value}" onClick="document.location='?langcode=${lang.key}';"></span></li>
									</c:forEach>
								</ul>
							</div></li>
						<li class="dropdown user user-menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <span class="hidden-xs">${userCaption}</span>
						</a></li>
						<li><a class="btn btn-success" onClick="document.location='userAccount/login';"> <i class="fa fa-user-circle"></i>
						</a></li>
						<li><a class="btn btn-success" onClick="document.location='userAccount/setPassword';"> <i class="fa fa-key"></i>
						</a></li>
						<li><a class="btn btn-danger" onClick="document.location='userAccount/logoff';"> <i class="fa fa-power-off"></i>
						</a></li>
					</ul>
				</div>
			</nav>
		</header>

		<aside class="main-sidebar">
			<section class="sidebar">
				<ul class="sidebar-menu">
					<c:forEach var="m" items="${usermenu}" varStatus="status">
						<li class="treeview active"><a href="#"> <i class="fa ${m.icon}"></i> <span> ${m.caption} </span> <span class="pull-right-container"> </span>
						</a>
							<ul class="treeview-menu" style="display: none;">
								<c:forEach var="page" items="${m.captions}" varStatus="ps">
									<li><a href="#" onClick="doAjaxGet('${m.urls[ps.index]}');"> <i class="fa ${m.icons[ps.index]}"> ${page} </i>
									</a></li>
								</c:forEach>
							</ul></li>
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

<script type="text/javascript" src="https://unpkg.com/popper.js@1.14.6/dist/umd/popper.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs-3.3.7/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-flash-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.2.7/raphael.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/qtip2/3.0.3/jquery.qtip.min.js"></script>

<script type="text/javascript" src="js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="js/adminlte.min.js"></script>
<script type="text/javascript" src="js/nauticana.js"></script>

<script type="text/javascript" src="js/path.city.TR.js"></script>
<script type="text/javascript" src="js/map.TR.js?v=123"></script>


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
		
	$(function(){
		$("#map svg path").hover(
			function() {
				var id=$(this).attr("id");
				$("#sehir").text(id);
		});
		$("#map svg path").click(
			function() {
				var id=$(this).attr("id");
				$("#sehir").text(id + " clicked");
		});
	})

</script>

</html>
