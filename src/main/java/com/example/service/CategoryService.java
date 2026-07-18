package com.example.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.CategoryRepository;
import com.example.dto.admin.AdminResponse;
import com.example.dto.admin.AdminUpdateRequest;
import com.example.dto.category.CategoryRequest;
import com.example.dto.category.CategoryResponse;
import com.example.dto.category.CategoryUpdateRequest;
import com.example.entity.Admin;
import com.example.entity.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private CategoryResponse convertToResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .active(category.getActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .totalProducts(category.getTotalProducts())
                .products(category.getProducts())
                .build();
    }
	
	    public CategoryResponse saveCategory(CategoryRequest request) {
	    	System.out.println("CategoryService.saveCategory()");
	          Category category=Category.builder()
	                  .name(request.getName())
	                  .description(request.getDescription())
	                  .imageUrl(request.getImageUrl())
	                  .createdAt(LocalDateTime.now())
	                  .updatedAt(LocalDateTime.now())
	                  .active(true)
	                  .build();
	          
	          category=  categoryRepository.save(category);
	          return convertToResponse(category);
	    }

	   
	    public List<CategoryResponse> getAllCategories() {
	    	System.out.println("CategoryService.getAllCategory()");
	        return categoryRepository.findAll()
	        		    .stream()
		            .map(this::convertToResponse)
		            .toList();
	    }

	    
	    public CategoryResponse getCategoryById(Long id) {
	    	System.out.println("CategoryService.getCategoryById()");
	        Category category= categoryRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));
	          category=  categoryRepository.save(category);
	          return convertToResponse(category);
	    }

	    
	    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest request) {
	    	System.out.println("CategoryService.updateCategory()");
	        Category existingCategory = categoryRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));

	        existingCategory.setName(request.getName());
	        existingCategory.setDescription(request.getDescription());
	        existingCategory.setImageUrl(request.getImageUrl());
	        existingCategory.setUpdatedAt(LocalDateTime.now());

	        existingCategory=  categoryRepository.save(existingCategory);
	          return convertToResponse(existingCategory);
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
