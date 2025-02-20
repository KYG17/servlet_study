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
	<table border="1" style="text-align: center">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
	<tbody>
		<!-- resultList가 비어있으면 ->게시글이 없습니다  -->
		<!-- 그렇지 않으면 번호,제목,내용 출력 -->
		<c:choose>
			<c:when test="${not empty resultList }">
				<c:forEach var="list" items="${resultList }">
					<tr>
						<td >${list.boardNo }</td>
						<td>${list.boardTitle }</td>						
						<td>${list.boardContent }</td>
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