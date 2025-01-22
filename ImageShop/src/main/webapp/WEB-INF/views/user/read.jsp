<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/user.css">
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="user.header.read" />
		</h2>

		<form:form modelAttribute="member">
			<form:hidden path="userNo" />
			<table class="user_table">
				<tr>
					<td><spring:message code="user.userId" /></td>
					<td><form:input path="userId" readonly="true" /></td>
					<td><spring:message code="user.userName" /></td>
					<td><form:input path="userName" readonly="true" /></td>
					<td><spring:message code="user.job" /></td>
					<td><form:select path="job" items="${jobList}" itemValue="value" itemLabel="label" disabled="true" /></td>
				</tr>
				<tr>
					<td><spring:message code="user.auth" /> - 1</td>
					<td><form:select path="authList[0].auth" disabled="true">
							<form:option value="" label="=== 선택해 주세요===" />
							<form:option value="ROLE_USER" label="사용자" />
							<form:option value="ROLE_MEMBER" label="회원" />
							<form:option value="ROLE_ADMIN" label="관리자" />
						</form:select></td>
				</tr>
				<tr>
					<td><spring:message code="user.auth" /> - 2</td>
					<td><form:select path="authList[1].auth" disabled="true">
							<form:option value="" label="=== 선택해 주세요 ===" />
							<form:option value="ROLE_USER" label="사용자" />
							<form:option value="ROLE_MEMBER" label="회원" />
							<form:option value="ROLE_ADMIN" label="관리자" />
						</form:select></td>
				</tr>
				<tr>
					<td><spring:message code="user.auth" /> - 3</td>
					<td><form:select path="authList[2].auth" disabled="true">
							<form:option value="" label="=== 선택해 주세요===" />
							<form:option value="ROLE_USER" label="사용자" />
							<form:option value="ROLE_MEMBER" label="회원" />
							<form:option value="ROLE_ADMIN" label="관리자" />
						</form:select>
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
			<button type="button" id="btnAjax">비동기요청</button>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/user_read.js">
		
	</script>
</body>
</html>