<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis 게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var formObj = $("#board");
		console.log(formObj);
		$("#btnModify").on("click", function() {
			var boardNo = $("#boardNo");
			var boardNoVal = boardNo.val();

			self.location = "/board/modify?boardNo=" + boardNoVal;
		});
		$("#btnRemove").on("click", function() {
			formObj.attr("action", "/board/remove");
			formObj.submit();
		});
		$("#btnList").on("click", function() {
			self.location = "/board/list";
		});
	});
</script>
</head>
<body>
	<h2>게시글 읽기</h2>
	<form:form modelAttribute="board">
		<form:hidden path="boardNo" />
		<table>
			<tr>
				<td>Title</td>
				<td><form:input path="title" readonly="true" /></td>
			</tr>
			<tr>
				<td>Writer</td>
				<td><form:textarea path="writer" readonly="true" /></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><form:textarea path="content" readonly="true" /></td>
			</tr>
		</table>
	</form:form>
	<div>
		<button type="button" id="btnModify">Modify</button>
		<button type="button" id="btnRemove">Remove</button>
		<button type="button" id="btnList">List</button>
	</div>
</body>