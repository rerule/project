package com.jsp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.dao.ArticleMapper;
import com.jsp.dao.OrderMapper;
import com.jsp.dao.UserMapper;
import com.jsp.domain.Article;
import com.jsp.domain.Item;
import com.jsp.domain.Order;
import com.jsp.domain.User;
import com.jsp.service.OrderService;
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<Order> _selectOrderListByUserId(Long user_id) {
		System.out.println(orderMapper.selectOrderListByUserId(user_id));
		// TODO Auto-generated method stub
		return orderMapper.selectOrderListByUserId(user_id);
	}

	@Override
	public void _insertOrder(Long user_id, List<Item> itemList) {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Order order = new Order();
		Double total=0.0;
		for (Item item : itemList) {
			Long article_id = item.getArticle_id();
			Integer number = item.getAmount();
			total+=articleMapper.selectPriceByArticleId(article_id)*number;
			//商家商品的数量减少
			articleMapper.updateAmountByArticleId(item);
		}
		order.setCode(uuid);
		order.setOrder_date(new Date());
		order.setTotal(total);
		User user = userMapper.selectUserByUserId(user_id);
		order.setUser(user);
		//增加新订单
		orderMapper.saveOrder(order);
		//中间表的生成
		for (Item item : itemList) {
			Long id = orderMapper.selectIdByOrderCode(uuid);
			item.setOrder_id(id);
			orderMapper.saveMiddle(item);
		}
		
	}

	@Override
	public void _deleteOrderByOrderId(Long id) {
		// TODO Auto-generated method stub
		orderMapper.deleteItemByOrderId(id);
		orderMapper.deleteOrderById(id);
		
	}

	@Override
	public List<Article> _selectShoppedArticleByOrderId(Long order_id) {
		// TODO Auto-generated method stub
		List<Article> list = articleMapper.selectShoppedArticleByOrderId(order_id);
		return list;
	}

}
