<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<fmt:setLocale value="${language.localeStr}" scope="session" />

<ncaTags:cmtVendorView records="${myCommitments}" language="${language}"/>
<ncaTags:cmtSalesView records="${sales}" language="${language}"/>
<ncaTags:cmtGuestView records="${allCommitments}" language="${language}"/>

<script type="text/javascript" src="js/dataTables.style.js"></script>
