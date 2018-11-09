<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="message"/>
<html lang="${language}">
<head>
    <title><fmt:message key="title.publications"/></title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>
<c:if test="${not empty sessionScope.publications}">
    <table border="1">
        <tbody>
        <tr>
            <th><fmt:message key="publication.id"/></th>
            <th><fmt:message key="publication.doi"/></th>
            <th><fmt:message key="publication.name"/></th>
            <th><fmt:message key="publication.author"/></th>
            <th><fmt:message key="publication.key_words"/></th>
        </tr>
        <c:forEach items="${sessionScope.publications}" var="pub">
            <c:url var="getPublicationByID" value="">
                <c:param name="command" value="Page"/>
                <c:param name="id" value="${pub.id}"/>
            </c:url>
            <tr>
                <td>
                    <a href="${getPublicationByID}">
                        <c:out value="${pub.id}">-</c:out>
                    </a>
                </td>
                <td><c:out value="${pub.DOI}">-</c:out></td>
                <td><c:out value="${pub.name}">-</c:out></td>
                <td><c:out value="${pub.author}">-</c:out></td>
                <td><c:out value="${pub.keyWords}">-</c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty sessionScope.publications}"><h3><fmt:message key="publication.no_found"/></h3></c:if>
<br>
<form action="" method="get">
    <input type="hidden" name="command" value="Sort"/>
    <input type="submit"  value="<fmt:message key="button.sort"/>"/>
</form>
<br>
<form action="" method="get">
    <input type="hidden" name="command" value="Filter"/>
    <label><fmt:message key="publication.doi"/>:
        <input type="text" name="doi"/>
    </label>
    <label><fmt:message key="publication.key_words"/>:
        <input type="text" name="key_words"/>
    </label>
    <p><input type="submit" value="<fmt:message key="button.filter"/>"/>
        <input type="reset" value="<fmt:message key="button.reset"/>"/></p>
</form>
</body>
</html>