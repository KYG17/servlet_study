<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Cookie c = new Cookie("visit_count" , "count");
    	Cookie[] cookies = request.getCookies();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방문 횟수</title>
</head>
<body>
	<h1>페이지 전환</h1>
	<%! int count = 0; %>
	
	<%if(cookies != null){
		for(Cookie x : cookies){
			if("visit_count".equals(c.getName())){
				count++;
			}
		}
	} %>
	
	<p>당신은 이 페이지를
	<strong>방문한 횟수는 <%= count %> 입니다!!</strong>
	</p>
	
</body>
</html>