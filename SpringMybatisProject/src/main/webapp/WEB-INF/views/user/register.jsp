<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis USER 등록</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var formObj = $("#member");
		$("#btnRegister").on("click", function() {
			formObj.attr("action", "/user/register");
			formObj.attr("method", "post");
			formObj.submit();
		});
	});
</script>
</head>
<body>
	<h3>회원 등록</h3>
	<form:form modelAttribute="member" action="/user/register">
		<table>
			<tr>
				<td>userid</td>
				<td><form:input path="userId" /></td>
				<td><font color="red"><form:errors path="userId" /></font></td>
			</tr>
			<tr>
				<td>userpw</td>
				<td><form:input path="userPw" /></td>
				<td><font color="red"><form:errors path="userPw" /></font></td>
			</tr>
			<tr>
				<td>username</td>
				<td><form:input path="userName" /></td>
				<td><font color="red"><form:errors path="userName" /></font></td>
			</tr>
		</table>
	</form:form>
	<div>
	<button type="submit" id="btnRegister">등록</button>
	</div>
</body>
</html>
