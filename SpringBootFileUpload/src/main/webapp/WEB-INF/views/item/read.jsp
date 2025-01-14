<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 회원</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		let formObj = $("#member");
		console.log(formObj);
		
		// 수정 요청만 진행
		$("#btnModify").on("click", function() {
			formObj.attr("action", "/user/modify");
			formObj.attr("method", "get");
			formObj.submit();
		});

		// 삭제 요청만 진행 
		$("#btnRemove").on("click", function() {
			formObj.attr("action", "/user/remove");
			formObj.attr("method", "post");
			formObj.submit();
		});

		// 리스트 
		$("#btnList").on("click", function() {
			self.location = "/user/list";
		});

	});
</script>
</head>
<body>
	<h3>회원 상세 정보</h3>
	<form:form modelAttribute="member">

		<form:hidden path="userNo" />

		<table>
			<tr>
				<td>userid</td>
				<td><form:input path="userId" readonly="true" /></td>

			</tr>
			<tr>
				<td>username</td>
				<td><form:input path="userName" readonly="true" /></td>
			</tr>
			<tr>
				<td>auth - 1</td>
				<td><form:select path="authList[0].auth" disabled="true" >
						<form:option value="" label="=== 선택해 주세요 ==="/>
						<form:option value="ROLE_USER" label="사용자" />
						<form:option value="ROLE_MEMBER" label="회원" />
						<form:option value="ROLE_ADMIN" label="관리자" />
					</form:select></td>
			</tr>
			<tr>
				<td>auth - 2</td>
				<td><form:select path="authList[1].auth" disabled="true" >
						<form:option value="" label="=== 선택해 주세요 ==="/>
						<form:option value="ROLE_USER" label="사용자" />
						<form:option value="ROLE_MEMBER" label="회원" />
						<form:option value="ROLE_ADMIN" label="관리자" />
					</form:select></td>
			</tr>
			<tr>
				<td>auth - 3</td>
				<td><form:select path="authList[2].auth" disabled="true" >
						<form:option value="" label="=== 선택해 주세요 ==="/>
						<form:option value="ROLE_USER" label="사용자" />
						<form:option value="ROLE_MEMBER" label="회원" />
						<form:option value="ROLE_ADMIN" label="관리자" />
					</form:select></td>
			</tr>
		</table>
	</form:form>
	<div>
		<button type="button" id="btnModify">Modify</button>
		<button type="button" id="btnRemove">Remove</button>
		<button type="button" id="btnList">List</button>
	</div>
</body>
</html>
