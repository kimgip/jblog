package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private PostRepository postRepository;
	
	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public void add(CategoryVo vo) {
		categoryRepository.insert(vo);
	}
	
	public List<CategoryVo> getCategoryList(String id){
		return categoryRepository.findCategoryList(id);
	}
	
	@Transactional
	public void delete(Long no, String id) {
		// post 삭제 전에 no, id 확인이 필요해보임..
		postRepository.deleteByCategoryNo(no);
		categoryRepository.deleteByNoAndId(no, id);
	}
}
