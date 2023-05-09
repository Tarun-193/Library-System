package com.springboot.first.service.implementation;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.first.dto.UserBookHistory;
import com.springboot.first.dto.UserTakenBooks;
import com.springboot.first.exception.ResoureNotFoundException;

import com.springboot.first.model.BookHistory;
import com.springboot.first.model.Books;
import com.springboot.first.repository.BooksHistoryRepository;
import com.springboot.first.repository.BooksRepository;
import com.springboot.first.repository.FinesRepository;
import com.springboot.first.service.BooksHistoryService;

@Service
public class BookHistoryServiceImplementation implements BooksHistoryService {

	private FinesRepository finesRepository;

	private BooksHistoryRepository booksHistoryRepository;

	private BooksRepository booksRepository;

	private Books books = new Books();

	public BookHistoryServiceImplementation(FinesRepository finesRepository,
			BooksHistoryRepository booksHistoryRepository, BooksRepository booksRepository) {
		super();
		this.finesRepository = finesRepository;
		this.booksHistoryRepository = booksHistoryRepository;
		this.booksRepository = booksRepository;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public BooksRepository getBooksRepository() {
		return booksRepository;
	}

	public void setBooksRepository(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}

	public FinesRepository getFinesRepository() {
		return finesRepository;
	}

	public void setFinesRepository(FinesRepository finesRepository) {
		this.finesRepository = finesRepository;
	}

	// issue book
	@Override
	public void issueBookHistory(BookHistory bookHistory) {
		booksHistoryRepository.generateRandomAlphanumeric(bookHistory);
		booksHistoryRepository.returnAndFine(bookHistory);

		String bookId = bookHistory.getBookId();
		Books existingBooks = booksRepository.findById(bookId)
				.orElseThrow(() -> new ResoureNotFoundException("book", "id", bookId));
		int findStockById = existingBooks.getNoOfBooks();
		existingBooks.setNoOfBooks(findStockById - 1);
		
		booksRepository.save(existingBooks);
		booksHistoryRepository.save(bookHistory);
	}

	// find book history by id
	@Override
	public List<UserBookHistory> findBookHistoryById(String bookId) {
		return booksHistoryRepository.findUsersByBookId(bookId);
	}

	// return book
	@Override
	public double returnBookHistory(String bookingId, String returnDate) {
		BookHistory existingBookHistory = booksHistoryRepository.findById(bookingId)
				.orElseThrow(() -> new ResoureNotFoundException("BookHistory", "BookingId", bookingId));

		double fineAmount = calculateFine(bookingId, returnDate);
		existingBookHistory.setFineAmount(fineAmount);

		String bookId = existingBookHistory.getBookId();
		Books existingBooks = booksRepository.findById(bookId)
				.orElseThrow(() -> new ResoureNotFoundException("book", "id", bookId));
		int findStockById = existingBooks.getNoOfBooks();
		existingBooks.setNoOfBooks(findStockById + 1);
		booksRepository.save(existingBooks);
		booksHistoryRepository.save(existingBookHistory);

		return fineAmount;
	}

	// method to calculate days
	public long calculateDays(String bookingId, String returnDate) {
		String ExpiryDate = booksHistoryRepository.expiryDate(bookingId);
		BookHistory existingBookHistory = booksHistoryRepository.findById(bookingId)
				.orElseThrow(() -> new ResoureNotFoundException("BookHistory", "BookingId", bookingId));
		existingBookHistory.setReturnDate(returnDate);
		LocalDate expiry = LocalDate.parse(ExpiryDate, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate returning = LocalDate.parse(returnDate, DateTimeFormatter.ISO_LOCAL_DATE);
		long days = ChronoUnit.DAYS.between(expiry, returning);
		return days;
	}

	// calculate fine
	@Override
	public double calculateFine(String bookingId, String returnDate) {
		long days = calculateDays(bookingId, returnDate);
		double fineAmount = finesRepository.calcFine(days);
		return fineAmount;
	}

	@Override
	public double findFine(String bookingId) {
		return booksHistoryRepository.findFine(bookingId);

	}

	@Override
	public List<UserTakenBooks> findUserTakenBooks(long userId) {
		return booksHistoryRepository.findUserTakenBooks(userId);
	}

}
