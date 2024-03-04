package com.library.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.library.jdbc.service.pojo.Author;
import com.library.jdbc.service.pojo.Book;

public class BookRawMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Author author = (new BeanPropertyRowMapper<>(Author.class)).mapRow(rs,rowNum);
		Book book = (new BeanPropertyRowMapper<>(Book.class)).mapRow(rs,rowNum);
		if(book != null) {
			book.getAuthors().add(author);
		}
		return book;
	}

}
