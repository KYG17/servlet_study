<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JS 방식!</title>
</head>
<body>
	<input type="text" id="user_name">
	<input type="button" value="제출(1)" onclick="jsGetTest();">
	<input type="button" value="제출(2)" onclick="jsPostTest();">
	
	
	<div id="result_div">
	
	</div>
	
	<script>
		const jsPostTest = function(){
			//post 방식
			// 1. 객체 생성
			const xhr = new XMLHttpRequest();
			//2. open()메소드 호출
			xhr.open("post","/jsAjaxPost");
			//3. 서버 응답 처리 함수 생성
			const userName = document.getElementById('user_name').value;
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					document.getElementById('result_div').innerHTML += xhr.responseText;
				}
			}
			// 4. Content-Type을 설정
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			
			// 5. send() 함수 설정
			xhr.send("userName="+userName);
		}
	
	
	
	
	
	
	
	
		const jsGetTest = function(){
			console.log("연결 확인ㅋㅋ");
			//1. XMLHttpRequest 객체 생성
			const xhr = new XMLHttpRequest();
			//2. open() 메소드 호출
			//매개변수로 방식, 주소, 동기/비동기
			//동기 / 비동기 안쓰면 비동기 방식으로 !
			const userName = document.getElementById('user_name').value;
			xhr.open("get","/jsAjaxGet?userName="+userName);
			//3. 서버 응답 처리 함수 생성
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					const result = xhr.responseText;
					document.getElementById("result_div").innerHTML += result;
				}
			}
			//4. 요청 보내기
			xhr.send();
		}
	
	</script>
</body>
</html>