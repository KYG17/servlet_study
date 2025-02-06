<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Include 태그 연습</title>
</head>
<body>
	<h2>===start===</h2>
	<%@ include file = "scripting.jsp" %>
	
	
	<h2>===end===</h2>
	<% int multiple = sum * 3; %>
	<h1>계산 결과 : <%= multiple %></h1>
	
</body>
</html>