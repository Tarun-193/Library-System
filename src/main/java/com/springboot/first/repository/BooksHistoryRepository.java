package com.springboot.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.*;

import com.springboot.first.dto.UserBookHistory;
import com.springboot.first.dto.UserTakenBooks;
import com.springboot.first.model.BookHistory;

public interface BooksHistoryRepository extends JpaRepository<BookHistory, String> {

	
	//Generate random BookingId 
    static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static final int RANDOM_STRING_LENGTH = 5;

    default void generateRandomAlphanumeric(BookHistory bookHistory) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            builder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        bookHistory.setBookingId(builder.toString());
    }
    
    //Set default value for Returning Date and Fine in BookHistory 
    default void returnAndFine(BookHistory bookHistory) {
    	bookHistory.setReturnDate("");
    	bookHistory.setFineAmount(0);
    }
    
    
    //Query to find users by bookId
    @Query("select new com.springboot.first.dto.UserBookHistory(u.userName, bh.issueDate, bh.returnDate, bh.bookingId) from Users u INNER JOIN BookHistory bh ON u.userId=bh.userId WHERE bh.bookId=:bookId")
    List<UserBookHistory> findUsersByBookId(String bookId);
        
    //Query to find expiryDate
    @Query("select expiryDate from BookHistory where bookingId=:bookingId")
    String expiryDate(String bookingId);
    
    //Query to find return date
    @Query("select returnDate from BookHistory where bookingId=:bookingId")
    String returnDate(String bookingId);
    
    //Query to find fine using Booking id
    @Query("select fineAmount from BookHistory where bookingId=:bookingId")
    double findFine(String bookingId);
    
    @Query("select new com.springboot.first.dto.UserTakenBooks(bh.bookId,b.bookName) from BookHistory bh INNER JOIN  Books b ON bh.bookId=b.bookId WHERE bh.userId=:userId")
    List<UserTakenBooks> findUserTakenBooks(long userId);
}
