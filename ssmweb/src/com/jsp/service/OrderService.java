package com.jsp.service;

import java.util.List;

import com.jsp.domain.Article;
import com.jsp.domain.Item;
import com.jsp.domain.Order;

public interface OrderService {

	public List<Order> _selectOrderListByUserId(Long user_id);

	public void _insertOrder(Long user_id,List<Item> itemList);

	public void _deleteOrderByOrderId(Long id);

	public List<Article> _selectShoppedArticleByOrderId(Long order_id);

}
