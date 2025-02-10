<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	Cookie [] cookies = request.getCookies();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키</title>
</head>
<body>

	<ul>
		<li><a href="/createCookieName">쿠키!!</a></li>
	
		<li>
			<% 
			String userId = "쿠키없음";
			if(cookies != null){
				for(Cookie c : cookies){
					if("user_id".equals(c.getName())){
						userId = c.getValue();
					}
				}
			} %>
			아이디 : <%= userId %>
		</li>
	
		
	</ul>

</body>
</html>