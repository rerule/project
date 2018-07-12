package com.exam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.util.Page;
import com.jsp.domain.User;
import com.jsp.service.UserService;

@Controller
@RequestMapping("/page")
public class PageController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/now")
	public String testPage(Model model,HttpServletRequest hsr){
		Object pageObject = model.asMap().get("page");
		Page page = null;
		if (pageObject==null) {
			page = new Page();
			System.err.println(page.getCurrentPage()+"pageObject");
		}else {
			page = (Page)pageObject;
		}
		Integer currentPage=null;
		if (hsr.getParameter("currentPage")==null) {
			currentPage=1;
		}else {
			currentPage =Integer.parseInt(hsr.getParameter("currentPage"));
		}
		
		int totalRows=userService._findUserTotalRows();
		page.setTotalRows(totalRows);
		page.setCurrentPage(currentPage);
		/*System.out.println(page.getNextPage());*/
		List<User> userList = userService._selectAllUser(page);
		model.addAttribute("userList", userList);
		model.addAttribute("page", page);
		return "/page/user";
	}
}
