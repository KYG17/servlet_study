<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	//값 가져오기
    	Cookie[] cookies = request.getCookies();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키랑 세션</title>
</head>
<body>
	
	<%if(session.isNew()||session.getAttribute("account") == null) {%>
		<a href="/login">로그인</a>
	<%} else{%>
		로그인한 사용자 정보 출력
	<%}  %>
	
	







	<h1>쿠키 연습하기</h1>
	<ul>
		<li>
			<a href="/createCookie">생성하기</a>
		</li>
		
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
		<li>
			<a href="/editCookie">수정하기</a>
		</li>
		
		<li>
			<a href="/removeCookie">삭제하기</a>
		</li>
	</ul>
	
	<h3><a href="/changePage">화면전환</a></h3>
	
	
	
	
	<h2>세션 연습하기</h2>
	<ol>
		<li> 
			<a href="/createSession">생성하기!</a>
		</li>
		<li>
			<%
				String memberId = "세션 없음";
				if(session != null){
					if(session.getAttribute("member_id") == null){
						memberId = "세션 없음";
					}else{
					//object로 들어가기 때문에 downcasting
					memberId = (String)session.getAttribute("member_id");
				}
			}
			%>
			세션명은 : <%= memberId %>
		</li>
	</ol>
	
	
	

</body>
</html>