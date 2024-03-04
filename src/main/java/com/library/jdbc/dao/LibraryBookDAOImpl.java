package com.library.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.library.jdbc.exception.BookNotFoundException;
import com.library.jdbc.exception.UnsupportedParameterException;
import com.library.jdbc.service.pojo.Author;
import com.library.jdbc.service.pojo.Book;
import com.library.jdbc.service.pojo.CustomInfo;
import com.library.jdbc.utils.Constants;
import com.library.jdbc.utils.LibraryHelper;

import org.postgresql.util.PSQLException;

@Repository
public class LibraryBookDAOImpl implements LibraryBookDAO {
	
	public static final Logger log =  LoggerFactory.getLogger(LibraryBookDAOImpl.class);
	
	@Autowired
	@Qualifier("postgreJdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	public static final RowMapper<Book> bookMapper = BeanPropertyRowMapper.newInstance(Book.class);
	public static final RowMapper<Author> authorMapper = BeanPropertyRowMapper.newInstance(Author.class);
	public static final RowMapper<CustomInfo> customInfoMapper = BeanPropertyRowMapper.newInstance(CustomInfo.class);
	
	@Override
	public Book get(String id) {
        Book book = jdbcTemplate.query(Constants.QUERY_GET_BOOK, new ResultSetExtractor<Book>() {
            public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
                Book book = null;
                int row = 0;
                while (rs.next()) {
                    if (book == null) {
                        book = bookMapper.mapRow(rs, row);
                    }
                    if (book != null) {
                    	LibraryHelper.processAuthor(book, authorMapper.mapRow(rs, row), rs);
                    	LibraryHelper.processKeywords(book, rs);
                    	LibraryHelper.processGenres(book, rs);                    	
                    	LibraryHelper.processCustomInfo(book, customInfoMapper.mapRow(rs, row), rs);                   	
                    }
                    row++;
                }
                return book;
            }

        }, id);
        if (book == null) {
        	throw new BookNotFoundException(id);
        }
        return book;
	}
	
	@Override
	@Transactional	
	public Book create(Book book) {
		jdbcTemplate.update(Constants.QUERY_INSERT_BOOK,
			new Object[] {
					book.getBookId(),
					book.getAddedDate(),
					book.getAllowRead(),
					book.getYear(),
					book.getAnnotation(),
					book.getChars(),
					book.getCover(),
					book.getFile(),
					book.getFileId(),
					book.getIntId(),
					book.getIsbn(),
					book.getLang(),
					book.getSrcLang(),
					book.getLastReleaseDate(),
					book.getLvl(),
					book.getOnSale(),
					book.getPrice(),
					book.getProgramUsed(),
					book.getVersion(),
					book.getHistory(),						
					book.getPublishCity(),
					book.getPublishYear(),
					book.getPublisherName(),
					book.getSequenceNameTitleInfo(),
					book.getSequenceNumberTitleInfo(),
					book.getSequenceNamePublishInfo(),
					book.getSequenceNumberPublishInfo(),
					book.getShowPreview(),
					book.getSrcOcr(),
					book.getSrcUrl(),
					book.getTitle(),
					book.getTranslatorFirstName(),
					book.getTranslatorId(),
					book.getTranslatorLastName(),
					book.getTranslatorMiddleName(),
					book.getTranslatorUrl(),
					book.getType()
	 			}
		);
        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_BOOK_AUTHOR, 
        			book.getAuthors(), 
        			book.getAuthors().size(),
        	        (PreparedStatement ps, Author author) -> {
        	            ps.setString(1, book.getBookId());
        	            ps.setString(2, author.getAuthorId());
        	          }                			
        			);
        }        		
        if (book.getCustomInfo() != null && !book.getCustomInfo().isEmpty()) {
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_CUSTOM_INFO, 
        			book.getCustomInfo(), 
        			book.getCustomInfo().size(),
        	        (PreparedStatement ps, CustomInfo customInfo) -> {
        	            ps.setString(1, customInfo.getCustomInfoValue());
        	            ps.setString(2, customInfo.getInfoType());
        	            ps.setString(3, book.getBookId());
        	          }                			
        			);
        }
        if (book.getGenres() != null && !book.getGenres().isEmpty()) {
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_GENRES, 
        			book.getGenres(), 
        			book.getGenres().size(),
        	        (PreparedStatement ps, String genre) -> {
        	            ps.setString(1, book.getBookId());
        	            ps.setString(2, genre);
        	          }                			
        			);                	
        }
        if (book.getKeywords() != null && !book.getKeywords().isEmpty()) {
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_KEYWORDS, 
        			book.getKeywords(), 
        			book.getKeywords().size(),
        	        (PreparedStatement ps, String keyword) -> {
        	            ps.setString(1, book.getBookId());
        	            ps.setString(2, keyword);
        	          }                			
        			);                	
        }                
              		
		return get(book.getBookId());
	}
	
	/*
	public Book create(Book book) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.executeWithoutResult(status -> {
			jdbcTemplate.update(Constants.QUERY_INSERT_BOOK,
    				new Object[] {
    						book.getBookId(),
    						book.getAddedDate(),
    						book.getAllowRead(),
    						book.getYear(),
    						book.getAnnotation(),
    						book.getChars(),
    						book.getCover(),
    						book.getFile(),
    						book.getFileId(),
    						book.getIntId(),
    						book.getIsbn(),
    						book.getLang(),
    						book.getSrcLang(),
    						book.getLastReleaseDate(),
    						book.getLvl(),
    						book.getOnSale(),
    						book.getPrice(),
    						book.getProgramUsed(),
    						book.getVersion(),
    						book.getHistory(),						
    						book.getPublishCity(),
    						book.getPublishYear(),
    						book.getPublisherName(),
    						book.getSequenceNameTitleInfo(),
    						book.getSequenceNumberTitleInfo(),
    						book.getSequenceNamePublishInfo(),
    						book.getSequenceNumberPublishInfo(),
    						book.getShowPreview(),
    						book.getSrcOcr(),
    						book.getSrcUrl(),
    						book.getTitle(),
    						book.getTranslatorFirstName(),
    						book.getTranslatorId(),
    						book.getTranslatorLastName(),
    						book.getTranslatorMiddleName(),
    						book.getTranslatorUrl(),
    						book.getType()
    		 			}
    		);
            if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
            	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_BOOK_AUTHOR, 
            			book.getAuthors(), 
            			book.getAuthors().size(),
            	        (PreparedStatement ps, Author author) -> {
            	            ps.setString(1, book.getBookId());
            	            ps.setString(2, author.getAuthorId());
            	          }                			
            			);
            }        		
            if (book.getCustomInfo() != null && !book.getCustomInfo().isEmpty()) {
            	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_CUSTOM_INFO, 
            			book.getCustomInfo(), 
            			book.getCustomInfo().size(),
            	        (PreparedStatement ps, CustomInfo customInfo) -> {
            	            ps.setString(1, customInfo.getCustomInfoValue());
            	            ps.setString(2, customInfo.getInfoType());
            	            ps.setString(3, book.getBookId());
            	          }                			
            			);
            }
            if (book.getGenres() != null && !book.getGenres().isEmpty()) {
            	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_GENRES, 
            			book.getGenres(), 
            			book.getGenres().size(),
            	        (PreparedStatement ps, String genre) -> {
            	            ps.setString(1, book.getBookId());
            	            ps.setString(2, genre);
            	          }                			
            			);                	
            }
            if (book.getKeywords() != null && !book.getKeywords().isEmpty()) {
            	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_KEYWORDS, 
            			book.getKeywords(), 
            			book.getKeywords().size(),
            	        (PreparedStatement ps, String keyword) -> {
            	            ps.setString(1, book.getBookId());
            	            ps.setString(2, keyword);
            	          }                			
            			);                	
            }   
        });	
        return get(book.getBookId());
	}
*/
	@Override
	@Transactional	
	public Book update(Book book) {
		jdbcTemplate.update(Constants.QUERY_UPDATE_BOOK,
				new Object[] {
						book.getAddedDate(),
						book.getAllowRead(),
						book.getYear(),
						book.getAnnotation(),
						book.getChars(),
						book.getCover(),
						book.getFile(),
						book.getFileId(),
						book.getIntId(),
						book.getIsbn(),
						book.getLang(),
						book.getSrcLang(),
						book.getLastReleaseDate(),
						book.getLvl(),
						book.getOnSale(),
						book.getPrice(),
						book.getProgramUsed(),
						book.getVersion(),
						book.getHistory(),						
						book.getPublishCity(),
						book.getPublishYear(),
						book.getPublisherName(),
						book.getSequenceNameTitleInfo(),
						book.getSequenceNumberTitleInfo(),
						book.getSequenceNamePublishInfo(),
						book.getSequenceNumberPublishInfo(),
						book.getShowPreview(),
						book.getSrcOcr(),
						book.getSrcUrl(),
						book.getTitle(),
						book.getTranslatorFirstName(),
						book.getTranslatorId(),
						book.getTranslatorLastName(),
						book.getTranslatorMiddleName(),
						book.getTranslatorUrl(),
						book.getType(),
						book.getBookId()
		 			}
		);
		jdbcTemplate.update(Constants.QUERY_DELETE_BOOK_AUTHOR, book.getBookId());
        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {                	
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_BOOK_AUTHOR, 
        			book.getAuthors(), 
        			book.getAuthors().size(),
        	        (PreparedStatement ps, Author author) -> {
        	            ps.setString(1, book.getBookId());
        	            ps.setString(2, author.getAuthorId());
        	          }                			
        			);
        }     
        jdbcTemplate.update(Constants.QUERY_DELETE_CUSTOM_INFO, book.getBookId());
        if (book.getCustomInfo() != null && !book.getCustomInfo().isEmpty()) {                	
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_CUSTOM_INFO, 
        			book.getCustomInfo(), 
        			book.getCustomInfo().size(),
        	        (PreparedStatement ps, CustomInfo customInfo) -> {
        	            ps.setString(1, customInfo.getCustomInfoValue());
        	            ps.setString(2, customInfo.getInfoType());
        	            ps.setString(3, book.getBookId());
        	          }                			
        			);
        }
        int[] rr = new int[4];
        int size = rr.length;
        jdbcTemplate.update(Constants.QUERY_DELETE_GENRES, book.getBookId());
        if (book.getGenres() != null && !book.getGenres().isEmpty()) {
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_GENRES, 
        			book.getGenres(), 
        			book.getGenres().size(),
        	        (PreparedStatement ps, String genre) -> {
        	            ps.setString(1, book.getBookId());
        	            ps.setString(2, genre);
        	          }                			
        			);                	
        }
        jdbcTemplate.update(Constants.QUERY_DELETE_KEYWORDS, book.getBookId());
        if (book.getKeywords() != null && !book.getKeywords().isEmpty()) {
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_KEYWORDS, 
        			book.getKeywords(), 
        			book.getKeywords().size(),
        	        (PreparedStatement ps, String keyword) -> {
        	            ps.setString(1, book.getBookId());
        	            ps.setString(2, keyword);
        	          }                			
        			);                	
        }                

        return get(book.getBookId());
	}

	@Override
	public void delete(String id) {
		jdbcTemplate.update(Constants.QUERY_DELETE_BOOK, id);		
	}

	@Override
	public List<Book> getBooks(Pageable pageable) {
		return jdbcTemplate.query(Constants.QUERY_GET_AllBOOKS, new ResultSetExtractor<List<Book>>() {
			public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Book> booksList = new ArrayList<>();
				String bookId = null;
				Book currentBook = null;
				int currentRow = 0;
				int currentItem = 0;
				while(rs.next()) {
					if (currentBook == null || !bookId.equals(rs.getString("bookid"))) {
						bookId = rs.getString("bookid");
						currentBook = bookMapper.mapRow(rs, currentRow++);
						currentItem = 0;
						booksList.add(currentBook);
					}
                	LibraryHelper.processAuthor(currentBook, authorMapper.mapRow(rs, currentItem), rs);
                	LibraryHelper.processKeywords(currentBook, rs);
                	LibraryHelper.processGenres(currentBook, rs);                    	
                	LibraryHelper.processCustomInfo(currentBook, customInfoMapper.mapRow(rs, currentItem), rs);
                	currentItem++;
				}			
				return booksList;
			}
		}, pageable.getPageSize(), pageable.getPageNumber());
	}

	@Override
	public List<Book> getBooksByParameter(String parameterName, String parameterValue, Pageable pageable) {
		String query = null;
		switch (parameterName) {
			case ("title") : {
				query = Constants.QUERY_GET_BOOKS_BY_TITLE;
				break;
			}
			case ("isbn") : {
				query = Constants.QUERY_GET_BOOKS_BY_ISBN;
				break;
			}
			case ("author") : {
				query = Constants.QUERY_GET_BOOKS_BY_AUTHOR;
				break;
			}
			default : {
				log.info("Uknown or invalid parameter specified, parameter name: {}, parameter value: {}", parameterName, parameterValue);
				throw new UnsupportedParameterException("Uknown or invalid parameter specified, parameter name: " + parameterValue + " parameter value: " + parameterValue );
			}			
		}
		return jdbcTemplate.query(query, new ResultSetExtractor<List<Book>>() {
			public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Book> booksList = new ArrayList<>();
				String bookId = null;
				Book currentBook = null;
				int currentRow = 0;
				int currentItem = 0;
				while(rs.next()) {
					if (currentBook == null || !bookId.equals(rs.getString("bookid"))) {
						bookId = rs.getString("bookid");
						currentBook = bookMapper.mapRow(rs, currentRow++);
						currentItem = 0;
						booksList.add(currentBook);
					}
                	LibraryHelper.processAuthor(currentBook, authorMapper.mapRow(rs, currentItem), rs);
                	LibraryHelper.processKeywords(currentBook, rs);
                	LibraryHelper.processGenres(currentBook, rs);                    	
                	LibraryHelper.processCustomInfo(currentBook, customInfoMapper.mapRow(rs, currentItem), rs);
                	currentItem++;
				}			
				return booksList;
			}
		}, parameterValue, pageable.getPageSize(), pageable.getPageNumber());
	}

	@Override
	public boolean isBookExist(String id) {	
		return jdbcTemplate.queryForObject(Constants.QUERY_CHECK_BOOK_EXIST, Boolean.class, id);
	}
	/*
	@Override
	@Transactional
	public Book create(Book book) {
		insertBook(book);
		insertAuthors(book);
		return book;
	}	
	
	private void insertBook(Book book) {
		jdbcTemplate.update(Constants.QUERY_INSERT_BOOK,
				new Object[] {
						book.getBookId(),
						book.getAddedDate(),
						book.getAllowRead(),
						book.getYear(),
						book.getAnnotation(),
						book.getChars(),
						book.getCover(),
						book.getFile(),
						book.getFileId(),
						book.getIntId(),
						book.getIsbn(),
						book.getLang(),
						book.getSrcLang(),
						book.getLastReleaseDate(),
						book.getLvl(),
						book.getOnSale(),
						book.getPrice(),
						book.getProgramUsed(),
						book.getVersion(),
						book.getHistory(),						
						book.getPublishCity(),
						book.getPublishYear(),
						book.getPublisherName(),
						book.getSequenceNameTitleInfo(),
						book.getSequenceNumberTitleInfo(),
						book.getSequenceNamePublishInfo(),
						book.getSequenceNumberPublishInfo(),
						book.getShowPreview(),
						book.getSrcOcr(),
						book.getSrcUrl(),
						book.getTitle(),
						book.getTranslatorFirstName(),
						book.getTranslatorId(),
						book.getTranslatorLastName(),
						book.getTranslatorMiddleName(),
						book.getTranslatorUrl(),
						book.getType()
		 			}
			);		
	}
	
	private void insertAuthors(Book book) {

        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
        	jdbcTemplate.batchUpdate(Constants.QUERY_INSERT_BOOK_AUTHOR, 
        			book.getAuthors(), 
        			book.getAuthors().size(),
        	        (PreparedStatement ps, Author author) -> {
        	            ps.setString(1, book.getBookId());
        	            ps.setString(2, author.getAuthorId());
        	          }                			
        			);
        } 		
 
//		throw new RuntimeException("Rollback as we have a Runtime Exception!");
	}	
	*/
}
