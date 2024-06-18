package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {
	private SqlSession sqlSession;

	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(PostVo vo) {
		return sqlSession.insert("post.insert", vo);
	}
	
	public List<PostVo> findPostList(Long no){
		return sqlSession.selectList("post.findPostList", no);
	}

	public PostVo findPostByNoAndCategoryNo(Long no, Long categoryNo) {
		return sqlSession.selectOne("post.findPostByNoAndCategoryNo", 
				Map.of(	"no", no,
						"categoryNo", categoryNo));
	}

	public int deleteByCategoryNo(Long no) {
		return sqlSession.delete("post.deleteByCategoryNo", no);
	}

	public int deleteByNo(Long no) {
		return sqlSession.delete("post.deleteByNo", no);
	}
}
