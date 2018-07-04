package com.jsp.dao;

import com.jsp.domain.User;



public interface UserMapper {
//	public User selectOne(Integer id);

	public User selectPasswordByUserName(String loginname);

	public void saveUser(User user);

	public Long selectIdByLoginName(String loginname);

	public User selectUserByUserId(Long user_id);
}
