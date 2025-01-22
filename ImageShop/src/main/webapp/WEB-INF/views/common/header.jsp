<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div align="right">
	<table>
		<tr>
			<!-- 로그인을 하지 않은 경우 로그인 페이지로 이동. -->
			<sec:authorize access="!isAuthenticated()">
				<td width="80"><a href="/user/register"><spring:message code="header.joinMember" /></a></td>
				<!-- 로그인을 메뉴에 추가한다. -->
				<td width="80"><a href="/auth/login"><spring:message code="header.login" /></a></td>
			</sec:authorize>
			<!-- 로그인을 거친 인증된 사용자인 경우 사용자명을 보여주고 로그아웃 페이지로 이동 -->
			<sec:authorize access="isAuthenticated()">
				<td width="180"><sec:authentication property="principal.username" /> 님 <a href="/auth/logout"> <spring:message code="header.logout" /></a></td>
			</sec:authorize>
		</tr>
	</table>
</div>
<hr>
