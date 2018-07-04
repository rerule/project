package com.jsp.dao;

import java.util.List;

import com.jsp.domain.Item;
import com.jsp.domain.Order;

public interface OrderMapper {

	public List<Order> selectOrderListByUserId(Long user_id);
	
	public Order selectOrderByOrderId(Long order_id);

	public void saveOrder(Order order);

	public Long selectIdByOrderCode(String uuid);
	
	public void saveMiddle(Item item);

	public void deleteOrderById(Long id);

	public void deleteItemByOrderId(Long id);

}
