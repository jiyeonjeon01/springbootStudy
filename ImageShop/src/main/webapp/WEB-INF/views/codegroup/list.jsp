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
<link rel="stylesheet" href="/css/codegroup.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="codegroup.header.list" />
		</h2>
		<a href="register"><spring:message code="action.new" /></a>
		<table border="1" class="list_table">
			<tr>
				<th align="center" width="160"><spring:message code="codegroup.groupCode" /></th>
				<th align="center" width="160"><spring:message code="codegroup.groupName" /></th>
				<th align="center" width="180"><spring:message code="codegroup.regdate" /></th>
			</tr>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="3"><spring:message code="common.listEmpty" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="codeGroup">
						<tr>
							<td align="center">${codeGroup.groupCode}</td>
							<td align="left"><a href="/codegroup/read?groupCode=${codeGroup.groupCode}">${codeGroup.groupName} </a></td>
							<td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${codeGroup.regDate}" /></td>
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