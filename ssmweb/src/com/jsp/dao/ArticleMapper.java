package com.jsp.dao;

import java.util.List;

import com.jsp.domain.Article;
import com.jsp.domain.Item;

public interface ArticleMapper {
	public List<Article> selectArticleByOrderId(Long order_id);

	public List<Article> selectArticles();

	public Double selectPriceByArticleId(Long article_id);

	public void updateAmountByArticleId(Item item);

	public List<Article> selectShoppedArticleByOrderId(Long order_id);
}
