<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<!-- Member m 을 가져와야 하기 때문에 import 필수! -->
    <%@ page import = "com.gn.member.vo.Member"%>
    <!--  세션 가져오기! Member로 다운케스팅 -->
    <% Member m = (Member)session.getAttribute("member"); %>
<link href="<%= request.getContextPath()%>/resources/css/include/nav.css" rel="stylesheet" type="text/css">
<nav>
	<div id="nav_wrap">
		<div class="menu">
			<ul>
			<% if(m == null) {%>
				<li>
					<a href="/memberLogin">로그인</a>
				</li>
				<!--  1. MemberLoginServlet
					2./views/member/login.jsp
					3. css = resources/css/member/login.css -->
				<li>
					<a href="/memberCreate">회원가입</a>
				</li>
				<li>
					<a href="/boardList">게시판</a>
				</li>
				<%} else{ %>
				<li>
					<a href="/boardCreate">게시글 등록</a>
				</li>
				<li>
					<a href="/memberLogout">로그아웃</a>			
				</li>
				<li>
					<a href="/memberUpdate">계정수정</a>
				</li>
				<%} %>
			</ul>
		</div>
	</div>
</nav>	 