<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.hide{
  display:none;
    }
.someclass{
color: red;
}

</style>
<script type="text/javascript">
function findSelected(cname) {
	var checkBoxes = document.getElementsByName(cname);
	var x = "";
	for (var i in checkBoxes) {
		if (checkBoxes[i].checked)
			x = x + "," + checkBoxes[i].value;
	}
	document.f.WBS_IDS.value = x;
}
</script>

<script type="text/javascript">
$(document).ready(function() {

$('input[type="text"]').keyup(function(){

var filter = jQuery(this).val();
  $(".menu ul > li").removeClass("hide");
  $(".menu ul > li").removeClass("show");
  
  $(".menu ul > li").each(function () {
     if ($(this).text().search(new RegExp(filter, "i")) < 0 && !$(this).hasClass('show')) {
	  	$(this).addClass('hide');
//	    $(this).removeAttr("style");
//	    $(this).find('input:checkbox[name="Arrow"]').removeAttr("checked");
     } else {
	  	$(this).addClass('show');
//	    $(this).attr("style", "color:red");      
	    $(this).find('input:checkbox[name="Arrow"]').attr("checked","checked");
     }
   });       
});

});
</script>
<!-- 
//<script type="text/javascript">
//$(function() {
//	  $("input[type='checkbox'][name='WBS']").change(function () {

			if($(this).attr("checked")) 
					$(this).removeAttr("checked")
			else 	$(this).attr("checked","checked");

alert($(this).siblings('ul').find("input[type='checkbox'][name='WBS']").attr("value"));
			$(this).siblings('ul')
	           .find("input[type='checkbox'][name='WBS']")
	           .prop('checked', this.checked);
			
	  });
	});


</script>
 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<input type=button class="btn btn-primary" id=sa1 value="${SELECT_ALL}" onClick="selectAll('WBS')" />
		<input type=button class="btn btn-primary" id=sa2 value="${DE_SELECT}" onClick="deSelectAll('WBS')" />
		<input type=button class="btn btn-primary" id=sa3 value="${TOGGLE_SELECT}" onClick="toggleSelect('WBS')" />
		
	</div>
	<form name="f" id="f">
		${CAPTION_FILTER}
		<input type="text" />
		${PROJECT_FILTER}
		<select name=projectFilter id=projectFilter onChange="doAjaxGet('projectWbs/select?projectId=' + document.f.projectId.value + '&projectFilter=' + document.f.projectFilter.value);">
			    <option value="-1"> - </option>
			    <c:forEach var="project" items="${projects}" varStatus="status">
					<c:choose>
						<c:when test="${projectFilter == project.id}">
							<option value="${project.id}" selected> ${project.caption} </option>
						</c:when>
						<c:otherwise>
							<option value="${project.id}"> ${project.caption} </option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>

		
		<div class="box-body menu">
	
		${wbshtml}	
	
		</div>
	
		<div class="box-footer">
			
				<input type=hidden name="prevpage" id="prevpage" value="doAjaxGet('projectWbs/select?id=${projectId}');">
				<input type=hidden name="projectId" id="projectId" value="${projectId}">
				<input type=hidden name=WBS_IDS id="WBS_IDS" value="">
				<a href="#" onClick="findSelected('WBS');doAjaxPost('projectWbs/select');" class="btn btn-primary">${OK}</a>
				<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
			
		</div>
	</form>
</div>