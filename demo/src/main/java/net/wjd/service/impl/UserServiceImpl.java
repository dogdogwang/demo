package net.wjd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wjd.dao.TUserMapper;
import net.wjd.model.User;
import net.wjd.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private TUserMapper userDao;

	public User getUserById(String id) {
		return this.userDao.selectByPrimaryKey(id);
	}

}
