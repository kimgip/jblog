package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.PostVo;

@Service
public class PostService {
	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public void write(PostVo vo) {
		postRepository.insert(vo);
	}
	
	public List<PostVo> getPostList(Long no){
		return postRepository.findPostList(no);
	}
	
	public PostVo getPostByNo(Long no) {
		return postRepository.findPostByNo(no);
	}
	
	public void delete(Long no) {
		postRepository.deleteByNo(no);
	}
}
