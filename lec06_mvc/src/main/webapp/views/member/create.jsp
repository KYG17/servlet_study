<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link href="<%=request.getContextPath() %>/resources/css/member/create.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.js"></script>
</head>
<body>
	<%@ include file ="../include/header.jsp" %>
	<%@ include file ="../include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>회원 가입</h3>
				<br>
				<span>※아이디는 추후에 수정할 수 없습니다. 
				<br>💕신중하게 선택해주세요💕</span>
			</div>
			<div class="create_member_form">
				<form name="create_member_form" action="/memberCreateEnd" method="post">
					<input type="text" name="member_id" placeholder="아이디"><br>
					<input type="password" name="member_pw" placeholder="비밀번호를 입력해주세요"> <br>
					<input type="password" name="member_pw_check" placeholder="비밀번호 확인"><br>
					<input type="text" name="member_name" placeholder="닉네임"><br>
					<input type="button" value="회원가입" onclick="createMemberForm();">
					
				
				</form>
			</div>
			<div class="login">
				<a href="/memberLogin">로그인</a>
			</div>
		</div>
	</section>
	
	<script>
		const createMemberForm = function(){
			//form의 name값
			const form = document.create_member_form;
			//if안에 들어가있는 form은 위에 있는 변수
			//아이디안에 아무것도 적지 않았다면 ! false일 때 이런 일 하고 싶어요
			if(!form.member_id.value){
				alert('아이디를 입력하세요⏰⏰⏰')
				form.member_id.focus();
			}else if(!form.member_pw.value){
				alert('비밀번호를 입력해주세요😊😊😊')
				form.member_pw.focus();
			}else if(!form.member_pw_check.value){
				alert('비밀번호 확인을 입력해주세요😎😎😎')
				form.member_pw_check.focus();
			}else if(form.member_pw.value != form.member_pw_check.value){
				alert('비밀번호가 일치하지 않습니다😒😒😒');
				form.member_pw_check.focus();
				//닉네임 유효성 검사도 했다고 가정
			}else if(!form.member_name.value){
				alert("닉네임을 입력해주세요!!");
				form.member_name.focus();
			}else{
				//form.submit();
					//form의 action값
					//type
					//data가 갔다가 올 때의 datatype은? -> JSON입니다
					//data : 는 보내는 data
					//success 의 매개변수 data 는 응답 받은 data
				$.ajax({
					url : "/memberCreateEnd",
					type : "post",
					data : {"member_id" : form.member_id.value,
							"member_pw" : form.member_pw.value,
							"member_name" : form.member_name.value},
					dataType:"JSON",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					success : function(data){
						//console.log(data);
						//res_msg를 alert창에 출력
						//만약에 res_code가 200가 같다면
						//경로로 이동(location)
						alert(data.res_msg);
						if(data.res_code == 200){
							window.location.href = "/views/member/create_success.jsp";
						}else{
							window.location.href = "/views/member/create_fail.jsp";
						}
						
					}
				});
			}					
		}
	</script>
	
</body>
</html>