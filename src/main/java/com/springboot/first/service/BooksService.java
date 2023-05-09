package com.springboot.first.service;

import java.util.List;

import com.springboot.first.dto.BookInfo;
import com.springboot.first.model.Books;

public interface BooksService {
	void saveBooks(Books books);
	Books updateBooks(String bookId,int activity,int num_books, String reason);
	int findStockByBookId(String bookId);
	List<BookInfo> findBookInfo(String bookId);
}
