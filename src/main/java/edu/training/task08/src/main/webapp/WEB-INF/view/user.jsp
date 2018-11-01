<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="edu.tutorial.model.User" %>
<html>
<head>
    <title>User Record</title>
</head>
<body>
<%
    if (request.getAttribute("user") != null) {
        User user = (User) request.getAttribute("user");
%>
<div>ID: <%= user.getId()%></div>
<div>First Name: <%= user.getFirstName()%></div>
<div>Last Name: <%= user.getLastName()%></div>

<%
} else {
%>

<h1>No student record found.</h1>

<% } %>
</body>
</html>