<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Image Shop</title>
<link rel="stylesheet" href="/css/codedetail.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">


		<h2>
			<spring:message code="codedetail.header.read" />
		</h2>

		<form:form modelAttribute="codeDetail">
			<table class="codedetail_table">
				<tr>
					<td><spring:message code="codedetail.groupCode" /></td>
					<td><form:select path="groupCode" items="${groupCodeList}" itemValue="value" itemLabel="label" readonly="true" /></td>
					<td><font color="red"><form:errors path="groupCode" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="codedetail.codeValue" /></td>
					<td><form:input path="codeValue" readonly="true" /></td>
					<td><font color="red"><form:errors path="codeValue" /></font></td>
				</tr>
				<tr>
					<td><spring:message code="codedetail.codeName" /></td>
					<td><form:input path="codeName" readonly="true" /></td>
					<td><font color="red"><form:errors path="codeName" /></font></td>
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
		$(document).ready(
				function() {

					var formObj = $("#codeDetail");

					$("#btnEdit").on("click",
							function() {
								formObj.attr("action", "/codedetail/modify");
								formObj.attr("method", "get");
								formObj.submit();

								/* var groupCode = $("#groupCode");
								var groupCodeVal = groupCode.val();

								var codeValue = $("#codeValue");
								var codeValueVal = codeValue.val();

								self.location = "/codedetail/modify?groupCode="
										+ groupCodeVal + "&" + "codeValue="
										+ codeValueVal; */
							});

					$("#btnRemove").on("click", function() {
						formObj.attr("action", "/codedetail/remove");
						formObj.submit();
					});

					$("#btnList").on("click", function() {
						self.location = "list";
					});

				});
	</script>



</body>
</html>