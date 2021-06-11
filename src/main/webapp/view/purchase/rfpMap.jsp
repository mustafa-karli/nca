<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="sehir"></div>

<div id="map"></div>

<c:forEach var="item" items="${records}" varStatus="status">
${item[0]} ${item[1]} ${item[2]} ${item[3]} <br>
</c:forEach>

<script type="text/javascript">
	$(function(){
		$("#map svg path").hover(
		  function() {
			var id=$(this).attr("id");
			$("#sehir").text(id);
		 });
		$("#map svg path").click(
				  function() {
					var id=$(this).attr("id");
					$("#sehir").text(id + " clicked");
				 });
	})
</script>

