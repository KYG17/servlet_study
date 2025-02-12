<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제이쿼리 Ajax</title>
<%--<script src="../../resources/jquery-3.7.1.js"></script> --%>
<script src="<%= request.getContextPath()%>/resources/jquery-3.7.1.js"></script>
</head>
<body>
	<input type="text" id="userId" name="user_id">
	<button type="button" id="get_btn">Get방식</button>
	<button type="button" id="post_btn">Post방식</button>
	<div id="result_div"></div>
	
	<script>
		$(document).ready(function(){
			//post방식
			$("#post_btn").click(function(){
				//클릭된 시점에 value를 가져와야 함!
				const userId = $("#userId").val();
				$.ajax({
					//url뒤에 key는 value는 안써요? 네! post방식이자나요~
					url : "/jqueryAjaxGet",
					type : "post",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					//servelt에 보내는 데이터 , 왼쪽은 key 오른쪽은 value(=$("#userId").val())
					//데이터가 여러개면 , 하고 key : value
					data : {userId : userId},
					success:function(data){
						$("#result_div").append("===✅ post 요청 성공!===");
						$("#result_div").append(data);
					},
					error:function(){
						alert("서버 요청 중 오류 발생");
					}		
				})
			})
			
			
			//get방식
			$("#get_btn").click(function(){
				const userId = $("#userId").val();
				$.ajax({
					//이런 url을 가지고 있는 Servlet을 만들거에요!(=요청을 보낼거에요)
					//url 필수 ! 
					url:"/jqueryAjaxGet?userId="+userId,
					type:'get',
					//success, error는 fuction함수
					success:function(data){
				        console.log("✅ 요청 성공!");
						console.log(data);
						$("#result_div").append(data);
					},
					//매개변수로 request,status,error 3개!
					error:function(request,status,error){
						console.log("=======error=======");
						console.log(request);
						console.log(status);
						console.log(error);
					}
				})
			})
			
		})	
	</script>

</body>
</html>