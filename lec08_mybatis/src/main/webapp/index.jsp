<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
				<!-- 두개 다 같은 방식 -->
		<%-- 	<c:url value="/boardDetail" var="url" />
				<c:param name="board_no" value="7" />
				<a href="${url }">상세조회ㅏ</a> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈화면</title>
</head>
<body>
	<ol>
		<li>
		<a href="/boardList">목록조회</a>	
		</li>
			
		<li>
			<a href="<c:url value='/boardDetail?board_no=7'/>">상세조회</a>
		</li>
		<li>
			<c:url value="/boardDetail" var="detailUrl">
				<c:param name="board_title" value="제목" />
				<c:param name="board_content" value="내용" />
			</c:url>
			<a href="${detailUrl }">상세조회 2번입니다다다다다닫다다다다</a>
		</li>
		<li>수정</li>
		<li>삭제</li>
		<li>등록</li>
	</ol>

</body>
</html>