<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/quiz/cookie"/>
<c:choose>
<c:when test="${loginUser == null }">
	<form method="post" action="chkUser">
		<input type="text" name="id" placeholder="아이디"><br>
		<input type ="password" name="pwd" placeholder="비밀번호"><br>
		<input type="submit" value="로그인">
	</form>
</c:when>
<c:otherwise>
	<hr>
	${loginUser }님 로그인 상태 입니다<br>
	<a href="chkUser">main 이동</a>
</c:otherwise>
</c:choose>
</body>

</html>