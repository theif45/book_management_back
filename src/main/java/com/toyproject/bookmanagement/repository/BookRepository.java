package com.toyproject.bookmanagement.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.toyproject.bookmanagement.entity.Book;
import com.toyproject.bookmanagement.entity.Category;
import com.toyproject.bookmanagement.entity.RentalList;

@Mapper
public interface BookRepository {
	public Book getBook(int bookId);
	public List<Book> searchBooks(Map<String, Object> map);
	public int getTotalCount(Map<String, Object> map);
	public List<Category> getCategories();
	public int getLikeCount(int bookId);
	public int getLikeStatus(Map<String, Object> map);
	public int setLike(Map<String, Object> map);
	public int disLike(Map<String, Object> map);
	// mapper에서 결과가 여러개인 경우 알아서 list로 얻음
	public List<RentalList> getRentalListByBook(int bookId);
	public int rentalBook(Map<String, Object> map);
	public int returnBook(Map<String, Object> map);
	public int registerBookList(int bookId);
}
