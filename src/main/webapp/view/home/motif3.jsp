<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<!DOCTYPE HTML>
<html ${LANGUAGE_DIRECTION}>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

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
<link type="text/css" rel="stylesheet" href="plugins/datatables/extensions/Responsive/css/dataTables.responsive.css" />

<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/price-range.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">
</head>

<body>
	<header id="header">
		<div class="header_top">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="#"><i class="fa fa-phone"></i> +90 312 123 45 67</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i> info@motifarge.com.tr</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-top-->

		<div class="header-middle">
			<div class="container">
				<div class="row">
					<div class="col-sm-1">
						<div class="logo pull-left">
							<a href="."><img src="images/home/logo.png" alt="" /></a>
						</div>
					</div>

					<div class="col-sm-1">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search" />
						</div>
					</div>

					<div class="col-sm-10">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-user"></i> Account</a></li>
								<li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li>
								<li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>
								<li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>
								<li><a href="login.html"><i class="fa fa-lock"></i> Login</a></li>
								<c:forEach var="lang" items="${languageList}" varStatus="status">
									<li><i class="flag-icon flag-icon-${lang.key}" onClick="document.location='?langcode=${lang.key}';"></i></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-middle-->

		<div class="header-bottom">
			<!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="index.html" class="active">Home</a></li>
								<c:if test="${!empty usermenu}">
									<c:forEach var="m" items="${usermenu}" varStatus="status">
										<li class="dropdown"><a href="#"> <i class="fa ${m.icon}">${m.caption}</i> <i class="fa fa-angle-down"> </i>
										</a>
											<ul role="menu" class="sub-menu">
												<c:forEach var="page" items="${m.captions}" varStatus="ps">
													<li><a href="#" onClick="doAjaxGet('${m.urls[ps.index]}');"> <i class="fa ${m.icons[ps.index]}"> ${page} </i></a></li>
												</c:forEach>
											</ul></li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-bottom-->

	</header>
	<!--/header-->


	<section>
		<div class="container" id="content">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<c:if test="${!empty productGroups}">
							<h2>Category</h2>
							<div class="panel-group category-products" id="accordian">
								<ncaTags:motifProductGroup productGroups="${productGroups}" language="${language}" />
							</div>
						</c:if>

						<c:if test="${!empty manufacturer}">
						<div class="brands_products">
							<h2>${language.getText("MANUFACTURER")}</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
									<c:forEach var="mgrc" items="${manufacturers}" varStatus="mIndex">
										<li><a href="#"> <span class="pull-right">(${mfrc.itemCount})</span>${mfrc.caption}</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
						</c:if>

						<div class="price-range">
							<h2>${language.getText("PRICE")}</h2>
							<div class="well text-center">
								<input type="text" class="span2" value="" data-slider-min="0" data-slider-max="1000" data-slider-step="5" data-slider-value="[250,450]" id="price"><br /> <b class="pull-left">0</b> <b class="pull-right">1000</b>
							</div>
						</div>

						<c:if test="${!empty materialAttributeGroups}">
							<c:forEach var="attr" items="${materialAttributeGroups}" varStatus="aIndex">
								<div class="brands_products">
									<h2>${attr.caption}</h2>
									<div class="brands-name">
										<ul class="nav nav-pills nav-stacked">
											<c:forEach var="val" items="attr.materialAttributeOptions" varStatus="vIndex">
												<c:choose>
													<c:when test="${empty val.high}">
														<li><a href="#"> <span class="pull-right">(${val.itemCount})</span>${val.low}</a></li>
													</c:when>
													<c:otherwise>
														<c:set var="r1" value="${val.low+(val.high-val.low)/2}"></c:set>
														<c:set var="r2" value="${val.high-(val.high-val.low)/2}"></c:set>
														<input type="text" class="span2" value="" data-slider-min="${val.low}" data-slider-max="${val.high}" data-slider-step="5" data-slider-value="[${r1},${r2}]" id="${val.id}">
														<br />
														<b class="pull-left">${val.low}</b>
														<b class="pull-right">${val.high}</b>

													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</div>
								</div>
							</c:forEach>
						</c:if>

						<div class="shipping text-center">
							<img src="images/home/shipping.jpg" alt="" />
						</div>

					</div>
				</div>


				<div class="col-sm-9 padding-right">
					<div class="features_items">
						<!--features_items-->
						<h2 class="title text-center">Features Items</h2>
						<div class="col-sm-4">

							<c:forEach var="product" items="${products}" varStatus="pIndex">
								<div class="product-image-wrapper">
									<div class="single-products">
										<div class="productinfo text-center">
											<img src="contentRelation/data/MT/${product.id}" alt="" />
											<h2>${product.price}${product.currentcy}</h2>
											<p>${product.caption}</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>${language.getText("ADD_TO_CART")}</a>
										</div>
									</div>
									<div class="choose">
										<ul class="nav nav-pills nav-justified">
											<li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
											<li><a href="#"><i class="fa fa-plus-square"></i>Add to compare</a></li>
										</ul>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<footer id="footer">
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="companyinfo">
							<h2>MOTIF AR-GE</h2>
							<p>Information about company, any text welcome here.</p>
						</div>
					</div>
					<div class="col-sm-7">
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe1.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe2.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe3.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe4.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="address">
							<img src="images/home/map.png" alt="" />
							<p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="footer-widget">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Service</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Online Help</a></li>
								<li><a href="#">Contact Us</a></li>
								<li><a href="#">Order Status</a></li>
								<li><a href="#">Change Location</a></li>
								<li><a href="#">FAQâ€™s</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Quock Shop</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">T-Shirt</a></li>
								<li><a href="#">Mens</a></li>
								<li><a href="#">Womens</a></li>
								<li><a href="#">Gift Cards</a></li>
								<li><a href="#">Shoes</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Policies</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Terms of Use</a></li>
								<li><a href="#">Privecy Policy</a></li>
								<li><a href="#">Refund Policy</a></li>
								<li><a href="#">Billing System</a></li>
								<li><a href="#">Ticket System</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>About MOTIF</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Company Information</a></li>
								<li><a href="#">Careers</a></li>
								<li><a href="#">Store Location</a></li>
								<li><a href="#">Affillate Program</a></li>
								<li><a href="#">Copyright</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3 col-sm-offset-1">
						<div class="single-widget">
							<h2>Acknowledge</h2>
							<form action="#" class="searchform">
								<input type="text" placeholder="Your email address" />
								<button type="submit" class="btn btn-default">
									<i class="fa fa-arrow-circle-o-right"></i>
								</button>
								<p>
									Get the most recent updates from <br />our site and be updated your self...
								</p>
							</form>
						</div>
					</div>

				</div>
			</div>
		</div>

		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright Â© 2018 MOTIF Ar-Ger. All rights reserved.</p>
					<p class="pull-right">
						Designed by <span><a target="_blank" href="http://www.nauticana.com">Nauticana</a></span>
					</p>
				</div>
			</div>
		</div>

	</footer>



	<script src="j/jquery.scrollUp.min.js"></script>
	<script src="j/price-range.js"></script>
	<script src="j/jquery.prettyPhoto.js"></script>
	<script src="j/main.js"></script>
	<script src="jquery/jquery-2.2.3.min.js"></script>
	<script src="j/jquery.inputmask.bundle.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="plugins/datatables/extensions/Responsive/js/dataTables.responsive.min.js"></script>
	<script src="plugins/select2/select2.full.min.js"></script>
	<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="dist/js/app.min.js"></script>
	<script src="j/dataTables.buttons.min.multiheaders.js"></script>
	<script src="j/jszip.min.js"></script>
	<script src="j/pdfmake.min.js"></script>
	<script src="j/vfs_fonts.js"></script>
	<script src="j/buttons.html5.min.multiheaders.js"></script>
	<script src="j/buttons.print.min.multiheaders.js"></script>
	<script src="j/nauticana.js"></script>

</body>

</html>