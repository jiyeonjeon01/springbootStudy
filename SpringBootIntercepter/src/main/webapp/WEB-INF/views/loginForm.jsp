<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인터셉터 로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="/login">
		<div>
			<input type="text" name="userId" value="admin">
		</div>
		<div>
			<input type="password" name="userPw" value="1234">
		</div>
		<div>
			<input type="submit">
		</div>
	</form>
</body>
</html>