package com.library.jdbc.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.library.jdbc.service.pojo.Author;
import com.library.jdbc.service.pojo.Book;
import com.library.jdbc.service.pojo.CustomInfo;

public class LibraryHelper {
	
	public static void processKeywords(Book book, ResultSet rs) throws SQLException {
    	if(rs.getString("keyword") != null) {
    		String keyword = rs.getString("keyword");
        	if (!book.getKeywords().contains(keyword)) {
        		book.getKeywords().add(keyword);
        	}                    		
    	}		
	}
	
	public static void processGenres(Book book, ResultSet rs) throws SQLException {
    	if(rs.getString("genre") != null) {
    		String genre = rs.getString("genre");
        	if (!book.getGenres().contains(genre)) {
        		book.getGenres().add(genre);
        	}                    		
    	}		
	}	
	
	public static void processAuthor(Book book, Author author, ResultSet rs) throws SQLException {
		if (author != null && author.getAuthorId() != null) {
        	if (!book.getAuthors().contains(author)) {
        		book.getAuthors().add(author);
        	}	
		}
	}
	
	public static void processCustomInfo(Book book, CustomInfo customInfo, ResultSet rs) throws SQLException {
		if (customInfo != null && customInfo.getCustomInfoValue() != null) {
	    	if (!book.getCustomInfo().contains(customInfo)) {
	    		book.getCustomInfo().add(customInfo);
	    	}	
		}
	}
	
	public static Pageable getPageable(Integer pageNumber, 
			Integer pageSize,
			int pageNumberDefaultValue,
			int pageSizeDefaultValue,
			int pageSizeMaxValue
			) {
		return PageRequest.of(getPageNumberValue(pageNumber, pageNumberDefaultValue),
				getPageSizeValue(pageSize, pageSizeDefaultValue, pageSizeMaxValue));
	}
	
	public static int getPageNumberValue(Integer pageNumber, int pageNumberDefaultValue) {
		int topValue = pageNumberDefaultValue;
		if(pageNumber != null) {
			topValue = pageNumber.intValue();
		}
		return topValue;
	}
	
	public static int getPageSizeValue(Integer pageSize, int pageSizeDefaultValue, int pageSizeMaxValue) {
		int pageSizeValue = pageSizeDefaultValue;
		if(pageSize != null) {
			pageSizeValue = pageSize.intValue();
		}
		return pageSizeValue < pageSizeMaxValue ? pageSizeValue : pageSizeMaxValue;
	}	
}
