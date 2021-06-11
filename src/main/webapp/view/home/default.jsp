<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="en_US" scope="session" />
<!DOCTYPE HTML>
<html ${LANGUAGE_DIRECTION}>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<title>${PAGETITLE}</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="flag-icon/css/flag-icon.min.css">
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="plugins/select2/select2.min.css">
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
<link type="text/css" rel="stylesheet" href="s/tree.css" />
<link type="text/css" rel="stylesheet" href="s/nauticana.css" />
<link type="text/css" rel="stylesheet" href="s/jquery.dataTables.min.css" />
<link type="text/css" rel="stylesheet" href="s/buttons.dataTables.min.css" />
<link href="plugins/datatables/extensions/Responsive/css/dataTables.responsive.css"	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/rowgroup/1.0.2/css/rowGroup.dataTables.min.css">
<script src="jquery/jquery-2.2.3.min.js"></script>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"> </script> -->
<script src="j/jquery.inputmask.bundle.min.js" type="text/javascript"> </script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- <script src="plugins/datatables/jquery.dataTables.min.js"></script> -->
<script type="text/javascript"	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"> </script>
<script type="text/javascript"	src="https://cdn.datatables.net/rowgroup/1.0.2/js/dataTables.rowGroup.min.js"> </script>

<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<script type="text/javascript"	src="plugins/datatables/extensions/Responsive/js/dataTables.responsive.min.js">
</script>
<script src="plugins/select2/select2.full.min.js"></script>
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="dist/js/app.min.js"></script>

<!-- <script type="text/javascript" src="j/font-awesome-all.js"></script> -->
<!-- <script type="text/javascript"	src="j/dataTables.buttons.min.multiheaders.js"></script> -->
<!-- <script type="text/javascript" src="/j/buttons.html5.min.multiheaders.js"></script> -->

<!-- <script type="text/javascript" src="/j/dataTables.buttons.min.js"></script> -->
<!-- <script type="text/javascript" src="j/jszip.min.js"></script> -->
<!-- <script type="text/javascript" src="j/pdfmake.min.js"></script> -->
<!-- <script type="text/javascript" src="j/vfs_fonts.js"></script> -->
<!-- <script type="text/javascript" src="/j/buttons.html5.min.js"></script> -->
<!-- <script type="text/javascript" src="/j/buttons.print.min.js"></script> -->

<!-- <script type="text/javascript" src="j/buttons.html5.min.multiheaders.js"></script> -->
<!-- <script type="text/javascript" src="j/buttons.print.min.multiheaders.js"></script> -->


<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript"	src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>





<script type="text/javascript" src="j/nauticana.js"></script>



<!-- https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js -->
<!-- https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js -->
<!-- https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js -->
<!-- https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js -->
<!-- https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js -->
<!-- https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js -->



</head>

<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper" style="height: auto;">
		<header id="header" class="main-header">
			<a href="." class="logo"> <span class="logo-mini"><img
					src="${logomini}" /></span> <span class="logo-lg"><img
					src="${logo}" /></span>
			</a>

			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<div class="navbar-custom-menu">
					<input type="hidden" id="username" name="username"
						value="${userCaption}">

					<ul class="nav navbar-nav">
						<li class="dropdown notifications-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-bell-o"></i> <span id="notifCount"
								class="notifCount label label-warning">${notifications.size()}</span></a>
							<ul class="dropdown-menu">
								<li class="header"><span class="notifCount">${notifications.size()}</span>
									tane bildiriminiz var.</li>
								<li>
									<ul class="menu">
										<c:forEach var="notify" items="${notifications}"
											varStatus="nCount">
											<li><span style="cursor: pointer;"><i class="fa fa-check complete pull-right"
													data-id="${notify.id}"> </i></span><a href="#"
												onClick="doAjaxGet('${statics.get(notify.notificationType.tablename).rootMapping}/show?id=${notify.objectId}')">
													<i class="fa fa-search"> ${notify.description} - <fmt:formatDate
															value="${notify.dueDate}" pattern="dd-MM-yyyy hh:mm" />
												</i> 
											</a></li>
										</c:forEach>
									</ul>
								</li>
							</ul></li>
						<li><div class="btn-group btn">
								<a class="btn btn-info" data-toggle="dropdown"> <i
									class="${langClass}"></i></a>
								<ul class="dropdown-menu list-inline" role="menu">
									<c:forEach var="lang" items="${languageList}"
										varStatus="status">
										<li><span class="${lang.value}"
											onClick="document.location='?langcode=${lang.key}';"></span></li>
									</c:forEach>
								</ul>
							</div></li>
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false"> <span class="hidden-xs">${userCaption}</span>
						</a></li>
						<li><a class="btn btn-success"
							onClick="document.location='userAccount/login';"> <i
								class="fa fa-user-circle"></i>
						</a></li>
						<li><a class="btn btn-success"
							onClick="document.location='userAccount/setPassword';"> <i
								class="fa fa-key"></i>
						</a></li>
						<li><a class="btn btn-danger"
							onClick="document.location='userAccount/logoff';"> <i
								class="fa fa-power-off"></i>
						</a></li>
					</ul>
				</div>
			</nav>
		</header>

		<aside class="main-sidebar">
			<section class="sidebar">
				<ul class="sidebar-menu">
					<c:forEach var="m" items="${usermenu}" varStatus="status">
						<li class="treeview active"><a href="#"> <i
								class="fa ${m.icon}"></i> <span> ${m.caption} </span> <span
								class="pull-right-container"> </span>
						</a>
							<ul class="treeview-menu" style="display: none;">
								<c:forEach var="page" items="${m.captions}" varStatus="ps">
									<li><a href="#"
										onClick="doAjaxGet('${m.urls[ps.index]}');"> <i
											class="fa ${m.icons[ps.index]}"> ${page} </i>
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
											<button type="button" class="btn btn-box-tool"
												data-widget="collapse">
												<i class="fa ${m.icon}"> ${m.caption}</i>
											</button>
										</h3>
										<div class="box-tools pull-right">
											<button type="button" class="btn btn-box-tool"
												data-widget="remove">
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
															<span class="info-box-icon"><i
																class="fa ${m.icons[ps.index]}"></i></span>

															<div class="info-box-content">
																<span class="info-box-text">
																	${m.captions[ps.index]} </span>
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

<script type="text/javascript">
	$('body').on('expanded.pushMenu collapsed.pushMenu', function() {
		debugger;
		setTimeout(function() {
			debugger;
			$('table.table').DataTable({
				retrieve : true,
				visible : true,
				api : true
			}).columns.adjust();
		}, 350);
	});

	$('i.complete').on('click',function(){
		var id = $(this).data("id");
		var item = $(this).parent().parent();
		url = "userNotification/restComplete/" + id;
		reqType = "POST";
		$.ajax({
	        type: reqType,
	        url: url,
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
debugger;
			        console.log("SUCCESS : ", data);

			        $('span.notifCount').text( $('span#notifCount').text() - 1)  ;
			        item.remove();
      		  },
	        error: function (e) {
	         console.log("ERROR : ", e);

	        }
	    });
		});


	
</script>

</html>
