package net.wjd.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wjd.mapper.UserMapper;
import net.wjd.model.User;
import net.wjd.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userDao;

	public User getUserById(String id) {
		return this.userDao.selectByPrimaryKey(id);
	}

}
