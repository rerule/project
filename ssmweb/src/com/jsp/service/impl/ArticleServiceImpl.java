package com.jsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.dao.ArticleMapper;
import com.jsp.domain.Article;
import com.jsp.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Override
	
	public List<Article> _selectArticles() {
		// TODO Auto-generated method stub
		return articleMapper.selectArticles();
	}

}
