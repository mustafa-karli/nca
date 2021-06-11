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

<title>${PAGETITLE}</title>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" type="text/css" href="flag-icon/css/flag-icon.min.css">
<link rel="stylesheet" type="text/css" href="mlz/css/tree.css" />
<link rel="stylesheet" type="text/css" href="mlz/css/nauticana.css" />
<link rel="stylesheet" type="text/css" href="mlz/css/comboTree.css" />
<link rel="stylesheet" type="text/css" href="mlz/css/style.css" />

<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.6.0/css/all.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4-4.1.1/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css"/>

<script type="text/javascript" src="https://unpkg.com/popper.js@1.14.6/dist/umd/popper.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4-4.1.1/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="mlz/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="mlz/js/nauticana.js"></script>


<style>
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	right: auto;
	margin-top: -6px;
	margin-left: -1px;
	-weblit-border-radius: 0 6px 6xp 6px;
	-moz-border-radius: 0 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
	display: block;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
}

@media ( min-width :768px) {
	.navbar-right .dropdown-menu {
		right: auto;
		left: 100%;
	}
}
</style>


</head>

<body>

	<header id="header" class="main-header" role="banner">

		<nav class="navbar navbar-static-top navbar-expand-lg navbar-light bg-light" role="navigation">
			<div class="container">
				<a class="navbar-brand" href="/">Malazima</a>

				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarsExample05">

					<ul class="navbar-nav ml-auto pl-lg-5 pl-0">

						<c:if test="${!empty usermenu}">
							<c:forEach var="m" items="${usermenu}" varStatus="status">
								<li class="nav-item dropdown">
									<a class="nav-link dropdown-toggle" href="#" id="dropdown${status.index}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fa ${m.icon}"> ${m.caption} </i>	</a>
									<div class="dropdown-menu" aria-labelledby="dropdown${status.index}">
										<c:forEach var="page" items="${m.captions}" varStatus="ps">
											<a class="dropdown-item" href="#" onClick="doAjaxGet('${m.urls[ps.index]}');"> <i class="fa ${m.icons[ps.index]}"> ${page} </i>	</a>
										</c:forEach>
									</div>
								</li>
							</c:forEach>
						</c:if>

						<li class="nav-item"><a class="nav-link" href="register.html">Login</a></li>

						<li class="nav-item"><a class="nav-link" href="login.html">Register</a></li>

						<c:if test="${!empty notifications}">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="notifications" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fa fa-bell-o"></i> <span id="notifCount" class="notifCount label label-warning">${notifications.size()}</span> </a>
							<div class="dropdown-menu" aria-labelledby="notifications">
							<c:forEach var="notify" items="${notifications}" varStatus="nCount">
								<span style="cursor: pointer;"><i class="fa fa-check complete pull-right" data-id="${notify.id}"> </i></span>
								<a class="dropdown-item" href="#" onClick="doAjaxGet('${statics.get(notify.notificationType.tablename).rootMapping}/show?id=${notify.objectId}')"> <i class="fa fa-search"> ${notify.description} - <fmt:formatDate value="${notify.dueDate}" pattern="dd-MM-yyyy hh:mm" /> </i> </a>
							</c:forEach>
							</div>
						</li>
						</c:if>

						<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="languages" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="${langClass}"></i> </a>
							<div class="dropdown-menu" aria-labelledby="languages">
							<c:forEach var="lang" items="${languageList}" varStatus="status">
								<a class="dropdown-item" href="#" onClick="document.location='?langcode=${lang.key}';"><i class="${lang.value}"></i></a>
							</c:forEach>
							</div>
						</li>

						<li class="nav-item"><a class="nav-link" onClick="document.location='userAccount/login';"> <i class="fa fa-user-circle"></i></a></li>

						<c:if test="${!empty userCaption}">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="userAccount" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> ${userCaption} <span class="caret"></span> </a>
							<div class="dropdown-menu" aria-labelledby="userAccount">
								<a class="dropdown-item" href="#" onClick="document.location='userAccount/setPassword';"><i class="fa fa-key"> Set password </i></a>
								<a class="dropdown-item" href="#" onClick="document.location='userAccount/logoff';"><i class="fa fa-power-off"> Log out</i></a>
							</div>
						</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>

		<input type="hidden" id="username" name="username" value="${userCaption}">

	</header>

    <section class="content" id="content">
      <div class="container">

        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-8 text-center">
            <h2 class="text-uppercase heading border-bottom mb-4">Services</h2>
            <p class="mb-3 lead">We provide construction material procurement services. Scroll down to see how it works.</p>
          </div>
        </div>

        <div class="row mb-5">

          <div class="col-lg-4 col-md-6 col-12 mb-3 element-animate">
            <div class="media d-block media-feature text-center">
              <span class="flaticon-blueprint icon"></span>
              <div class="media-body">
                <h3 class="mt-0 text-black">House Renovation</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                <p><a href="#" class="btn btn-outline-primary btn-sm">Learn More</a></p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 col-12 mb-3 element-animate">
            <div class="media d-block media-feature text-center">
              <span class="flaticon-building-1 icon"></span>
              <div class="media-body">
                <h3 class="mt-0 text-black">Construction Consultant</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                <p><a href="#" class="btn btn-outline-primary btn-sm">Learn More</a></p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 col-12 mb-3 element-animate">
            <div class="media d-block media-feature text-center">
              <span class="flaticon-crane icon"></span>
              <div class="media-body">
                <h3 class="mt-0 text-black">General Contracting</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                <p><a href="#" class="btn btn-outline-primary btn-sm">Learn More</a></p>
              </div>
            </div>
          </div>
          
        </div>
        <!-- END row -->
        <div class="row justify-content-center element-animate"> 
          <div class="col-md-4"><p><a href="services.html" class="btn btn-primary btn-block">View All Services</a></p></div>
        </div>
      </div>
    </section>
		
		
	<footer class="site-footer">
		<div class="container">
			<div class="pull-right hidden-xs">
				<b>Malazima is a company of it's affiliates in Turkey, US and Brazil</b> 
			</div>
			<strong>Copyright &copy; 2018 <a href="http://www.nauticana.com"> Nauticana Cloud Applications</a>.</strong> All rights reserved.
		</div>
	</footer>

</body>

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
	
	doAjaxGet('requestForProposal/allPublished');

</script>

</html>
