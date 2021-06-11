<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">

	<div class="box-body">
		<!-- 
			<div class="form-group">
				<label class="col-sm-2 control-label" for="id"> ${PROJECT_ID} </label>
				<div class="col-sm-10">
					<label class="control-label">${id}</label>
					<form:input type="hidden" path="id"/>
				</div>
			</div>
 		-->
 		<form:input type="hidden" path="id"/>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${recordHeaders[1]}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control required" path="caption"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="status"> ${recordHeaders[2]} </label>
			<div class="col-sm-10">
				 ${record.status}
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="customer"> ${recordHeaders[3]} </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="customer"/>
			</div>
		</div>

			<div class="form-group">
				<label  class="col-sm-2 control-label required" for="country"> ${recordHeaders[4]} </label>
				<div class="col-sm-10"> 
					<form:select name="country" path="country"  class="form-control select2" items="${COUNTRY}"/>
				</div>
			</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="location"> ${recordHeaders[5]} </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="location"/>
			</div>
		</div>
<%-- 		<div class="form-group">
			<label  class="col-sm-2 control-label" for="contractDate"> ${recordHeaders[6]}  </label>
			<div class="col-sm-10"> 
				<form:input type="date" class="form-control" path="contractDate"/>
			</div>
		</div> --%>

		 
		 <div class="form-group">
                <label  class="col-sm-2 control-label" for="contractDate"> ${recordHeaders[6]}  </label>

                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <form:input type="text" class="form-control pull-right" path="contractDate"/>
                </div>
                <!-- /.input group -->
          </div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="areaHandover"> ${recordHeaders[7]}  </label>
			 <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input  type="text" class="form-control" path="areaHandover"/>
			</div>
		</div>
		<%-- 24.11.2017 tarihli kullanıcı testi sonucunda alanların kapatılması talep edildi -->
		<%-- <div class="form-group"> 
			<label  class="col-sm-2 control-label" for="duration"> ${recordHeaders[8]}  </label>
			<div class="col-sm-10"> 
				<form:input type="number" class="form-control" path="duration"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="revizedDuration"> ${recordHeaders[9]}  </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="revizedDuration"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="revizedCompletion"> ${REVIZED_COMPLETION}  </label>
			<div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input class="form-control" path="revizedCompletion"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="expectedCompletion"> ${EXPECTED_COMPLETION}  </label>
			<div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input class="form-control" path="expectedCompletion"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="endOfWarranty"> ${END_OF_WARRANTY}  </label>
			<div class="input-group date col-md-6">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input class="form-control" path="endOfWarranty"/>
			</div>
		</div> --%>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="contractedAmount"> ${recordHeaders[14]}  </label>
			<div class="col-sm-10"> 
				<form:input type="text" class="currency form-control" path="contractedAmount"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="contractExchange"> ${recordHeaders[15]}  </label>
			<div class="col-sm-10"> 
				<form:select name="contractExchange" path="contractExchange"  class="form-control" items="${EXCHANGE}"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="expectedCost"> ${recordHeaders[16]}  </label>
			<div class="col-sm-10"> 
				<form:input  class="currency form-control" path="expectedCost"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="advancePercent"> ${recordHeaders[17]} % </label>
			<div class="col-sm-10"> 
				<form:input class="form-control percentage" path="advancePercent"/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label  class="col-sm-2 control-label" for="letterOfAdvance"> ${recordHeaders[18]} % </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="letterOfAdvance"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="letterOfWarranty"> ${recordHeaders[19]}  </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="letterOfWarranty"/>
			</div>
		</div> --%>
		
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="organizationId">${recordHeaders[20]}</label>
			<div class="col-sm-10">
				<form:select path="organizationId" class="form-control select2" items="${ORGANIZATION}" />
			</div>
		</div>
		
		
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>

</div>

<script>
   $('document').ready(function() {

	//mask money
//	    $('.currency').maskMoney({precision:0, thousands:' '}).maskMoney( "mask" );

	//Initialize Select2 Elements
	    $(".select2").select2();

	//Date picker
	    $('.date').datepicker({
	      autoclose: true,
	      format: 'dd/mm/yyyy'
	    });
	    
	});
</script>

<script>
	console.log(navigator.userAgent);
	$(".currency").inputmask( {
		alias: 'decimal',		
		groupSeparator: ' ',
	    autoGroup: true, 
		digits: 2, 
		digitsOptional: true,
		rightAlign: false
	});
	$(".percentage").inputmask( {
		alias: 'numeric',		
	    autoGroup: false, 
	    integerDigits:3,
		digits: 2, 
		min: 0,
		max:100,
		digitsOptional: true,
		rightAlign: false
	});
</script>

<script>
	$(document).ready(function(){
		$('.required').attr({'placeholder':'${REQUIRED}'});	
	});

</script>
