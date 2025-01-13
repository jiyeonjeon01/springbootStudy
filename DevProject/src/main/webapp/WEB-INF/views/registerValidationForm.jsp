<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 등록</title>
</head>
<body>
	<h2>사용자 등록 화면</h2>
	<form:form modelAttribute="member" method="post"
		action="registerValidation">
		<table>
			<tr>
				<td>유저ID</td>
				<td><form:input path="userId" /><font color="red"><form:errors
							path="userId" /></font></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><form:password path="password" /><font color="red"><form:errors
							path="password" /></font></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><form:input path="userName" /><font color="red"><form:errors
							path="userName" /></font></td>
			</tr>
			<tr>
				<td>E-MAIL</td>
				<td><form:input path="email" /><font color="red"><form:errors
							path="email" /></font></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><form:radiobutton path="gender" value="male" label="Male" />
					<br> <form:radiobutton path="gender" value="female"
						label="Female" /> <br> <form:radiobutton path="gender"
						value="other" label="Other" /></td>
			</tr>
		</table>
		<table>
			<tr>
				<td align="center"><input type="submit" name="btnSubmit"
					value="등록"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
