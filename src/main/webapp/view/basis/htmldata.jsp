<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="box box-primary">
	<div class="box-body">
	
		<table id="dataTable1" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th> Name </th>
					<th> Data </th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="hhh" items="${r1}" varStatus="status">
			<tr>
    	        <td>${hhh}</td>
    	        <td>${r2[status.index]}</td>
			</tr>
			</c:forEach>         
			</tbody>
		</table>
	</div>
</div>
