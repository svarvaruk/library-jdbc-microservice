package com.library.jdbc.utils;

public class Constants {

	public static final String QUERY_GET_AUTHOR = "SELECT id authorid, first_name, last_name, middle_name, "
			+ "full_name_rodit, ex_id, url, lvl, relation, subject_id, nickname, "
			+ "email, home_page, hub_id "
			+ "FROM authors "
			+ "WHERE id=?";
	
	public static final String QUERY_CHECK_AUTHOR_EXIST = "SELECT EXISTS(SELECT id FROM authors WHERE id = ?)";
			
	public static final String QUERY_INSERT_AUTHOR = "INSERT INTO authors "
			+ "(id, first_name, last_name, middle_name, "
			+ "full_name_rodit, ex_id, url, lvl, relation, subject_id, nickname, "
			+ "email, home_page, hub_id) "
			+ "VALUES "
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String QUERY_DELETE_AUTHOR = "DELETE FROM authors "
			+ "WHERE id=?";	
	
	public static final String QUERY_UPDATE_AUTHOR = "UPDATE authors SET first_name=?, last_name=?, "
			+ "middle_name=?, full_name_rodit=?, "
			+ "ex_id=?, url=?, lvl=?, relation=?, subject_id=?, nickname=?, "
			+ "email=?, home_page=?, hub_id=? "
			+ "WHERE id=?";	
	
	public static final String QUERY_GET_ALLAUTHORS = "SELECT id authorid, first_name, last_name, "
			+ "middle_name, full_name_rodit, "
			+ "ex_id, url, lvl, relation, subject_id, nickname, "
			+ "email, home_page, hub_id "
			+ "FROM authors "
			+ "ORDER BY last_name "
			+ "LIMIT ? "
			+ "OFFSET ?";
	
	public static final String QUERY_GET_AUTHOR_BY_LAST_NAME = "SELECT id authorid, first_name, last_name, "
			+ "middle_name, full_name_rodit, "
			+ "ex_id, url, lvl, relation, subject_id, nickname, "
			+ "email, home_page, hub_id "
			+ "FROM authors "
			+ "WHERE last_name=? "
			+ "ORDER BY last_name "
			+ "LIMIT ? "
			+ "OFFSET ?";			
	
	public static final String QUERY_GET_AUTHOR_BY_FIRST_NAME =  "SELECT id authorid, first_name, last_name, "
			+ "middle_name, full_name_rodit, "
			+ "ex_id, url, lvl, relation, subject_id, nickname, "
			+ "email, home_page, hub_id "
			+ "FROM authors "
			+ "WHERE first_name=? "
			+ "ORDER BY last_name "
			+ "LIMIT ? "
			+ "OFFSET ?";	
	
	public static final String QUERY_GET_AUTHOR_BY_FIRST_AND_LAST_NAME =  "SELECT id authorid, first_name, last_name, "
			+ "middle_name, full_name_rodit, "
			+ "ex_id, url, lvl, relation, subject_id, nickname, "
			+ "email, home_page, hub_id "
			+ "FROM authors "
			+ "WHERE first_name=? AND last_name=? "
			+ "ORDER BY last_name "
			+ "LIMIT ? "
			+ "OFFSET ?";		
	
	public static final String QUERY_GET_BOOK = "SELECT books.id bookid, authors.id authorid,  book_keywords.keywords keyword, book_genres.genres genre, custominfo.id, custominfo.custom_info_value, custominfo.info_type, custominfo.book_id, authors.first_name, authors.last_name, authors.middle_name, authors.full_name_rodit, authors.url, authors.ex_id, authors.hub_id, authors.nickname, authors.email, authors.home_page, authors.lvl, authors.relation, authors.subject_id, books.addeddate, books.allowread, books.year, books.annotation, books.chars, books.cover, books.file, books.fileid, books.int_id, books.isbn, books.lang, books.src_lang, books.lastreleasedate, books.lvl, books.onsale, books.price, books.programused, books.version, books.history, books.publishcity, books.publishyear, books.publishername, books.sequencenametitle, books.sequencenumbertitle, books.sequencenamepublish, books.sequencenumberpublish, books.showpreview, books.srcocr, books.srcurl, books.title, books.translatorfirstname, books.translatorid, books.translatorlastname, books.translatormiddlename, books.translatorurl, books.type "
			+ "FROM books "
			+ "LEFT JOIN books_authors "
			+ "ON books.id=books_authors.book_id "
			+ "LEFT JOIN authors "
			+ "ON books_authors.authors_id=authors.id "
			+ "LEFT JOIN book_keywords "
			+ "ON books.id=book_keywords.book_id "
			+ "LEFT JOIN book_genres "
			+ "ON books.id=book_genres.book_id "
			+ "LEFT JOIN custominfo "
			+ "ON books.id=custominfo.book_id "
			+ "WHERE books.id=?";	
	
	public static final String QUERY_CHECK_BOOK_EXIST = "SELECT EXISTS(SELECT id FROM library.books WHERE id = ?)";
	
	public static final String QUERY_DELETE_BOOK = "DELETE FROM books "
			+ "WHERE id=?";
	
	public static final String QUERY_DELETE_BOOK_AUTHOR = "DELETE FROM books_authors WHERE book_id=?";	
	
	public static final String QUERY_DELETE_CUSTOM_INFO = "DELETE FROM custominfo WHERE book_id=?";
	
	public static final String QUERY_DELETE_GENRES = "DELETE FROM book_genres WHERE book_id=?";
	
	public static final String QUERY_DELETE_KEYWORDS = "DELETE FROM book_keywords WHERE book_id=?";
	
	public static final String QUERY_INSERT_BOOK = "INSERT INTO library.books("
			+ "id, addeddate, allowread, year, annotation, chars, cover, file, fileid, int_id, isbn, lang, src_lang, lastreleasedate, lvl, onsale, price, programused, version, history, publishcity, publishyear, publishername, sequencenametitle, sequencenumbertitle, sequencenamepublish, sequencenumberpublish, showpreview, srcocr, srcurl, title, translatorfirstname, translatorid, translatorlastname, translatormiddlename, translatorurl, type) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String QUERY_INSERT_BOOK_AUTHOR = "INSERT INTO library.books_authors("
			+ "book_id, authors_id) "
			+ "VALUES (?, ?)";
	
	public static final String QUERY_INSERT_CUSTOM_INFO = "INSERT INTO library.custominfo("
			+ "custom_info_value, info_type, book_id) "
			+ "VALUES (?, ?, ?)";
	
	public static final String QUERY_INSERT_GENRES = "INSERT INTO library.book_genres("
			+ "book_id, genres) "
			+ "VALUES (?, ?)";
	
	public static final String QUERY_INSERT_KEYWORDS = "INSERT INTO library.book_keywords("
			+ "book_id, keywords) "
			+ "VALUES (?, ?)";		
	
	public static final String QUERY_UPDATE_BOOK = "UPDATE library.books SET "
			+ "addeddate=?, allowread=?, year=?, annotation=?, chars=?, cover=?, "
			+ "file=?, fileid=?, int_id=?, isbn=?, lang=?, src_lang=?, lastreleasedate=?, "
			+ "lvl=?, onsale=?, price=?, programused=?, version=?, history=?, publishcity=?, "
			+ "publishyear=?, publishername=?, sequencenametitle=?, sequencenumbertitle=?, "
			+ "sequencenamepublish=?, sequencenumberpublish=?, showpreview=?, srcocr=?, "
			+ "srcurl=?, title=?, translatorfirstname=?, translatorid=?, translatorlastname=?, "
			+ "translatormiddlename=?, translatorurl=?, type=? "
			+ "WHERE id=?";
	
	public static final String QUERY_GET_AllBOOKS = "SELECT books.id bookid, authors.id authorid,  book_keywords.keywords keyword, book_genres.genres genre, custominfo.id, custominfo.custom_info_value, custominfo.info_type, custominfo.book_id, authors.first_name, authors.last_name, authors.middle_name, authors.full_name_rodit, authors.url, authors.ex_id, authors.hub_id, authors.nickname, authors.email, authors.home_page, authors.lvl, authors.relation, authors.subject_id, books.addeddate, books.allowread, books.year, books.annotation, books.chars, books.cover, books.file, books.fileid, books.int_id, books.isbn, books.lang, books.src_lang, books.lastreleasedate, books.lvl, books.onsale, books.price, books.programused, books.version, books.history, books.publishcity, books.publishyear, books.publishername, books.sequencenametitle, books.sequencenumbertitle, books.sequencenamepublish, books.sequencenumberpublish, books.showpreview, books.srcocr, books.srcurl, books.title, books.translatorfirstname, books.translatorid, books.translatorlastname, books.translatormiddlename, books.translatorurl, books.type "
			+ "FROM (SELECT * FROM books ORDER BY title LIMIT ? OFFSET ?) books "
			+ "LEFT JOIN books_authors "
			+ "ON books.id=books_authors.book_id "
			+ "LEFT JOIN authors "
			+ "ON books_authors.authors_id=authors.id "
			+ "LEFT JOIN book_keywords "
			+ "ON books.id=book_keywords.book_id "
			+ "LEFT JOIN book_genres "
			+ "ON books.id=book_genres.book_id "
			+ "LEFT JOIN custominfo "
			+ "ON books.id=custominfo.book_id";
	
	public static final String QUERY_GET_BOOKS_BY_TITLE = "SELECT books.id bookid, authors.id authorid,  book_keywords.keywords keyword, book_genres.genres genre, custominfo.id, custominfo.custom_info_value, custominfo.info_type, custominfo.book_id, authors.first_name, authors.last_name, authors.middle_name, authors.full_name_rodit, authors.url, authors.ex_id, authors.hub_id, authors.nickname, authors.email, authors.home_page, authors.lvl, authors.relation, authors.subject_id, books.addeddate, books.allowread, books.year, books.annotation, books.chars, books.cover, books.file, books.fileid, books.int_id, books.isbn, books.lang, books.src_lang, books.lastreleasedate, books.lvl, books.onsale, books.price, books.programused, books.version, books.history, books.publishcity, books.publishyear, books.publishername, books.sequencenametitle, books.sequencenumbertitle, books.sequencenamepublish, books.sequencenumberpublish, books.showpreview, books.srcocr, books.srcurl, books.title, books.translatorfirstname, books.translatorid, books.translatorlastname, books.translatormiddlename, books.translatorurl, books.type "
			+ "FROM (SELECT * FROM books WHERE title LIKE ? ORDER BY title LIMIT ? OFFSET ?) books "
			+ "LEFT JOIN books_authors "
			+ "ON books.id=books_authors.book_id "
			+ "LEFT JOIN authors "
			+ "ON books_authors.authors_id=authors.id "
			+ "LEFT JOIN book_keywords "
			+ "ON books.id=book_keywords.book_id "
			+ "LEFT JOIN book_genres "
			+ "ON books.id=book_genres.book_id "
			+ "LEFT JOIN custominfo "
			+ "ON books.id=custominfo.book_id";
	
	public static final String QUERY_GET_BOOKS_BY_ISBN = "SELECT books.id bookid, authors.id authorid,  book_keywords.keywords keyword, book_genres.genres genre, custominfo.id, custominfo.custom_info_value, custominfo.info_type, custominfo.book_id, authors.first_name, authors.last_name, authors.middle_name, authors.full_name_rodit, authors.url, authors.ex_id, authors.hub_id, authors.nickname, authors.email, authors.home_page, authors.lvl, authors.relation, authors.subject_id, books.addeddate, books.allowread, books.year, books.annotation, books.chars, books.cover, books.file, books.fileid, books.int_id, books.isbn, books.lang, books.src_lang, books.lastreleasedate, books.lvl, books.onsale, books.price, books.programused, books.version, books.history, books.publishcity, books.publishyear, books.publishername, books.sequencenametitle, books.sequencenumbertitle, books.sequencenamepublish, books.sequencenumberpublish, books.showpreview, books.srcocr, books.srcurl, books.title, books.translatorfirstname, books.translatorid, books.translatorlastname, books.translatormiddlename, books.translatorurl, books.type "
			+ "FROM (SELECT * FROM books WHERE isbn = ? ORDER BY title LIMIT ? OFFSET ?) books "
			+ "LEFT JOIN books_authors "
			+ "ON books.id=books_authors.book_id "
			+ "LEFT JOIN authors "
			+ "ON books_authors.authors_id=authors.id "
			+ "LEFT JOIN book_keywords "
			+ "ON books.id=book_keywords.book_id "
			+ "LEFT JOIN book_genres "
			+ "ON books.id=book_genres.book_id "
			+ "LEFT JOIN custominfo "
			+ "ON books.id=custominfo.book_id";	
	
	public static final String QUERY_GET_BOOKS_BY_AUTHOR = "SELECT books.bookid bookid, authors.id authorid,  book_keywords.keywords keyword, book_genres.genres genre, custominfo.id, custominfo.custom_info_value, custominfo.info_type, custominfo.book_id, authors.first_name, authors.last_name, authors.middle_name, authors.full_name_rodit, authors.url, authors.ex_id, authors.hub_id, authors.nickname, authors.email, authors.home_page, authors.lvl authorslvl, authors.relation, authors.subject_id, books.addeddate, books.allowread, books.year, books.annotation, books.chars, books.cover, books.file, books.fileid, books.int_id, books.isbn, books.lang, books.src_lang, books.lastreleasedate, books.lvl, books.onsale, books.price, books.programused, books.version, books.history, books.publishcity, books.publishyear, books.publishername, books.sequencenametitle, books.sequencenumbertitle, books.sequencenamepublish, books.sequencenumberpublish, books.showpreview, books.srcocr, books.srcurl, books.title, books.translatorfirstname, books.translatorid, books.translatorlastname, books.translatormiddlename, books.translatorurl, books.type "
			+ "FROM ("
				+ "SELECT books.id bookid, authors.id authorid, authors.first_name, authors.last_name, authors.middle_name, authors.full_name_rodit, authors.url, authors.ex_id, authors.hub_id, authors.nickname, authors.email, authors.home_page, authors.lvl authorslvl, authors.relation, authors.subject_id, books.addeddate, books.allowread, books.year, books.annotation, books.chars, books.cover, books.file, books.fileid, books.int_id, books.isbn, books.lang, books.src_lang, books.lastreleasedate, books.lvl, books.onsale, books.price, books.programused, books.version, books.history, books.publishcity, books.publishyear, books.publishername, books.sequencenametitle, books.sequencenumbertitle, books.sequencenamepublish, books.sequencenumberpublish, books.showpreview, books.srcocr, books.srcurl, books.title, books.translatorfirstname, books.translatorid, books.translatorlastname, books.translatormiddlename, books.translatorurl, books.type "
				+ "FROM books "
				+ "INNER JOIN books_authors "
				+ "ON books.id=books_authors.book_id "
				+ "LEFT JOIN authors ON books_authors.authors_id=authors.id "
				+ "WHERE authors.last_name LIKE ? ORDER BY title LIMIT ? OFFSET ?"
			+ ") books "
			+ "LEFT JOIN books_authors "
			+ "ON books.bookid=books_authors.book_id "
			+ "LEFT JOIN authors "
			+ "ON books_authors.authors_id=authors.id "
			+ "LEFT JOIN book_keywords "
			+ "ON books.bookid=book_keywords.book_id "
			+ "LEFT JOIN book_genres "
			+ "ON books.bookid=book_genres.book_id "
			+ "LEFT JOIN custominfo "
			+ "ON books.bookid=custominfo.book_id ";
}
