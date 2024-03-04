package com.library.jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.jdbc.exception.AuthorNotFoundException;
import com.library.jdbc.exception.LibraryServiceException;
import com.library.jdbc.service.pojo.Author;
import com.library.jdbc.utils.Constants;

@Repository
public class LibraryAuthorDAOImpl implements LibraryAuthorDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
		
	@Override
	public String create(Author author) {
		jdbcTemplate.update(Constants.QUERY_INSERT_AUTHOR,
				new Object[] { 
						author.getAuthorId(),
						author.getFirstName(),
						author.getLastName(),
						author.getMiddleName(),
						author.getFullNameRodit(),
						author.getExId(),
						author.getUrl(),
						author.getLvl(),
						author.getRelation(),
						author.getSubjectId(),
						author.getNickname(),
						author.getEmail(),
						author.getHomePage(),
						author.getHubId()
		 			}
		);
		return author.getAuthorId();
	}

	@Override
	public Author get(String id) {
		try {
			return jdbcTemplate.queryForObject(Constants.QUERY_GET_AUTHOR, BeanPropertyRowMapper.newInstance(Author.class), id);
		} catch (EmptyResultDataAccessException e) {
			throw new AuthorNotFoundException(id);
		} catch (Exception e) {
			throw new LibraryServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Author update(Author author) {
		jdbcTemplate.update(Constants.QUERY_UPDATE_AUTHOR,
				new Object[] { 
						author.getFirstName(),
						author.getLastName(),
						author.getMiddleName(),
						author.getFullNameRodit(),
						author.getExId(),
						author.getUrl(),
						author.getLvl(),
						author.getRelation(),
						author.getSubjectId(),
						author.getNickname(),
						author.getEmail(),
						author.getHomePage(),
						author.getHubId(),
						author.getAuthorId()
		 			}
		);
		return author;
	}
	
	@Override
	public void delete(String id) {
		jdbcTemplate.update(Constants.QUERY_DELETE_AUTHOR, id);
		
	}

	@Override
	public List<Author> getAuthors(Pageable pageable) {
		return jdbcTemplate.query(Constants.QUERY_GET_ALLAUTHORS,
		        BeanPropertyRowMapper.newInstance(Author.class), 
		        pageable.getPageSize(), 
		        pageable.getPageNumber());
	}
	
	@Override
	public List<Author> getAuthorsByLastName(String lastName, Pageable pageable) {
		return jdbcTemplate.query(Constants.QUERY_GET_AUTHOR_BY_LAST_NAME,
		        BeanPropertyRowMapper.newInstance(Author.class), 
		        lastName, 
		        pageable.getPageSize(), 
		        pageable.getPageNumber());
	}

	@Override
	public List<Author> getAuthorsByFirstName(String firstName, Pageable pageable) {
		return jdbcTemplate.query(Constants.QUERY_GET_AUTHOR_BY_FIRST_NAME,
		        BeanPropertyRowMapper.newInstance(Author.class), 
		        firstName, 
		        pageable.getPageSize(), 
		        pageable.getPageNumber());
	}

	@Override
	public List<Author> getAuthorsByFirstAndLastName(String firstName, String lastName, Pageable pageable) {
		return jdbcTemplate.query(Constants.QUERY_GET_AUTHOR_BY_FIRST_AND_LAST_NAME,
		        BeanPropertyRowMapper.newInstance(Author.class), 
		        firstName, 
		        lastName, 
		        pageable.getPageSize(), 
		        pageable.getPageNumber());
	}

	@Override
	public boolean isAuthorExist(String id) {	
		return jdbcTemplate.queryForObject(Constants.QUERY_CHECK_AUTHOR_EXIST, Boolean.class, id);
	}

}
