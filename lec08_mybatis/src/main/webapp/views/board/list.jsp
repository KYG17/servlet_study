<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value='/boardList'/>" id="searchFrm">
	<fieldset>
		<legend>검색하기</legend>
		<input type="text" name="board_title" placeholder="제목">
		<input type="text" name="board_content" placeholder="내용">
		<input type="text" name="member_name" placeholder="작성자">
		<input type="submit" value="조회">
	</fieldset>
	<fieldset>
		<legend>정렬하기</legend>
		<select name="order_type" id="order_type" >
			<option value="-1">선택입니다</option>
			<option value="1">최신순 ㅋ</option>
			<option value="2">옛날꺼 ㅋ</option>
		</select>
	</fieldset>
</form>
<script>
	const orderType = document.getElementById('order_type');
	orderType.onchange = function(){
		document.getElementById('searchFrm').submit();
	}
</script>



	<table border="1" style="text-align: center">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>날짜</th>
			</tr>
		</thead>
	<tbody>
		<!-- resultList가 비어있으면 ->게시글이 없습니다  -->
		<!-- 그렇지 않으면 번호,제목,내용 출력 -->
		<c:choose>
			<c:when test="${not empty resultList }">
				<c:forEach var="list" items="${resultList }" varStatus="vs">
					<tr>
						<td >${vs.count }</td>
						<td>${list.boardTitle }</td>						
						<td>${list.boardContent }</td>
						<fmt:parseDate value="${list.regDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="todatDate" />
						<td><fmt:formatDate value="${todatDate }" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>			
				</c:forEach>		
			</c:when>
			<c:otherwise>
				<tr>
				<td colspan="3">조회된 결과가 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
	
	
	</tbody>
	</table>

</body>
</html>