package com.gp.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp.blog.dto.CategoryDto;
import com.gp.blog.payloads.ApiResponse;
import com.gp.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
	    // Create category and get the updated CategoryDto with generated ID
	    CategoryDto createdCategory = categoryService.createCategory(categoryDto);
	    
	    // Return the updated CategoryDto with status CREATED
	    return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}

	

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>updateCategory( @Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId ){

		CategoryDto updatedCategory = categoryService.updateCategory( categoryDto ,categoryId);
		  
		    // Return the updated CategoryDto with status CREATED
		    return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer categoryId ){
		categoryService.deleteCategory(categoryId);
		ApiResponse apiRes=new ApiResponse();
		apiRes.setMessege("Deleted Successfully");
		apiRes.setSuccess(true);
		return new ResponseEntity<>(apiRes, HttpStatus.OK);
	}
	

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer categoryId){
	CategoryDto category=categoryService.getCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getAllCategory(){
	List<CategoryDto> category=categoryService.getAllCategory();
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

}
