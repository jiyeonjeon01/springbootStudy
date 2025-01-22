<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			<spring:message code="codegroup.header.read" />
		</h2>
		<form:form modelAttribute="codeGroup">
			<table class="read_table">
				<tr>
					<td><spring:message code="codegroup.groupCode" /></td>
					<td><form:input path="groupCode" readonly="true" /></td>
					<td><font color="red"><form:errors path="groupCode" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="codegroup.groupName" /></td>
					<td><form:input path="groupName" readonly="true" /></td>
					<td><font color="red"><form:errors path="groupName" /></font></td>
				</tr>
			</table>
		</form:form>
		<div>
			<button type="button" id="btnEdit">
				<spring:message code="action.edit" />
			</button>
			<button type="button" id="btnRemove">
				<spring:message code="action.remove" />
			</button>
			<button type="button" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
		$(document).ready(function() {
			var formObj = $("#codeGroup");
			$("#btnEdit").on("click", function() {
				var groupCode = $("#groupCode");
				var groupCodeVal = groupCode.val();

				self.location = "modify?groupCode=" + groupCodeVal;
			});
			$("#btnRemove").on("click", function() {
				formObj.attr("action", "/codegroup/remove");
				formObj.submit();
			});
			$("#btnList").on("click", function() {
				self.location = "list";
			});
		});
	</script>

</body>
</html>