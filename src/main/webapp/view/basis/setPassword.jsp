<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">  
<link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="../dist/css/skins/skin-blue.min.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">

<div class="login-logo">
    <b> ${APPLICATION_TITLE} </b>
</div>

<div class="login-box-body">
    <p class="login-box-msg">${PAGE_TITLE}</p>

	<form id="f" name="f" method="post">
	<table>
		<tr>
			<th> <label class="col-sm-2 control-label" for="userAccountName"> ${USERNAME} </label> </th>
			<td> ${userAccountName} <input type="hidden" name="userAccountName" id="userAccountName" value="${userAccountName}"> </td>
		</tr>
		<tr>
			<th> <label  class="col-sm-2 control-label" for="passText">${PASSWORD}</label> </th>
			<td> <input type=password id="passText" name="passText"  class="form-control"> </td>
		</tr>
		<tr>
			<th> <label  class="col-sm-2 control-label" for="repeatPassword">${PASSWORD}</label> </th>
			<td> <input type=password id="repeatPassword" name="repeatPassword" class="form-control"> </td>
		</tr>
		<tr>
			<th> &nbsp; </th>
			<td>
				<a href="#" onclick="if (document.f.passText.value == document.f.repeatPassword.value) {document.f.submit();} else {alert('passwords not equal');}" class="btn btn-primary">${SAVE}</a>
				<a href="${prevpage}" class="btn btn-warning">${CANCEL}</a>
			</td>
		</tr>
	</table>
	</form>
  </div>
</div>

</body>
</html>
