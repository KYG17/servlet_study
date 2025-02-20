<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 버전에 따라서 uri는 바뀜
    docs에 보면 친절하게 설명해준다
    귀찮다고 안하지 말고 좀 보자 !!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL/JSTL연습</title>
</head>
<body>
	<H1>EL</H1>
	<%
		//점점 더 범위가 커지는중이다!
		// 1.pageScope 설정
		pageContext.setAttribute("test","페이지 범위");
		// 2.requestScope 설정
		request.setAttribute("test", "request범위");
		// 3.sessionScope 살장
		session.setAttribute("test","세션 범위");
		// 4.applicationScope 설정
		application.setAttribute("test","애플리케이션 범위");
	
	%>
	<h3>1.JSP 방식</h3>
	<UL>
		<li>
			<%=pageContext.getAttribute("test") %>		
		</li>
		<li>
			<%=request.getAttribute("test") %>
		</li>
		<li>
			<%=session.getAttribute("test") %>
		</li>
		<li>
			<%=application.getAttribute("test") %>
		</li>
	</UL>
	<h3>2.EL 방식</h3>
	<p>${test}</p>
<!-- 	//특정 값을 가져오고 싶으면  -->
	<p>${sessionScope.test}</p>
	<p>${applicationScope.test}</p>
	<h3>3. 객체 꺼내오기</h3>
	
	<!-- //Person객체를 사용하기 위해 import필수 -->
	<%@ page import="com.gn.vo.Person" %>
	<% 
		request.setAttribute("person", new Person("김철수",77)); 
	
	%>
	<ol>
		<li>
			<% Person p = (Person)request.getAttribute("person"); %>
			JSP 방식 :	<%=p.getName() + " : " +p.getAge() %>	
		</li>	
		<li>
		<%-- 1. 다운캐스트 x  2. getter 알아서 실행됨 vo에서 get/setter는 만들어야함 --%>
			EL방식 :${person.name} : ${person.age}  
		</li>
	</ol>
	<h2>EL의 연산자</h2>
	<!-- 세팅 하는 부분은 EL이 하는게 아니다! -->
	<%@page import="java.util.*" %>
	<%
		// 1.숫자
		request.setAttribute("num1",10);
		request.setAttribute("num2",3);
		
		// 2.문자
		request.setAttribute("str1", "사과");
		request.setAttribute("str2", "바나나");
		
		//3. 객체
		request.setAttribute("p1", new Person("이영희",23));
		request.setAttribute("p2",null);
		
		//4. 리스트
		request.setAttribute("list1",new ArrayList<String>());
		List<String>list2 = new ArrayList<String>();
		list2.add("오늘 날씨가 춥네요");
		request.setAttribute("list2",list2);
	%>
	<h3>1. 산술연산</h3>
	<p>
		10 + 3 = ${num1+num2}<br>
		10 - 3 = ${num1-num2}<br>
		10 / 3 = ${num1 div num2}<br>
		10 % 3 = ${num1 mod num2}
	
	</p>
	<h3>2.문자열 연결</h3>
	<p>
		${str1 } : ${str2 }
		
	</p>
	<h3>3. 대소 비교</h3>
	<p>
	<!-- 왼쪽에 있는 애가 오른쪽에 있는 애보다 큰가요? true/false -->
		num1이 num2 보다 큰가요? : ${num1 gt num2} <br>
		num1이 num2 보다 작거나 같은가요?? : ${num1 le num2}
	</p>
	<h3>4. 동등 비교</h3>
	<p>
	<!-- num1이 10이랑 같아요?  -->
	<!-- num2가 3이랑 다릅니까?  -->
		숫자 일치 : ${num1 eq 10 }<br>
		숫자 불일치 : ${num2 ne 3 }<br>
		문자 일치 : ${str1 eq str2}<br>
		문자 불일치 : ${str1 ne str2 }<br>
	</p>
	<h3>5. 비어있는지 확인</h3>
	<ol>
	<!--비어있습니까?!!  -->
	<!--비어있는게 아니지 않은가요? empty앞에다가 not을 쓴다 -->
		<li>문자 : ${not empty str1 }</li>
		<li>객체 : ${empty p2 }
		<li>컬렉션 : ${empty list1 }
	</ol>
	<h3>6.논리 연산자</h3>
	<p>
	${true and true }<br>
	${num1 eq 10 and num2 eq 3 }<br>
	${false or true }
	
	</p>
	
	
	
	
	
	
	
	
	
	
	<hr>
	<h1>JSTL</h1>
	<h2>1. 변수</h2>
	<!-- 숫자 2개를 선언한고 싶다  -->
	<c:set var="n1" value="15"/>
	<c:set var="n2" value="20"/>
	<c:set var="result" value="${n1+n2 }"/>
	<!-- 출력문! -->
	<c:out value="${result }" /> <br>
	
	<c:set var="hello" value="<b>오늘 수업이 정말 유익하네요!</b>" />
	<c:out value="${hello}" escapeXml="false" />
	
	<!-- 애는 else if 같은 게 없어요! -->
	<h2>2. 조건문(if)</h2>
	
	<c:if test="${num1 le num2 }">
		<p>num1이 num2보다 작거나 같다</p>
	</c:if>
	<h2>3. 조건문(choose)</h2>
	<c:choose>
		<c:when test="${num1 gt 20 }">
			<p>10이 20 보다 큽니까?</p>
		</c:when>
		<c:when test="${num1 ge 10 }">
			<p>num1이 10보다 크거나 같으면서, 20보다 작거나 같나여?</p>
		</c:when>
		<c:otherwise>
			<p>num1이 10보다 작습니까?</p>
		</c:otherwise>
	</c:choose>
	<h2>4. 반복문</h2>
	<!-- step은 안쓰면 default 1  -->
	<c:forEach var="i" begin="1" end="10" step="2">
		<p>반복 숫자 : ${i }
	</c:forEach>
	<%
		String[] colors = {"red","gold","blue"};
		request.setAttribute("colors", colors);
	%>
	<ul>
		<c:forEach var="color" items="${colors }">
			<li style="color:${color};">${color }</li>
		</c:forEach>
		<c:forEach var="i" begin="1" end="6">
			<h${i }>%가 더 편한 것 같습니다</h${i }>
		</c:forEach>
	</ul>
	
	<c:forEach var="num" begin="0" end="5" varStatus="vs">
		<p <c:if test="${vs.first }">style="color:red"</c:if>>
			인덱스 : ${vs.index }<br>
			카운트 : ${vs.count }<br>
			첫번째인가요 ? (true/false) : ${vs.first }<br>
			마지막인가요 ? : ${vs.last }
		</p>
	</c:forEach>
	
	<hr><hr><hr>
	<h1>JSTL Formatting Library</h1>
	<h2>1. formatNumber</h2>
	<!-- 천 단위의 ,가 생김 -->
	<fmt:formatNumber value="1234.567" type="number"/> <br>
	<!-- currency : 소수점 단위가 있으면 반올림 -->
	<fmt:formatNumber value="1234.567" type="currency"/> <br>
	<!-- 특정 나라를 설정하고 싶으면 setLocale과 한세트! -->
	<fmt:setLocale value="en_US"/>
	<fmt:formatNumber value="1234.567" type="currency"/> <br>
	<!-- 퍼센트로 알아서 해서 그리고 반올림 처리 -->
	<fmt:formatNumber value="0.875" type="percent" /> <br>
	
	<fmt:formatNumber value="1234.567" pattern="#,###.##" /><br>
	<fmt:formatNumber value="1234.5" pattern="00000.00" />

	<h2>2. formatDate</h2>
	<!-- now는 데이트 타입의 객체 -->
	<c:set var="now" value="<%=new java.util.Date() %>" />
	<fmt:formatDate value="${now }" type="date"/><br>
	<!-- 패턴을 지정해서 써주자! -->
	<fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss" /><br>
	
	<!-- 문자열 타입의 날짜 정보일뿐 -->
	<!-- pattern: value가 어떤 형식으로 적혀져 있나요? 내가 어떻게 읽으면 되는거야? -->
	<fmt:parseDate value="2025-02-20" pattern="yyyy-MM-dd" var="parsedDate" />
	<fmt:formatDate value="${parsedDate }" pattern="yy년MM월"/><br>
	<br>
	<h1>JSTL Function Lib</h1>
	<c:set var="data" value="How Are You? I am fine" />
	<p><c:out value="${fn:toUpperCase(data) }" /></p>
	<p><c:out value="${fn:replace(data,'fine','괜찮아') }" />
	<p><c:out value="${fn:contains(data, 'You') }?'잇다':'없다' " /></p>
	
	
	
	
	
	
	
	
	
</body>
</html>