package com.toyproject.bookmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.bookmanagement.dto.book.SearchBookReqDto;
import com.toyproject.bookmanagement.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<?> searchBooks(SearchBookReqDto searchBookReqDto) {
		return ResponseEntity.ok().body(bookService.searchBooks(searchBookReqDto));
	}
}
