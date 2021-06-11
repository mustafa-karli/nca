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

function control(){
	var error = 0;
    $(".table tr:not(:first, last)").each(function () {
	     var totalVal = 0;
	     var firstVal = 0;

		$(this).find("td.editable-text").each(function(index){
			if (index == 0){
				firstVal= parseInt($(this).text()) || 0;
			} else {
				totalVal += parseInt($(this).text()) || 0;
			};
		});
		if(firstVal != totalVal && totalVal > 0){
			$(this).css('background-color', 'red')
			error = 1;
		} else {
			$(this).css('background-color', 'white')
		}
	});
	if (error == 0 ) {
		doAjaxPost('projectWbsManhour/editTeamLead');
	} else {
		alert("Total ManHour does not match!");
	}
}

$('.editable-text').bind({
    keyup:function(){ 
            $(".table tr:last td").css( "color","blue" ).text(function (i) {
                var totalVal = 0;
                $(this).parent().prevAll().find("td.editable-text:nth-child(" + (i+4) + ")").each(function () {
                    totalVal += parseInt($(this).text()) || 0;
                    $(".table tr:last th:first").text('Total');
                });
                return totalVal;

            });
			
			var count=0
			for(i=1;i<$('tr').length;i++){
				var trs=parseInt($('tr:eq('+i+')').find('td:last').text())
				count+=trs
			}
    }
});


</script>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">

	<p>${workerCaption}</p>
	<p>${date}</p>
	
	<table class="table table-bordered table-hover table-editable">
	<c:forEach var="row" items="${records}" varStatus="rowStatus">
		<tr>
		<c:forEach var="col" items="${row}" varStatus="colStatus">
			<c:choose>
				<c:when test="${rowStatus.index < 1 || colStatus.index < 3}">
					<th> ${col} </th>
				</c:when>
				<c:otherwise>
					<td contenteditable="true" class="editable-text"> ${col} </td>
				</c:otherwise>
			</c:choose>
			
			
          </c:forEach> 
          <%-- <c:choose>
				<c:when test="${rowStatus.index < 1}">
					<th></th>
				</c:when>
				<c:otherwise>
					<td></td>
				</c:otherwise>
			</c:choose> --%>
        </tr>
      </c:forEach>     
      <tr>
      <th colspan=3></th>

      <td class="total"></td>
      <td class="total"></td>
      <td class="total"></td>
      <td class="total"></td>
<!--       <td class="total"></td> -->
      </tr>        
    </table>
	</div>

	<div class="box-footer">
		<form name="f" id="f" method="post">
			<input type=hidden name=projectId value="${projectId}">
			<input type=hidden name=teamId value="${teamId}">
			<input type=hidden name=workerId value="${workerId}">
			<input type=hidden name=date value="${date}">
			<input type=hidden name=data value="">
			<a href="#" class="btn btn-primary" onClick="captureData();control();"> ${language.getIconText("SAVE")} </a>
			<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${language.getIconText("CANCEL")} </a> 
		</form>
	</div>
</div>