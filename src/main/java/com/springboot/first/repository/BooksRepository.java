package com.springboot.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.first.dto.BookInfo;
import com.springboot.first.model.Books;
import java.util.*;


public interface BooksRepository extends JpaRepository<Books, String> {
	 static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    static final int RANDOM_STRING_LENGTH = 10;
	    
	    //Generate Random AlphaNumeric String
	    default void generateRandomAlphanumeric(Books books) {
	        Random random = new Random();
	        StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
	            builder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
	        }
	        books.setBookId(builder.toString());
	    }
	    
	    
	    //Add/Remove books
	    default  void addRemove(int activity,int num_books,Books books) {
	    	switch (activity) {
			case 1:
				int add=books.getNoOfBooks()+num_books;
				books.setNoOfBooks(add);
				break;
			case 2:
				int remove=books.getNoOfBooks()-num_books;
				books.setNoOfBooks(remove);
			default:
				break;
			}
	    }
	    
	    
	    //Custom query for finding the no of books in stock
	    @Query("SELECT b.noOfBooks from Books b where bookId=:BookId")
	    int findStockById(@Param("BookId") String BookId );
	    
	    //Custom query to find book info    
	    @Query("select new com.springboot.first.dto.BookInfo(b.noOfBooks, b.rack, b.cost) from Books b WHERE b.bookId=:bookId")
	    List<BookInfo> findBookInfo(String bookId);
	    
	    Optional<Books> findByBookName(String bookName);
}
