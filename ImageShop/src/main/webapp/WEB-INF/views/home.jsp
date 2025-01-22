<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Shop</title>
<!-- <script type="text/javascript" src="/js/test.js"></script> -->
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <jsp:include page="./common/menu.jsp" />
    <div align="center">
        <h1>
            <spring:message code="common.homeWelcome" />
        </h1>
        <P>${serverTime}</P>
    </div>
    <jsp:include page="./common/footer.jsp" />
</body>
</html>