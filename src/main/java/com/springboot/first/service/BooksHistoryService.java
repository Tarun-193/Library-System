package com.springboot.first.service;

import java.util.List;


import com.springboot.first.dto.UserBookHistory;
import com.springboot.first.dto.UserTakenBooks;
import com.springboot.first.model.BookHistory;

public interface BooksHistoryService {
	void issueBookHistory(BookHistory bookHistory);
	double returnBookHistory(String bookingId,String returnDate);
	List<UserBookHistory> findBookHistoryById(String bookId);
	double calculateFine(String bookingId,String returnDate);
	double findFine(String bookingId);
	List<UserTakenBooks> findUserTakenBooks(long userId);
}
