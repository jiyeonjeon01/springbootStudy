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

		$("#btnModify").on("click", function() {
			formObj.attr("action", "/item/modify");
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
	<h2>상품 수정</h2>
	<form:form modelAttribute="item" action="modify"
		enctype="multipart/form-data">
		<form:hidden path="itemId" />
		<form:hidden path="pictureUrl" />

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
				<td><img src="/item/display?itemId=${item.itemId}" width="210"
					height="240"></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="picture" /></td>
				<td></td>
			</tr>
			<tr>
				<td>개요</td>
				<td><form:textarea path="description" /></td>
				<td><font color="red"><form:errors path="description" /></font></td>
			</tr>
		</table>

	</form:form>

	<div>
		<button type="button" id="btnModify">Modify</button>
		<button type="button" id="btnList">List</button>
	</div>
</body>
</html>
