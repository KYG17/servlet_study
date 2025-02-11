<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Cookie c = new Cookie("visit_count" , "1");
c.setMaxAge(60*60*24);
response.addCookie(c);
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
	
	<%
		if(cookies != null){
			for(Cookie x : cookies){
				if("visit_count".equals(x.getName())){
					
					//쿠키의 value는 무조건 String 그래서 Interger.parseInt로 형변환
					  count = Integer.parseInt(x.getValue())+1;
				}
			}
		}
		//내가 한 것 처럼 count에 Integer.parseInt(x.getValue())+1 이렇게 해도 되지만
		//count = Integer.parseInt(x.getValue())이렇게 재할당하고
		//for문 밖에다가 count++하면 하나씩 증가
	
		//쿠키 생성 및 갱신 -> 수정
		//value는 String이기 때문에 다시 string으로 형변환
		Cookie newCookie = new Cookie("visit_count",String.valueOf(count));
		newCookie.setMaxAge(60*60*24);
		response.addCookie(newCookie);
    %>
  
				

	
	<p>당신은 이 페이지를
	<strong>방문한 횟수는 <%= count %> 입니다!!</strong>
	</p>
	
</body>
</html>