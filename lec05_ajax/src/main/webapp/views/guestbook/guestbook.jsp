<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<%= request.getContextPath()%>/resources/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<h1>servlet을 통해서 이동하였습니다!</h1>
		<h2>방명록</h2>
    <input type="text" id="guest_name" placeholder="이름">
    <textarea id="guest_message" placeholder="메시지"></textarea>
    <button id="submit_btn">등록</button>

    <h3>📝 남긴 메시지</h3>
    <ul id="guestbook_list">
    
    </ul>
    
    <script>
    	$(function(){
    		$("#submit_btn").click(function(){
    			const guestArr = $("#guest_name").val();
    			const msg = $("#guest_message").val();
    			/* console.log(textResult);
    			console.log(guestMsg); */
    			$.ajax({
    				url : "/GuestBookEndServlet",
    				type : "get" ,
    				dataType : "json", // 응답의 결과로 json형태로 받아줘
    				data : {guestArr : guestArr , msg : msg},
    				success : function(data){
    					$("#guestbook_list").append("<li>"+"이름 : "+data.name+"방명록 : "+data.message+"</li>")
    				}
    				
    			})
    		})
    	})
    </script>
</body>
</html>