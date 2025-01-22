<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Image Shop</title>
<link rel="stylesheet" href="/css/list.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">


		<h2>
			<spring:message code="user.header.list" />
		</h2>
		<a href="register"><spring:message code="action.new" /></a>

		<table border="1" class="user_table">
			<tr>
				<th align="center" width="60"><spring:message code="user.no" /></th>
				<th align="center" width="80"><spring:message code="user.userId" /></th>
				<th align="center" width="300"><spring:message code="user.userPw" /></th>
				<th align="center" width="100"><spring:message code="user.userName" /></th>
				<th align="center" width="100"><spring:message code="user.job" /></th>
				<th align="center" width="180"><spring:message code="user.regdate" /></th>
			</tr>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="6"><spring:message code="common.listEmpty" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="member">
						<tr>
							<td align="center">${member.userNo}</td>
							<td align="center"><a href='/user/read?userNo=${member.userNo}'>${member.userId}</a></td>
							<td align="left">${member.userPw}</td>
							<td align="right">${member.userName}</td>
							<td align="right">${member.job}</td>
							<td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${member.regDate}" /></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>


	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


	<script>
		var result = "${msg}";

		if (result === "SUCCESS") {
			alert("<spring:message code='common.processSuccess' />");
		}
	</script>


</body>
</html>