package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;

	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(CategoryVo vo) {
		return sqlSession.insert("category.insert", vo);
	}
	
	public List<CategoryVo> findCategoryList(String id){
		return sqlSession.selectList("category.findCategoryList", id);
	}
	
	public int deleteByNoAndId(Long no, String id) {
		return sqlSession.delete("category.deleteByNoAndId", Map.of("no", no, "id", id));
	}
}
