<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
function captureData() {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        csv.push(row.join(","));        
    }
    document.f.data.value=csv.join("\n");
}


$('.table tr:nth-child(3) th:first').text($('.table tr:nth-child(3) th:first').text()+"(FORMEN)").css('color', 'red');	

$('.editable-text').bind({
    keyup:function(){ 
    	  $(".table tr:not(:eq(1), last) td:last-child").css( "color","blue" ).text(function () {
              var totalVal = 0;
              $(this).parent().find("td.editable-text").each(function () {
                  totalVal += parseInt($(this).text()) || 0;
                  //totalVal += parseInt( );
              });
              return totalVal;
          });

    	  $(".table tr:last td").css( "color","blue" ).text(function (i) {
            var totalVal = 0;
            $(this).parent().prevAll().find("td.editable-text:nth-child(" + (i+2) + ")").each(function () {
                totalVal += parseInt($(this).text()) || 0;
                $(".table tr:last th:first").text('Total');
                $(".table tr:first th:last").text('Total');
            });
            return totalVal;

        });
        var count=0
		for(i=2;i<$('tr').length;i++){
			var trs=parseInt($('tr:eq('+i+')').find('td:last').text());
			count+=trs;
		}
		$(".table tr:last td:last").text(count).css({color: "red" } )
       
      }


});

</script>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">

	<p>${teamCaption}</p>
	<p>${date}</p>

	<table  class="table table-bordered table-hover table-editable">
		<c:forEach var="row" items="${records}" varStatus="rowStatus">
		<c:if test="${rowStatus.index > 0}">
		<tr>
		  <c:forEach var="col" items="${row}" varStatus="colStatus">
			<c:choose>
				<c:when test="${rowStatus.index > 0 && rowStatus.index < 3 && colStatus.index > 0}">
					<th> ${col} </th>
				</c:when>
				<c:when test="${rowStatus.index > 2 && colStatus.index == 1}">
					<th> ${col} </th>
				</c:when>
				<c:when test="${rowStatus.index > 2 && colStatus.index > 1}">
					<td contenteditable="true" class="editable-text"> ${col} </td>
				</c:when>
			</c:choose>
          </c:forEach>             
			<c:choose>
				<c:when test="${rowStatus.index < 2}">
					<th> ___-___-___</th>
				</c:when>
				<c:otherwise>
					<td> &nbsp; </td>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${rowStatus.index < 2}">
					<th rowspan=2>${DESCRIPTION}</th>
				</c:when>
				<c:when test="${rowStatus.index > 2}">
					<td> &nbsp; </td>
				</c:when>
			</c:choose>
			
        </tr>
        </c:if>
        </c:forEach>  
    </table>
	</div>

	<div class="box-footer">
		<form name="f" id="f" method="post">
			<input type=hidden name=projectId value="${projectId}">
			<input type=hidden name=teamId value="${teamId}">
			<input type=hidden name=date value="${date}">
			<input type=hidden name=cats value="${cats}">
			<input type=hidden name=pers value="${pers}">
			<input type=hidden name=data value="">
			<a href="#" class="btn btn-primary" onClick="captureData();doAjaxPost('projectWbsManhour/editTeam');"> ${SAVE} </a>
			<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${CANCEL} </a> 
		</form>
	</div>
</div>