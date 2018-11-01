<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="edu.tutorial.model.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>FIRST NAME</th>
        <th>SECOND NAME</th>
    </tr>
    <%
        if (request.getAttribute("users") != null) {
            List<User> users = (List<User>) request.getAttribute("users");
            for (User user : users) {
    %>
    <tr>
        <td><%= user.getId()%></td>
        <td><%= user.getFirstName()%></td>
        <td><%= user.getLastName()%></td>
    </tr>
    <% }%>
</table>
<%
} else {
%>
<h1>No student record found.</h1>
<% } %>
</body>
</html>