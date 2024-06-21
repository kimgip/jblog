package com.poscodx.jblog.service;

import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;

@Service
public class BlogService {
	private BlogRepository blogRepository;

	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public BlogVo getById(String id) {
		return blogRepository.findById(id);
	}
	
	public void update(BlogVo vo) {
		blogRepository.update(vo);
	}
}
