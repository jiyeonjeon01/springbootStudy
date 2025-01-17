<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인터셉터 활용 - 세션 처리</title>
</head>
<body>
	<h1>환영합니다!</h1>
	<P>서버의 시간은 ${serverTime} 입니다.</P>
		${userInfo.userId}
	<br>${userInfo.userName}
	<br>
</body>
</html>