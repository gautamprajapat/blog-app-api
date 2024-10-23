package com.gp.blog.service;

import java.util.List;

import com.gp.blog.dto.CategoryDto;

public interface CategoryService {
	//create 
	 CategoryDto createCategory(CategoryDto categoryDto);
	//Update
	 CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//Delete
	 void deleteCategory(Integer categoryId);
	//get
	 CategoryDto getCategory(Integer categoryId);
	//getAll
	 
	 List<CategoryDto>getAllCategory();

}
