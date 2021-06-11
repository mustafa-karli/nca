<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/AdminLTE.min.css">
<link rel="stylesheet" type="text/css" href="../css/skins/_all-skins.min.css">
<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.6.0/css/all.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs-3.3.7/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-flash-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.css"/>

<script type="text/javascript" src="https://unpkg.com/popper.js@1.14.6/dist/umd/popper.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs-3.3.7/jq-3.3.1/jszip-2.5.0/dt-1.10.18/af-2.3.2/b-1.5.4/b-colvis-1.5.4/b-flash-1.5.4/b-html5-1.5.4/b-print-1.5.4/cr-1.5.0/fc-3.2.5/fh-3.1.4/kt-2.5.0/r-2.2.2/rg-1.1.0/rr-1.2.4/sc-1.5.0/sl-1.2.6/datatables.min.js"></script>

</head>

<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <b> ${APPLICATION_TITLE} </b>
  </div>

  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>
    <p> <b> ${message}</b> </p>

    <form:form id="loginForm" method="post" modelAttribute="loginBean" >
      <div class="form-group has-feedback">
        <form:input type="username" placeholder="Username or email" id="username" name="username" path="username" class="form-control"/>
       <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <form:input id="password" name="password" path="password" type="password" class="form-control" placeholder="Password" />
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <form:select id="language" name="language" path="language" items="${LANGUAGE}" type="language" class="form-control" placeholder="Language" />
      </div>
      <div class="row">
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat" value="Logon">Sign In</button>
        </div>
      </div>
    </form:form>
  </div>
</div>

</body>
</html>
