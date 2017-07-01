package net.wjd.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.wjd.mapper.UserMapper;
import net.wjd.model.User;
import net.wjd.model.UserExample;

@Repository
public class TUserMapper implements UserMapper {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public int countByExample(UserExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByExample(UserExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<User> selectByExample(UserExample example) {
		// TODO Auto-generated method stub
		return null;
	}

	public User selectByPrimaryKey(String id) {
		User user = sqlSessionFactory.openSession().selectOne("selectByPrimaryKey", id);
		return user;
	}

	public int updateByExampleSelective(User record, UserExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByExample(User record, UserExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
