package com.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.domain.Article;
import com.jsp.domain.Item;
import com.jsp.domain.Order;
import com.jsp.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	//某用户订单记录
	@RequestMapping("/orderList")
	public String orderList(Model model,HttpSession httpSession){
		if (httpSession.getAttribute("loginname")!=null) {
			String loginname = httpSession.getAttribute("loginname").toString();
			String password = httpSession.getAttribute("password").toString();
			Long user_id = Long.parseLong(httpSession.getAttribute("user_id").toString());
			List<Order> orderList = orderService._selectOrderListByUserId(user_id);
			model.addAttribute("orderList", orderList);
			return "/order/orderList";
		}else {
			return "redirect:/";
		}
		
	}
	//安全退出
	@RequestMapping("/exit")
	public String exit(HttpSession httpSession){
		httpSession.invalidate();
		return "redirect:/";
	}
	//为用户删除订单
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, String> deleteOrder(@RequestParam("jsonString") String jsonString,HttpSession httpSession){
		JSONObject jsonObject =JSONObject.fromObject(jsonString);
		Long id = Long.parseLong(jsonObject.get("order_id").toString());
		String loginname = httpSession.getAttribute("loginname").toString();
		orderService._deleteOrderByOrderId(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", loginname+"删除了一个订单");
		return map;
//		System.out.println(jsonObject.get("order_id"));
		/*Map<String, String> jsonMap = objectMapper.readValue(jsonObject, Map.class);
		Set<String> keys = jsonMap.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			
			System.out.println(iterator.next()+"----1--------1----1-----1----");
		}*/
		
	}
	
	//为用户新增一个订单
	@RequestMapping("/addNewOrder")
	@ResponseBody
	public Map<String, String> addNewOrder(@RequestBody List<Item> itemList,HttpSession httpSession){
		Long user_id = Long.parseLong(httpSession.getAttribute("user_id").toString());
		String loginname = httpSession.getAttribute("loginname").toString();
		Map<String, String> map = new HashMap<String, String>();
		orderService._insertOrder(user_id,itemList);
		map.put("status", loginname+"增加了一个订单");
		return map;
	}
	
	
	//为用户查看订单
	@RequestMapping("/info")
	public String orderInfo(@RequestParam("jsonObj") String jsonStr,Model model){
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Long order_id = Long.parseLong(jsonObject.get("order_id").toString());
		List<Article> articleList=orderService._selectShoppedArticleByOrderId(order_id);
		model.addAttribute("articleList", articleList);
		
		return "/order/orderDetail";
	}
}

