package com.toyproject.bookmanagement.dto.book;

import java.util.List;

import lombok.Data;

@Data
public class SearchBookReqDto {
	private int page;
	private List<Integer> categoryIds;
	private String searchValue;
}
