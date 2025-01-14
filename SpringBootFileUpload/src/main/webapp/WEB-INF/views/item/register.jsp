<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		var formObj = $("#item");

		$("#btnRegister").on("click", function() {
			formObj.attr("action", "/item/register");
			formObj.attr("method", "post");
			formObj.submit();
		});

		$("#btnList").on("click", function() {
			self.location = "/item/list";
		});

	});
</script>
</head>
<body>
	<h2>상품 등록</h2>
	<form:form modelAttribute="item" action="/item/register"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>상품명</td>
				<td><form:input path="itemName" /></td>
				<td><font color="red"><form:errors path="itemName" /></font></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><form:input path="price" />&nbsp;원</td>
				<td><font color="red"><form:errors path="price" /></font></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="picture" /></td>
				<td></td>
			</tr>
			<tr>
				<td>개요</td>
				<td><form:textarea path="description" /></td>
				<td><form:errors path="description" /></td>
			</tr>
		</table>
	</form:form>

	<div>
		<button type="button" id="btnRegister">Register</button>
		<button type="button" id="btnList">List</button>
	</div>
</body>
</html>
