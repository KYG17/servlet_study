<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이거는 연습</title>
</head>
<body>
	<h1>용규 할 수 있다</h1>
	<ul>
		<li><a href="views/filter.html">필터연습</a></li>
	</ul>
	
		<form action="/receive/msg" method="post">
		<fieldset>
			<legend>메시지 작성</legend>
			<textarea cols="20" rows="3" name="msg"></textarea>
			<input type="submit" value="보내기">
		</fieldset>
	</form>
	
</body>
</html>