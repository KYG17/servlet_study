<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String msg = (String)request.getAttribute("msg");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 확인</title>
</head>
<body>
	<h3>필터 연습 중</h3>
	<%= msg %>
</body>
</html>