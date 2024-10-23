package com.gp.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.blog.dto.CategoryDto;
import com.gp.blog.entities.Category;
import com.gp.blog.exceptions.ResourceNotFoundException;
import com.gp.blog.repositories.CategoryRepo;
import com.gp.blog.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	    // Map CategoryDto to Category
	    Category category = modelMapper.map(categoryDto, Category.class);
	    
	    // Save the category to the database, which will auto-generate the ID
	    Category addedCategory = categoryRepo.save(category);
	    
	    // Ensure the saved category contains the generated categoryId
	    return modelMapper.map(addedCategory, CategoryDto.class); // Return the updated CategoryDto
	}



	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		 Category category=categoryRepo.findById(categoryId)
				 .orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",categoryId));
		 
		 category.setCategoryTitle(categoryDto.getCategoryTitle());
		 category.setCategoryDescription(categoryDto.getCategoryDescription());
		 Category updatedCategory=categoryRepo.save(category);
		 return modelMapper.map(updatedCategory, CategoryDto.class) ;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
			
		Category category=categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
		
		categoryRepo.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category=categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category>categories=categoryRepo.findAll();
		
		List<CategoryDto>categoriesDto=categories.stream()
				.map(category->modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoriesDto;
	}

}
