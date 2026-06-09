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
import com.example.entity.Category;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	

	@PostMapping()
	public Category saveCategory(@RequestBody Category category) {
		System.out.println("CategoryController.saveCategory()");
		categoryService.saveCategory(category);
		return category;	
	}
	
	@GetMapping()
	public List<Category> getAllCategory() {
		System.out.println("CategoryController.getAllCategory()");
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable long id) {
		System.out.println("CategoryController.getCategoryById() running.......");
		return categoryService.getCategoryById(id);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id,@RequestBody Category category) {
		System.out.println("CategoryController.updateCategory() running.......");
		return categoryService.updateCategory(id,category);
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
