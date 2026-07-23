package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.category.CategoryRequest;
import com.example.dto.category.CategoryResponse;
import com.example.dto.category.CategoryUpdateRequest;
import com.example.service.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	

	@PostMapping()
	public CategoryResponse saveCategory(@Valid @RequestBody CategoryRequest request) {
		System.out.println("CategoryController.saveCategory()");
		return categoryService.saveCategory(request);
	}
	
	@GetMapping()
	public List<CategoryResponse> getAllCategory() {
		System.out.println("CategoryController.getAllCategory()");
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/{id}")
	public CategoryResponse getCategoryById(@PathVariable long id) {
		System.out.println("CategoryController.getCategoryById() running.......");
		return categoryService.getCategoryById(id);
	}
	
	@PutMapping("/{id}")
	public CategoryResponse updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryUpdateRequest request) {
		System.out.println("CategoryController.updateCategory() running.......");
		return categoryService.updateCategory(id,request);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable long id) {
		System.out.println("CategoryController.deleteCategory() running.......");
		categoryService.deleteCategory(id);
	}
	
	@DeleteMapping("/deleteAllCategory")
	public void deleteAllCategory() {
		System.out.println("CategoryController.deleteAllCategory() running.......");
		categoryService.deleteAllCategory();
	}
	

}
