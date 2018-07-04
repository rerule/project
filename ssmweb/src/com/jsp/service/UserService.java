package com.jsp.service;

import com.jsp.domain.User;

public interface UserService {
	public User _selectOne(Integer id);

	public boolean comparePassword_SelectPasswordByLoginName(String loginname,String password);

	public void _saveUser(User user);

	public Long _selectIdByLoginName(String loginname);
}
