package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.BookNotFoundException;
import com.cognizant.model.BookModel;
import com.cognizant.repository.BookRepository;

@RestController
@RequestMapping("api")
public class BookController {
	@Autowired
	private BookRepository bookRepo;

	@SuppressWarnings("rawtypes")
	@GetMapping("books/detail")
	public List getBookDetails() {
		return bookRepo.findAll();
	}

	@PostMapping("books/add")
	public BookModel addNewBook(@RequestBody BookModel newBook) {
		return bookRepo.save(newBook);
	}

	@GetMapping("books/{id}")
	public BookModel findByBookId(@PathVariable Long id) {
		BookModel book = bookRepo.findById(id);
		if (book == null) {
			throw new BookNotFoundException(id);
		}
		return book;

	}

	@PutMapping("books/{id}")
	public BookModel updateBookModel(@RequestBody BookModel newBook, @PathVariable Long id) {
		BookModel bookData = bookRepo.findById(id);
		if (bookData != null) {
			BookModel book = new BookModel();
			book.setId(newBook.getId());
			book.setTitle(newBook.getTitle());
			book.setAuthor(newBook.getAuthor());
			book.setPrice(newBook.getPrice());
			book.setLanguage(newBook.getLanguage());
			return bookRepo.save(book);
		} else {
			throw new BookNotFoundException(id);
		}
	}

	@DeleteMapping("books/{id}")
	void deleteBook(@PathVariable Long id) {
		bookRepo.deleteById(id);
	}

}
