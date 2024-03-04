package com.library.jdbc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.library.jdbc.service.pojo.Book;

public interface LibraryBookDAO {
	Book create(Book book);
	Book get(String id);
	boolean isBookExist(String id);
	Book update(Book book);
	void delete(String id);
	List<Book> getBooks(Pageable pageable);
	List<Book> getBooksByParameter(String parameterName, String parameterValue, Pageable pageable);
}
