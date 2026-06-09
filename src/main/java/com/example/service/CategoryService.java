package com.example.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.CategoryRepository;
import com.example.entity.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	
	    public Category saveCategory(Category category) {
	    	System.out.println("CategoryService.saveCategory()");
	        return categoryRepository.save(category);
	    }

	   
	    public List<Category> getAllCategories() {
	    	System.out.println("CategoryService.getAllCategory()");
	        return categoryRepository.findAll();
	    }

	    
	    public Category getCategoryById(Long id) {
	    	System.out.println("CategoryService.getCategoryById()");
	        return categoryRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));
	    }

	    
	    public Category updateCategory(Long id, Category category) {
	    	System.out.println("CategoryService.updateCategory()");
	        Category existingCategory = categoryRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));

	        existingCategory.setName(category.getName());
	        existingCategory.setDescription(category.getDescription());
	        existingCategory.setImageUrl(category.getImageUrl());

	        return categoryRepository.save(existingCategory);
	    }

	    
	    public void deleteCategory(Long id) {
	    	System.out.println("CategoryService.deleteCategory()");
	        Category category = categoryRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));

	        categoryRepository.delete(category);
	    }
	    
	    public void deleteAllCategory() {
	    	System.out.println("CategoryService.deleteAllCategory()");
	        categoryRepository.deleteAll();
	        
	    }
	}
