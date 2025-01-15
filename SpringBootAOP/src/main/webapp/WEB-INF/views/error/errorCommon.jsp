<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AOP 게시판 에러 처리</title>
</head>
<body>
	<h1>에러 발생!</h1>
	<p>${exception.toString()}</p>
</body>
</html>
