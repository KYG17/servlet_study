<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='com.gn.board.vo.Board , java.util.List' %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href='<%=request.getContextPath()%>/resources/css/board/list.css' rel="stylesheet" type="text/css">
<% List<Board> list = (List<Board>)request.getAttribute("resultList"); %>
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 목록</h3>
			</div><br>
			<div class="board_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="50%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
					<% for(Board board : list) {%>
					<tr>
						<th><%=board.getBoardNo() %></th>
						<th><%=board.getBoardTitle() %></th>
						<th><%=board.getMemberName() %></th>
						<th><%=board.getRegDate() %></th>					
					</tr>
					<%} %>	
					</tbody>
				</table>
			</div>
		</div>
	</section>	
</body>
</html>