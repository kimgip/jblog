<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<h1><a href="${pageContext.request.contextPath}/${id}">${blogVo.title }</a></h1>
	<ul>
	<c:choose>
		<c:when test="${empty authUser }">
			<li><a href="${pageContext.request.contextPath}/user/login"><spring:message code="header.gnb.login"/> </a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/user/logout"><spring:message code="header.gnb.logout"/></a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin"><spring:message code="header.gnb.blog.settings"/> </a></li>		
		</c:otherwise>
	</c:choose>
	</ul>
</div>