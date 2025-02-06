<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 대여</title>
</head>
<body>
	<h3>도서 대출</h3>
	<form action="/borrow" method="get">
	<fieldset>
	<legend>고객 정보</legend>
	<label for="userName">고객명 :</label>
	<input type="text" id="userName" name="userName">
	<br>
	<label for="userPhone">전화번호 :</label>
	<input type="text" id="userPhone" name="userPhone">
	<br>
	<label for="userEmail">Email :</label>
	<input type="email" id="userEmail" name="userEmail">
	</fieldset>
	
	<fieldset>
	<legend>도서 선택</legend>
	<label for="select">도서 :</label>
	<select name="select" id="select">
		<option value="1">자바 프로그래밍 입문</option>
		<option value="2">웹 개발의 기초</option>
		<option value="3">데이터베이스 시스템</option>
	</select>
	</fieldset>
	
	<fieldset>
	<legend>대출 기간</legend>
	<label>대출 기간(일) : </label>
	<input type="number" id="days" name="days">
	</fieldset>
	
	<button>대출하기</button>
	</form>
</body>
</html>