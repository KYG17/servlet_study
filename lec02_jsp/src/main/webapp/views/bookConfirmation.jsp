<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String name = (String)request.getAttribute("name");
    	String phone = (String)request.getAttribute("phone");
    	String email = (String)request.getAttribute("email");
    	String [] book = (String[])request.getAttribute("book");
    	String number = (String)request.getAttribute("number");
    	int totalprice = (int)request.getAttribute("totalprice");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내역 확인</title>
</head>
<body>
<h1>도서 대출 내역</h1>
<h3>[고객 정보]</h3>
<ul>
	<li><%= name %></li>
	<li><%= phone %></li>
	<li><%= email %></li>
</ul>
<br>
<h3>[대출 정보]</h3>
	<ul>
		<%
			switch(book[0]){
			case "1" : out.println("<li>"+"자바 프로그래밍 입문"+"</li>") ; break;
			case "2" : out.println("<li>"+"웹 개발의 기초"+"</li>"); break;
			case "3" : out.println("<li>"+"데이터베이스 시스템"+"</li>"); break;			
			}
			%>
			
			<li>대출 기간 :  <%=number %>  일 </li>
	</ul>
	
	<h3>대출 금액 : <%= totalprice %> </h3>
	
	<h1>도서를 즐겁게 읽으세요!!</h1>
	
	


</body>
</html>