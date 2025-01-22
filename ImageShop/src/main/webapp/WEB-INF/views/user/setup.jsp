<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Image Shop</title>
<link rel="stylesheet" href="/css/user.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">



		<h2>
			<spring:message code="user.header.register" />
		</h2>
		<form:form modelAttribute="member" action="/user/setup">
			<table>
				<tr>
					<td><spring:message code="user.userId" /></td>
					<td><form:input path="userId" /></td>
					<td><font color="red"><form:errors path="userId" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="user.userPw" /></td>
					<td><form:input path="userPw" /></td>
					<td><font color="red"><form:errors path="userPw" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="user.userName" /></td>
					<td><form:input path="userName" /></td>
					<td><font color="red"><form:errors path="userName" /></font></td>
				</tr>
			</table>
		</form:form>
		<div>
			<button type="button" id="btnRegister">
				<spring:message code="action.register" />
			</button>
			<button type="button" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>




	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />



	<script>
		$(document).ready(function() {
			var formObj = $("#member");
			$("#btnRegister").on("click", function() {
				formObj.submit();
			});
			$("#btnList").on("click", function() {
				self.location = "list";
			});
		});
	</script>





</body>
</html>