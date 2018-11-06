<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="edu.training.model.entities.Publication" %>
<html>
<head>
    <title>Publication</title>
</head>
<body>


<%
    if (request.getAttribute("publication") != null) {
        Publication publication = (Publication) request.getAttribute("publication");
%>

ID: <%= publication.getId()%><br>
DOI: <%= publication.getDOI()%><br>
Name: <%= publication.getName()%><br>
Author: <%= publication.getAuthor()%><br>
Key Words: <%= publication.getAuthor()%><br>
<a href="publications?id=<%= publication.getId()%>&references=true">SHOW REFERENCES</a>
<%
} else {
%>
<h1>No publication record found.</h1>
<% } %>
<a href="publications">GO BACK</a>
</body>
</html>