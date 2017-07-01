package net.wjd.mapper;

import net.wjd.model.User;

public interface UserMapper {
	
	User selectByPrimaryKey(String id);
	
    
}