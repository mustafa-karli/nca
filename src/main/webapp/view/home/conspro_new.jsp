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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/v/bs4-4.1.1/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link rel="stylesheet" href="2018/css/nauticana.css" />
<link rel="stylesheet" href="2018/css/nauticana.table.css" />
 
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/v/bs4-4.1.1/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script src="2018/js/nauticana.js"></script>





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

<body class="hold-transition skin-blue layout-top-nav">

<div class="wrapper" style="height: auto;">
	<header id="header" class="main-header">
		<nav class="navbar navbar-static-top" role="navigation">
			<div class="container">
        		<div class="navbar-header">
        		  <a href="/" class="navbar-brand"><b>Cons</b> Pro </a>
        		  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse"><i class="fa fa-bars"></i></button>
        		</div>

				<div class="collapse navbar-collapse pull-left" id="navbar-collapse">

					<input type="hidden" id="username" name="username" value="${userCaption}">

					<ul class="nav navbar-nav mr-auto">

						<c:if test="${!empty purchaseGroups}">
						<li class="dropdown">
							<a data-toggle="dropdown" href="#"> Products <span class="caret"></span> </a>
							<ul class="dropdown-menu">
								<ncaTags:consproGroup purchaseGroups="${purchaseGroups}" language="${language}" />
							</ul>
						</li>
						</c:if>

						<c:if test="${!empty usermenu}">
						<li class="dropdown">
							<a data-toggle="dropdown" href="#"> Menu <span class="caret"></span> </a>
							<ul class="dropdown-menu">
								<c:forEach var="m" items="${usermenu}" varStatus="status">
									<li class="dropdown-submenu"><a href="#"> <i class="fa ${m.icon}"> ${m.caption} </i> </a>
										<ul class="dropdown-menu">
											<c:forEach var="page" items="${m.captions}" varStatus="ps">
												<li><a href="#" onClick="doAjaxGet('${m.urls[ps.index]}');"> <i class="fa ${m.icons[ps.index]}"> ${page} </i>
												</a></li>
											</c:forEach>
										</ul>
									</li>
								</c:forEach>
							</ul>
						</li>
						</c:if>
					</ul>
				</div>
				
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<c:if test="${!empty notifications}">
						<li class="dropdown notifications-menu">
							<a href="#" data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span id="notifCount" class="notifCount label label-warning">${notifications.size()}</span></a>
							<ul class="dropdown-menu">
								<li class="header"><span class="notifCount">${notifications.size()}</span> tane bildiriminiz var.</li>
								<li>
									<ul class="menu">
										<c:forEach var="notify" items="${notifications}" varStatus="nCount">
											<li><span style="cursor: pointer;"><i class="fa fa-check complete pull-right" data-id="${notify.id}"> </i></span><a href="#" onClick="doAjaxGet('${statics.get(notify.notificationType.tablename).rootMapping}/show?id=${notify.objectId}')"> <i class="fa fa-search"> ${notify.description} - <fmt:formatDate value="${notify.dueDate}" pattern="dd-MM-yyyy hh:mm" /></i></a></li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</li>
						</c:if>
						
						<li class="dropdown">
							<a href="#" data-toggle="dropdown"> <i class="${langClass}"></i></a>
							<ul class="dropdown-menu">
								<li class="in-line">
								<c:forEach var="lang" items="${languageList}" varStatus="status">
									<a href="#" onClick="document.location='?langcode=${lang.key}';"><i class="${lang.value}"></i></a>
								</c:forEach>
								</li>
							</ul>
						</li>
						
						<li>
							<a onClick="document.location='userAccount/login';"> <i class="fa fa-user-circle"></i></a>
						</li>

						<c:if test="${!empty userCaption}">
						<li class="dropdown">
							<a href="#" data-toggle="dropdown"> ${userCaption} <span class="caret"></span> </a>
							<ul class="dropdown-menu">
								<li><a href="#" onClick="document.location='userAccount/setPassword';"><i class="fa fa-key"> Set password </i></a></li>
								<li><a href="#" onClick="document.location='userAccount/logoff';"><i class="fa fa-power-off"> Log out</i></a></li>
							</ul>
						</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<div class="content-wrapper">
		<div class="container">
			<section class="content-header">
			</section>
			<section class="content">
				<div id="content"></div>
			</section>
		</div>
	</div>

	<footer class="main-footer">
		<div class="container">
			<div class="pull-right hidden-xs">
				<b>conspro is a company of it's affiliates in Turkey, US and Brazil</b> 
			</div>
			<strong>Copyright &copy; 2018 <a href="http://www.nauticana.com"> Nauticana Cloud Applications</a>.</strong> All rights reserved.
		</div>
	</footer>

</div>
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
