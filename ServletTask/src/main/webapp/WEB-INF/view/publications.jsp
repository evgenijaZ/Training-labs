<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="edu.training.model.entities.Publication" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Publications</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>AUTHOR</th>
    </tr>
    <%
        if (request.getAttribute("publications") != null) {
            List<Publication> publications = (List<Publication>) request.getAttribute("publications");
            for (Publication publication : publications) {
                long id = publication.getId();
    %>

    <tr>
        <td><a href="publications?id=<%=id%>"><%=id%>
        </td>
        <td><%= publication.getName()%>
        </td>
        <td><%= publication.getAuthor()%>
        </td>
    </tr>
    <% }%>
</table>
<%
} else {
%>
<h1>No publication record found.</h1>
<% } %>
</body>
</html>