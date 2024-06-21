package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@Transactional
	public void join(UserVo vo) {
		userRepository.insert(vo);
		blogRepository.insert(new BlogVo(vo.getId(), vo.getName(), "/assets/images/logo.jpg"));
		categoryRepository.insert(new CategoryVo("미분류", "", vo.getId()));
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPasssword(id, password);
	}
	
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
}
