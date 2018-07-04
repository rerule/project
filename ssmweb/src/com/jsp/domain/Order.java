package com.jsp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	private Long id;  // 订单id，主键
	private String code;  // 订单编号
	private Double total; // 订单总金额
	private Date order_date;//订单日期时间
	// 订单和用户是多对一的关系，即一个订单只属于一个用户
	private User user;
		
	// 订单和商品是多对多的关系，即一个订单可以包含多种商品
	private List<Article> articles;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	
	
	
	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", code=" + code + ", total=" + total + "]";
	}
	
	
}
