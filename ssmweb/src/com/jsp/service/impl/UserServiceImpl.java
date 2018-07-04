package com.jsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.dao.UserMapper;
import com.jsp.domain.User;
import com.jsp.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;


	@Override
	public boolean comparePassword_SelectPasswordByLoginName(String loginname,String password) {
		boolean flag = false;
		User user = userMapper.selectPasswordByUserName(loginname);
		if (user!=null) {
			String pass = user.getPassword();
			if (pass!=null && pass.equals(password)) {
				flag=true;
			}
		}
		
		
		return flag;
	}


	@Override
	public User _selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
//		return userMapper.selectOne(id);
	}


	@Override
	public void _saveUser(User user) {
		// TODO Auto-generated method stub
		userMapper.saveUser(user);
	}


	@Override
	public Long _selectIdByLoginName(String loginname) {
		// TODO Auto-generated method stub
		return userMapper.selectIdByLoginName(loginname);
	}


}
