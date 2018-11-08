<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="message" />
<html lang="${language}">
<head>
    <title><fmt:message key="title.publication" /></title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>
<c:if test="${not empty requestScope.publication}">
    <b> <fmt:message key="publication.id" />:</b><c:out value="${publication.id}"></c:out><br>
    <b> <fmt:message key="publication.doi" />:</b><c:out value="${publication.DOI}"></c:out><br>
    <b> <fmt:message key="publication.name" />: </b><c:out value="${publication.name}"></c:out><br>
    <b> <fmt:message key="publication.author" />:</b><c:out value="${publication.author}"></c:out><br>
    <b> <fmt:message key="publication.key_words" />:</b><c:out value="${publication.keyWords}"></c:out><br>
    <c:url var="getReferences" value="publications">
        <c:param name="id" value="${publication.id}"/>
        <c:param name="references" value="true"/>
    </c:url>
    <a href="${getReferences}"><fmt:message key="publication.href.show_references" /></a>
</c:if>
<c:if test="${empty requestScope.publication}">
    <h3><fmt:message key="publication.no_found" /></h3>
</c:if>
<a href="publications"><fmt:message key="publication.href.go_back" /></a>
</body>
</html>