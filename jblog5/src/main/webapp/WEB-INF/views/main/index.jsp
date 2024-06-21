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
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form class="search-form">
			<fieldset>
				<input type="text" name="keyword" />
				<spring:message code="main.search" var="search_button"/>
				<input type="submit" value="${search_button }" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title"> <label><spring:message code="main.blog.title"/></label>
				<input type="radio" name="which" value="tag"> <label><spring:message code="main.tag"/></label>
				<input type="radio" name="which" value="blog-user"> <label><spring:message code="main.blog.user"/></label>
			</fieldset>
		</form>
	</div>
</body>
</html>