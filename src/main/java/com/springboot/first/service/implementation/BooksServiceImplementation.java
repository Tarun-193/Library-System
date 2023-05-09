package com.springboot.first.service.implementation;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.first.model.Books;
import com.springboot.first.repository.BooksRepository;
import com.springboot.first.service.BooksService;
import com.springboot.first.dto.BookInfo;
import com.springboot.first.exception.ResoureNotFoundException;

@Service
public class BooksServiceImplementation implements BooksService {

	private BooksRepository booksRepository;

	public BooksServiceImplementation(BooksRepository booksRepository) {
		super();
		this.booksRepository = booksRepository;
	}

	@Override
	public void saveBooks(Books books) {
		Optional<Books> optionalBooks= booksRepository.findByBookName(books.getBookName());
		if(optionalBooks.isPresent()) {
            throw new IllegalArgumentException("Book already exists in database!");
		}else {			
			booksRepository.generateRandomAlphanumeric(books);
			books.setReasonForUpdate("");
			booksRepository.save(books);
		}
	}

	@Override
	public Books updateBooks(String bookId, int activity, int num_books, String reason) {
		Books existingBooks = booksRepository.findById(bookId)
				.orElseThrow(() -> new ResoureNotFoundException("Book", "id", bookId));
		booksRepository.addRemove(activity, num_books, existingBooks);
		existingBooks.setReasonForUpdate(reason);
		booksRepository.save(existingBooks);
		return existingBooks;
	}

	@Override
	public int findStockByBookId(String bookId) {
		return booksRepository.findStockById(bookId);
	}

	@Override
	public List<BookInfo> findBookInfo(String bookId) {
		return booksRepository.findBookInfo(bookId);
	}

}
