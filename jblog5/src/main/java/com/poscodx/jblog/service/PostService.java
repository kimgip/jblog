package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public void write(PostVo vo) {
		postRepository.insert(vo);
	}
	
	@Transactional
	public List<PostVo> getPostList(Long categoryNo, String id){
		if(categoryRepository.findByNoAndId(categoryNo, id) == null) {
			return null;
		}
		return postRepository.findPostList(categoryNo);
	}
	
	@Transactional
	public PostVo getPostByNo(Long no, Long categoryNo, String id) {
		if(categoryRepository.findByNoAndId(categoryNo, id) == null) {
			return null;
		}
		return postRepository.findPostByNoAndCategoryNo(no, categoryNo);
	}
	
	@Transactional
	public void delete(Long no, String id) {
		if(categoryRepository.findByPostNoAndId(no, id) == null) {
			return;
		}
		postRepository.deleteByNo(no);
	}
}
