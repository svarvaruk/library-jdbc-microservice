package com.library.jdbc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.jdbc.dao.LibraryBookDAO;
import com.library.jdbc.exception.BookAlreadyExistException;
import com.library.jdbc.exception.BookNotFoundException;
import com.library.jdbc.service.pojo.Book;

@Service
public class BookService {
	public static final Logger log =  LoggerFactory.getLogger(BookService.class);
	
	LibraryBookDAO bookRepository;	
	
	public BookService(LibraryBookDAO bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book getBook(String id) {
		return bookRepository.get(id);
	}
	
	public void deleteBook(String id) {
		if (bookRepository.isBookExist(id)) {		
			bookRepository.delete(id);
		} else {
			throw new BookNotFoundException(id);
		}
	}
	
	public Book createBook(Book book) {
		if (!bookRepository.isBookExist(book.getBookId())) {
			book = bookRepository.create(book);
		} else {
			throw new BookAlreadyExistException(book.getBookId());
		}
		return book;
	}
	
	public Book updateBook(Book book) {
		if (bookRepository.isBookExist(book.getBookId())) {
			book = bookRepository.update(book);
		} else {
			throw new BookNotFoundException(book.getBookId());
		}
		return book;
	}
	
	public List<Book> getBooks(Pageable pageable) {
		return bookRepository.getBooks(pageable);
	}
	
	public List<Book> getBooksByTitle(String title, Pageable pageable) {
		return bookRepository.getBooksByParameter("title", title, pageable);
	}
	
	public List<Book> getBooksByIsbn(String isbn, Pageable pageable) {
		return bookRepository.getBooksByParameter("isbn", isbn, pageable);
	}	
	
	public List<Book> getBooksByAuthorLastName(String authorLastName, Pageable pageable) {
		return bookRepository.getBooksByParameter("author", authorLastName, pageable);
	}	
}
