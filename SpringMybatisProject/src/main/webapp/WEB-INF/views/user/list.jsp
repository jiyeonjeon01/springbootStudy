<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 회원</title>
</head>
<body>
	<form:form modelAttribute="member" method="POST" action="search">
		<h3>회원 목록</h3>
		USER ID : <form:input path="userId" />
		<input type="submit" value="검색" />
	</form:form>
	
	<a href="/user/register">New</a>
	<table border="1">
		<tr>
			<th align="center" width="60">NO</th>
			<th align="center" width="80">USERID</th>
			<th align="center" width="50">USERPW</th>
			<th align="center" width="50">USERNAME</th>
			<th align="center" width="180">REGDATE</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="5">사용자 아이디가 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
			
			
				<c:forEach items="${list}" var="member">
					<tr>
						<td align="center">${member.userNo}</td>
						<td align="center"><a href='/user/read?userNo=${member.userNo}'>${member.userId}</a></td>
						<td align="left">${member.userPw}</td>
						<td align="right">${member.userName}</td>
						<td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${member.regDate}" /></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>
