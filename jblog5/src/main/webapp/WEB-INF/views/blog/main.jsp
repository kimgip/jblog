<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						<c:set var="contents" value='${fn:replace(postVo.contents, "<", "&lt;") }'/>
						<c:set var="contents" value='${fn:replace(contents, ">", "&gt;") }'/>
						${fn:replace(contents, newline, "<br>") }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="vo">
						<li><a href="${pageContext.request.contextPath}/${id }/${vo.categoryNo }/${vo.no }">${vo.title }</a> 
						<span>${vo.regDate }</span>
						<c:if test="${not empty authUser }">
						<span><a href="${pageContext.request.contextPath}/${authUser.id}/admin/delete?no=${vo.no}" class="del">
								<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></span>						
						</c:if>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/blog-navigator.jsp" />
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>