package com.example.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.CategoryRepository;
import com.example.dao.ProductRepository;
import com.example.entity.Category;
import com.example.entity.Product;

@Service
public class ProductService {
	
	  @Autowired
	    private ProductRepository productRepository;

	    @Autowired
	    private CategoryRepository categoryRepository;

	    public Product saveProduct(Product product) {
	        System.out.println("ProductService.saveProduct()");
	        return productRepository.save(product);
	    }

	    public List<Product> getAllProducts() {
	        System.out.println("ProductService.getAllProducts()");
	        return productRepository.findAll();
	    }

	    public Product getProductById(Long id) {
	        System.out.println("ProductService.getProductById()");
	        return productRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));
	    }

	    public Product updateProduct(Long id, Product updatedProduct) {
	    	System.out.println("ProductService.updateProduct()");
	        Product existingProduct = productRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));

	        existingProduct.setName(updatedProduct.getName());
	        existingProduct.setDescription(updatedProduct.getDescription());
	        existingProduct.setPrice(updatedProduct.getPrice());
	        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
	        existingProduct.setBrand(updatedProduct.getBrand());
	        existingProduct.setImageUrl(updatedProduct.getImageUrl());
	        existingProduct.setAverageRating(updatedProduct.getAverageRating());
	        existingProduct.setCategory(updatedProduct.getCategory());

	        return productRepository.save(existingProduct);
	    }

	    public void deleteProduct(Long id) {
	    	System.out.println("ProductService.deleteProduct()");
	        Product product = productRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));
	        productRepository.delete(product);
	    }
	    
	    public List<Product> searchProducts(String keyword) {
	    	System.out.println("ProductService.searchProducts()");
	        return productRepository.findByNameContainingIgnoreCase(keyword);
	    }

	    public List<Product> getProductsByCategory(Long categoryId) {
	    	System.out.println("ProductService.getProductsByCategory()");
	        Category category = categoryRepository.findById(categoryId)
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));
	        return productRepository.findByCategory(category);
	    }
}
