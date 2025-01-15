<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AOP 게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var formObj = $("#board");
		$("#btnModify").on("click", function() {
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "post");
			formObj.submit();
		});
		$("#btnList").on("click", function() {
			self.location = "/board/list";
		});
	});
</script>
</head>
<body>
	<h2>AOP 게시글 수정</h2>
	<form:form modelAttribute="board" action="modify">
		<form:hidden path="boardNo" />
		<table>
			<tr>
				<td>Title</td>
				<td><form:input path="title" /></td>
				<td><font color="red"><form:errors path="title" /></font></td>
			</tr>
			<tr>
				<td>Writer</td>
				<td><form:input path="writer" /></td>
				<td><font color="red"><form:errors path="writer" /></font></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><form:textarea path="content" /></td>
				<td><font color="red"><form:errors path="content" /></font></td>
			</tr>
		</table>
	</form:form>

	<div>
		<button type="button" id="btnModify">Modify</button>
		<button type="button" id="btnList">List</button>
	</div>
</body>
</html>
