<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
     String name = (String)request.getAttribute("user_name");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과</title>
</head>
<body>
<h1>결과</h1>
<h3><%= name %></h3>

</body>
</html>