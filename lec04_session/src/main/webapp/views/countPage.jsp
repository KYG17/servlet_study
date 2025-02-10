<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방문 횟수</title>
</head>
<body>
	<h1>페이지 전환</h1>
	<%! int count = 0; %>
	
	<%
	Cookie c = new Cookie("visit_count" , "count");
	c.setMaxAge(60*60*24);
	response.addCookie(c);
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie x : cookies){
			if("visit_count".equals(x.getName())){
				count = Integer.parseInt(x.getValue());
				count++;	
			}
		}
	}	
		Cookie newCookie = new Cookie("visit_count", String.valueOf(count));
		newCookie.setMaxAge(60 * 60 * 24); 
    	response.addCookie(newCookie); 
    %>
				

	
	<p>당신은 이 페이지를
	<strong>방문한 횟수는 <%= count %> 입니다!!</strong>
	</p>
	
</body>
</html>