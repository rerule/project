package com.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsp.dao.OrderMapper;
import com.jsp.domain.Order;

@Controller
@RequestMapping(value="/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@RequestMapping(value="/getBookList")
	public String getBookList(Model model,HttpServletRequest hsr){
		
		List<Book> bookList = bookService.getBookList();
		for (Book book : bookList) {
			System.out.println(book.getCategory().getName());;
		}
		model.addAttribute("bookList", bookList);
		hsr.setAttribute("test", bookList);
		return "/book/bookList";
	}
	
	@RequestMapping("/test")
	public void test(){
		List<Order> orderList = orderMapper.selectOrderListByUserId(1L);
		System.out.println(orderList.size()+"0110101");
	}
}
