package com.jsp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.domain.User;
import com.jsp.interceptors.SameUrlToken;
import com.jsp.service.UserService;
import com.test.Book;
import com.test.BookService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute User user,HttpSession httpSession){
		String loginname = user.getLoginname().trim();
		String password = user.getPassword().trim();
		
		
			boolean comparePassword = userService.comparePassword_SelectPasswordByLoginName(loginname,password);
			if (comparePassword) {
				Long user_id = userService._selectIdByLoginName(loginname);
				httpSession.setAttribute("user_id", user_id);
				httpSession.setAttribute("loginname", loginname);
				httpSession.setAttribute("password", password);
				return "redirect:/order/orderList";
			}else {
				return "redirect:/";
			}
		
		
		
	}
	
	@RequestMapping("/toRegister")
	public String toRegister(){
		return "/user/register";
	}
	
	@SameUrlToken
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@ModelAttribute User user,Model model){
		model.addAttribute("user", user);
		userService._saveUser(user);
		return "/book/bookList";
	}
}
