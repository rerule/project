package com.jsp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsp.domain.Article;
import com.jsp.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/articleItems")
	@ResponseBody
	public List<Article> articles(HttpSession httpSession){
		Long user_id = (Long)httpSession.getAttribute("user_id");
		List<Article> articles = articleService._selectArticles();
		return articles;
	}
}
