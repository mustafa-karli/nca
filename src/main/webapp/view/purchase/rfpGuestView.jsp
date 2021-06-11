<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>

<fmt:setLocale value="${language.localeStr}" scope="session" />

<ncaTags:rfpGuestView records="${records}" vendors="${vendors}" client="${client}" updateAllowed="${UPDATE_ALLOWED}" language="${language}"/>

<script src="js/nauticana.style.js"></script>
