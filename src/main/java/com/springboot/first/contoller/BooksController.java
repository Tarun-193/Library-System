package com.springboot.first.contoller;

import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.first.dto.BookInfo;
import com.springboot.first.model.Books;
import com.springboot.first.service.BooksService;


@RestController
@RequestMapping("/api/books")
public class BooksController {

	private BooksService booksService;

	public BooksController(BooksService booksService) {
		super();
		this.booksService = booksService;
	}

	@PostMapping
	public ResponseEntity<?> saveBooks(@RequestParam("bookName") String bookName, @RequestParam("author") String author,
			@RequestParam("noOfBooks") int noOfBooks, @RequestParam("cost") double cost,
			@RequestParam("rack") String rack, @RequestParam("bookImage") MultipartFile bookImage) throws IOException {
		try {
			Books books = new Books();
			books.setBookName(bookName);
			books.setAuthor(author);
			books.setNoOfBooks(noOfBooks);
			books.setCost(cost);
			books.setRack(rack);

			Path tempfile = Files.createTempFile("bookImage", ".jpg");
			Files.write(tempfile, bookImage.getBytes());
			books.setBookImage(tempfile.toString());

			booksService.saveBooks(books);

			return new ResponseEntity<Books>(books, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
					.body("{\"error\": \"" + "BookName already exists" + "\"}");
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateBooks(@PathVariable("id") String id,
			@RequestBody Map<String, String> requestBody) {
		int activity = Integer.parseInt(requestBody.get("activity"));
		
		int num_books = Integer.parseInt(requestBody.get("num_books"));
		
		String reason = (String) requestBody.get("resonForUpdate");
		
		if(activity==1 || activity==2) {
		return new ResponseEntity<Books>(booksService.updateBooks(id, activity, num_books, reason), HttpStatus.OK);
	}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
					.body("{\"error\": \"" + "Enter valid activity" + "\"}");
		}
		}

	@GetMapping("/findStockById/{id}")
	public int findStockById(@PathVariable("id") String bookid) {
		return booksService.findStockByBookId(bookid);
	}

	@GetMapping("/bookinfo/{id}")
	public List<BookInfo> findBookInfoById(@PathVariable("id") String bookId) {
		return booksService.findBookInfo(bookId);
	}

}
