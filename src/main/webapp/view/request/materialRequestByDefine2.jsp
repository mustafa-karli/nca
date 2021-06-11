<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="nonPrint">
<div id="printArea">
<c:forEach var="record" items="${records}" varStatus="rIndex">

<div class="box box-primary">

	<div class="box-body">
		<p> <b>${language.getText("ORGANIZATION")} :</b> ${record.organization.caption} </p>
		<p> <b>${language.getText("ORDER_ID")} : </b> ${record.id} </p>
		<p> <b>${language.getText("ORDER_DATE")} :</b> ${fn:substring(record.requestDate, 0, 10)} </p>
		<p> <b>${language.getText("DUE_DATE")}: </b>${fn:substring(record.dueDate, 0, 10)} </p>
		<p> <b>${language.getText("DESCRIPTION")} :</b> ${record.description} </p>

		<c:forEach var="line" items="${record.materialRequestItems}" varStatus="iIndex">

			<p><br>${language.getText("MATERIAL")} : ${line.material.caption}
			<c:forEach var="attr" items="${line.materialRequestItemAttrs}" varStatus="aIndex">
				<br> &nbsp; ${attr.materialAttributeGroup.caption} ${attr.value} 
			</c:forEach>
			</p>
			<p>${language.getText("QUANTITY")} : ${line.quantity} ${line.unit}</p>
		</c:forEach>
		<c:if test="${record.status == 'G'}"> <a href="contentRelation/firstData/MR/${record.id}" target="_blank"> <img src="contentRelation/firstThumb/MR/${record.id}"> </a> </c:if>
	</div>
</div>
</c:forEach> 
</div>
<div class="box-footer">
	<a href="#" class="btn btn-warning" onClick="doAjaxGet('materialRequestReport/list');"> ${language.getText("CLOSE")} </a>
	<a id="btnPrint" href="#" class="btn btn-info"> ${language.getText("PRINT")} </a>
</div>

</div>
<script>

$("#btnPrint").click(function () {
	var divToPrint = document.getElementById('printArea');
    var htmlToPrint = '' +
        '<style type="text/css">' +
	        '.box {border:1px solid #000;'+
	        ' border-radius: 5px;'+
	        ' padding: 25px;}'+
        '</style>';
    htmlToPrint += divToPrint.outerHTML;
    newWin = window.open("");
    newWin.document.write("<h3 align='center'>Özel Pasta Siparişleri</h3>");
    newWin.document.write(htmlToPrint);
    newWin.print();
    newWin.close();
});



</script>