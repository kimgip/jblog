package com.poscodx.jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.security.UserDetailsImpl;
import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	private SqlSession sqlSession;

	public UserRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo findByIdAndPasssword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPasssword",
					Map.of("id", id, "password", password));
	}
	
	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}
	
	public UserDetailsImpl findById2(String id) {
		return sqlSession.selectOne("user.findById2", id);
	}

}
