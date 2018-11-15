<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="message" />
<html>
<head>
    <title><fmt:message key="title.error" /></title>
</head>
<h2><fmt:message key="${requestScope.message}" /></h2>
<body>
<a href="publications"><fmt:message key="home.href.show_publications" /></a>
</body>
</html>
