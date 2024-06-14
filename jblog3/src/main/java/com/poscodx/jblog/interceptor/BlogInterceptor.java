package com.poscodx.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.vo.BlogVo;

public class BlogInterceptor implements HandlerInterceptor {
	private BlogService blogService;
	
	public BlogInterceptor(BlogService blogService) {
		this.blogService = blogService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		BlogVo blogItem = (BlogVo)request.getServletContext().getAttribute("blogItem");
		
//		if(blogItem == null) {
//			request.getServletContext().setAttribute("blogItem", blogService.getById(request.getParameter("id")));
//		}
		return true;
	}

}
