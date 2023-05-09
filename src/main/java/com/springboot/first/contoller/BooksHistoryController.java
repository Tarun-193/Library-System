package com.springboot.first.contoller;

import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.first.dto.UserBookHistory;
import com.springboot.first.dto.UserTakenBooks;
import com.springboot.first.model.BookHistory;
import com.springboot.first.model.Books;
import com.springboot.first.model.Users;
import com.springboot.first.repository.BooksRepository;
import com.springboot.first.repository.UsersRepository;
import com.springboot.first.service.BooksHistoryService;

@RestController
@RequestMapping("/api/bookhistory")
public class BooksHistoryController {

	private BooksHistoryService booksHistoryService;
	private UsersRepository usersRepository;
	private BooksRepository booksRepository;

	public BooksHistoryController(BooksHistoryService booksHistoryService, UsersRepository usersRepository,
			BooksRepository booksRepository) {
		super();
		this.booksHistoryService = booksHistoryService;
		this.usersRepository = usersRepository;
		this.booksRepository = booksRepository;
	}

	@PostMapping("/issueBook")
	public ResponseEntity<String> issueBookHistory(@RequestBody BookHistory bookHistory) {

		Optional<Users> existingUsers = usersRepository.findById(bookHistory.getUserId());
		Optional<Books> existingBook = booksRepository.findById(bookHistory.getBookId());
		if (existingUsers.isPresent() && existingBook.isPresent()) {
			booksHistoryService.issueBookHistory(bookHistory);
			return ResponseEntity.status(HttpStatus.CREATED).header("Content-Type", "application/json")
					.body("{\"message\": \"" + "Book issued" + "\"}");

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
				.body("{\"error\": \"" + "userId Or BookId invalid" + "\"}");
	}

	@PutMapping("/returnBook")
	public double returnBookHistory(@RequestBody Map<String, String> requestBody) {
		String bookingId = (String) requestBody.get("bookingId");
		String returnDate = (String) requestBody.get("returnDate");
		return booksHistoryService.returnBookHistory(bookingId, returnDate);
	}

	@GetMapping("/historyByBookId/{id}")
	public List<UserBookHistory> findBookHistoryByBookId(@PathVariable("id") String bookId) {
		return booksHistoryService.findBookHistoryById(bookId);
	}

	@GetMapping("/findFine/{id}")
	public double findeFineByBookingId(@PathVariable("id") String bookingId) {
		return booksHistoryService.findFine(bookingId);
	}

	@GetMapping("/findUserTakenBooks/{id}")
	public List<UserTakenBooks> findUserTakenBooks(@PathVariable("id") long userId) {
		return booksHistoryService.findUserTakenBooks(userId);
	}
}
