<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<style>
    .hide{
      display:none;
        }
    .someclass{
    color: red;
	}
  
    </style>

<script type="text/javascript">
$(document).ready(function() {

$("#captionFilter").keyup(function(){

var filter = jQuery(this).val();
  $(".menu ul > li").removeClass("hide");
  $(".menu ul > li").removeClass("show");
  
  $(".menu ul > li").each(function () {
     if ($(this).text().search(new RegExp(filter, "i")) < 0 && !$(this).hasClass('show')) {
	  	$(this).addClass('hide');
	    $(this).find('label').removeAttr("style");
//	    $(this).find('input:checkbox[name="Arrow"]').removeAttr("checked");
     } else {
	  	$(this).addClass('show');
	    $(this).find('input:checkbox[name="Arrow"]').attr("checked","checked");
	    $(this).find('label').attr("style", "color:red");   
     }
   });       
});

});
</script>

${DATATABLE1}

<div class="box box-primary menu">

	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
 		<a class="btn btn-primary" href="#" onClick="doAjaxGet('${newlink}');"> ${NEW} </a>
 		<input type="text" id="captionFilter" />
	</div>

	<div class="box-body" class="menu">
	<ul class="tv3">
	<ncaTags:treeCheck tree="${tree}" linkText="${linkText}" linkAddr="${linkAddr}" linkFunc=""/>
	</ul>
    </div>
</div>
