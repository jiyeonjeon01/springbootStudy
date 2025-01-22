<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Image Shop</title>
<link rel="stylesheet" href="/css/codegroup.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="codegroup.header.register" />
		</h2>
		<form:form modelAttribute="codeGroup" action="/codegroup/modify" method="post">
			<table class="input_table">
				<tr>
					<td><spring:message code="codegroup.groupCode" /></td>
					<td><form:input path="groupCode" /></td>
					<td><font color="red"><form:errors path="groupCode" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="codegroup.groupName" /></td>
					<td><form:input path="groupName" /></td>
					<td><font color="red"><form:errors path="groupName" /></font></td>
				</tr>
			</table>
		</form:form>
		<div>
			<button type="button" id="btnModify">
				<spring:message code="action.modify" />
			</button>
			<button type="button"" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>


	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
		$(document).ready(function() {
			var formObj = $("#codeGroup");
			$("#btnModify").on("click", function() {
				formObj.submit();
			});
			$("#btnList").on("click", function() {
				self.location = "list";
			});
		});
	</script>

</body>
</html>