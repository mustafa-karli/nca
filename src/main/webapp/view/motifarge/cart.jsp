<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				${PAGE_TITLE}
			</div>
			<div class="panel-body">
				<div class="row">
					<c:forEach var="product" items="${products}" varStatus="pCount">
						<div class="col-md-4 col-sm-6">
							<span class="sc-product-item thumbnail">
							<img src="../../flag-icon/pasta1.jpg"/>
							<!--	<img data-name="product_image" src""> src="${product.imageUrl}" --> 
								<div class="caption">
									<h4 data-name="product_name"> ${product.caption} </h4>
									<p data-name="product_desc"> ${product.description} </p>
									<hr class="line">
									<div>
										<c:forEach var="attr" items="${product.materialAttributes}" varStatus="aCount">
										<div class="form-group">
											<label> ${attr.materialAttributeGroup.caption} : </label> ${attr.value}
										</div>
										</c:forEach>
<!--
										<div class="checkbox">
											<label>
												<input type="checkbox" name="gift_wrap" value="1"> Gift wrap
											</label>
										</div>
 -->
										<strong class="price pull-left"> ${product.price} </strong>
										<input name="product_price" value="${product.price}" type="hidden" />
										<input name="product_id" value="${product.id}" type="hidden" />
										<button class="sc-add-to-cart btn btn-success btn-sm pull-right">Add to cart</button>
									</div>
									<div class="clearfix"></div>
								</div>
							</span>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<aside class="col-md-4">
		<form method="POST" name=f id=f> 
			<div id="smartcart"></div>
		</form>
	</aside>
</div>

<script src="j/jquery.smartCart.js" type="text/javascript"> </script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#smartcart').smartCart();
	});
</script>
