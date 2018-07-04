package com.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<Book> getBookList() {
		// TODO Auto-generated method stub
		return bookMapper.getBookList();
	}

}
