<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form class="login-form" method="post" action="${pageContext.request.contextPath }/user/auth">
      		<label><spring:message code="user.label.id"/></label> <input type="text" name="id">
      		<label><spring:message code="user.label.password"/></label> <input type="text" name="password">
      		<spring:message code="user.login.button.text" var="login_button"/>
      		<input type="submit" value="${login_button }">
		</form>
	</div>
</body>
</html>
