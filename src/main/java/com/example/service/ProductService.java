package com.example.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.CategoryRepository;
import com.example.dao.ProductRepository;
import com.example.dto.product.ProductRequest;
import com.example.dto.product.ProductResponse;
import com.example.dto.product.ProductUpdateRequest;
import com.example.entity.Category;
import com.example.entity.Product;

@Service
public class ProductService {
	
	  @Autowired
	    private ProductRepository productRepository;

	    @Autowired
	    private CategoryRepository categoryRepository;

	    private ProductResponse convertToResponse(Product product) {

	        return ProductResponse.builder()
	        		    .id(product.getId())
	        		    .name(product.getName())
	        		    .description(product.getDescription())
	        		    .price(product.getPrice())
	        		    .discountedPrice(product.getDiscountedPrice())
	        		    .discountPercentage(product.getDiscountPercentage())
	        		    .stockQuantity(product.getStockQuantity())
	        		    .brand(product.getBrand())
	        		    .sku(product.getSku())
	        		    .featured(product.getFeatured())
	        		    .totalSold(product.getTotalSold())
	        		    .imageUrl(product.getImageUrl())
	        		    .averageRating(product.getAverageRating())
	                .active(product.getActive())
	                .createdAt(product.getCreatedAt())
	                .updatedAt(product.getUpdatedAt())
	                .category(product.getCategory())
	                .reviews(product.getReviews())
	                .build();
	    }
	    
	    private BigDecimal calculateDiscount(BigDecimal amount,Integer percentage) {

	        if (percentage == null || percentage <= 0) {
	            return amount;
	        }

	        BigDecimal discount = amount
	                .multiply(BigDecimal.valueOf(percentage))
	                .divide(BigDecimal.valueOf(100));

	        return amount.subtract(discount);
	    }
	    
	    
	    public ProductResponse saveProduct(ProductRequest request) {
	        System.out.println("ProductService.saveProduct()");
	       
	        Category category = categoryRepository.findById(request.getCategoryId())
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));
	        
	         String sku = "PRD-" + UUID.randomUUID()
            .toString()
            .substring(0, 8)
            .toUpperCase();
	        
	         BigDecimal discountedPrice = calculateDiscount(request.getPrice(),request.getDiscountPercentage());
	         
	        Product product=Product.builder()
	        		.name(request.getName())
        		    .description(request.getDescription())
        		    .price(request.getPrice())
        		    .discountedPrice(discountedPrice)
        		    .discountPercentage(request.getDiscountPercentage())
        		    .stockQuantity(request.getStockQuantity())
        		    .brand(request.getBrand())
        		    .sku(sku)
        		    .featured(request.getFeatured())
        		    .totalSold(0)
        		    .imageUrl(request.getImageUrl())
        		    .averageRating(0.0)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .category(category)
	        		.build();
	        
	        product= productRepository.save(product);
	        return convertToResponse(product);
	    }
	    

	    public List<ProductResponse> getAllProducts() {
	        System.out.println("ProductService.getAllProducts()");
	        return productRepository.findAll()
	        		                    .stream()
                                    .map(this::convertToResponse)
                                    .toList();
	    }

	    public ProductResponse getProductById(Long id) {
	        System.out.println("ProductService.getProductById()");
	        Product product= productRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));
	        
	        return convertToResponse(product);
	    }

	    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
	    	System.out.println("ProductService.updateProduct()");
	        Product existingProduct = productRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));

	        existingProduct.setName(request.getName());
	        existingProduct.setDescription(request.getDescription());
	        existingProduct.setPrice(request.getPrice());
	        existingProduct.setStockQuantity(request.getStockQuantity());
	        existingProduct.setBrand(request.getBrand());
	        existingProduct.setImageUrl(request.getImageUrl());
	        existingProduct.setDiscountPercentage(request.getDiscountPercentage());
	        existingProduct.setFeatured(request.getFeatured());
	        existingProduct.setActive(false);
	        existingProduct.setUpdatedAt(LocalDateTime.now());

	        existingProduct= productRepository.save(existingProduct);
	        return convertToResponse(existingProduct);
	    }

	    public void deleteProduct(Long id) {
	    	System.out.println("ProductService.deleteProduct()");
	        Product product = productRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));
	        productRepository.delete(product);
	    }
	    
	    public List<ProductResponse> searchProducts(String keyword) {
	    	System.out.println("ProductService.searchProducts()");
	        return productRepository.findByNameContainingIgnoreCase(keyword)
	        		                    .stream()
                                     .map(this::convertToResponse)
                                      .toList();
	    }

	    public List<ProductResponse> getProductsByCategory(Long categoryId) {
	    	System.out.println("ProductService.getProductsByCategory()");
	        Category category = categoryRepository.findById(categoryId)
	                .orElseThrow(() ->
	                        new RuntimeException("Category not found"));
	        return productRepository.findByCategory(category)
	        		                     .stream()
                                      .map(this::convertToResponse)
                                        .toList();
	    }
}
