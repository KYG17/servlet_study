<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<!-- Member m 을 가져와야 하기 때문에 import 필수! -->
    <%@ page import = "com.gn.member.vo.Member"%>
    <!--  세션 가져오기! Member로 다운케스팅 -->
    <% Member m = (Member)session.getAttribute("member"); %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<%= request.getContextPath()%>/resources/css/include/nav.css" rel="stylesheet" type="text/css">
<%-- <nav>
	<div id="nav_wrap">
		<div class="menu">
			<ul>
			<!--  게시판은 로그인/비로그인 한 사람도 볼 수 있으므로 조건문 밖으로 빼놓음 -->
				<li>
					<a href="/boardList">게시판</a>
				</li>
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
</nav>	  --%>

<nav>
	<div id="nav_wrap">
		<div class="menu">
			<ul>
			<!--  게시판은 로그인/비로그인 한 사람도 볼 수 있으므로 조건문 밖으로 빼놓음 -->
				<li>
					<a href="/boardList">게시판</a>
				</li>
					<c:choose>
						<c:when test="${empty member }">
								<li>
					<a href="/memberLogin">로그인</a>
				</li>
				<!--  1. MemberLoginServlet
					2./views/member/login.jsp
					3. css = resources/css/member/login.css -->
				<li>
					<a href="/memberCreate">회원가입</a>
				</li>
						</c:when>
						<c:otherwise>
								<li>
					<a href="/boardCreate">게시글 등록</a>
				</li>
				<li>
					<a href="/memberLogout">로그아웃</a>			
				</li>
				<li>
					<a href="/memberUpdate">계정수정</a>
				</li>
						</c:otherwise>
					</c:choose>
			</ul>
		</div>
	</div>
</nav>	 