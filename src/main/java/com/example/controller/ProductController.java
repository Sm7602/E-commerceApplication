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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.product.ProductRequest;
import com.example.dto.product.ProductResponse;
import com.example.dto.product.ProductUpdateRequest;
import com.example.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponse saveProduct(@Valid @RequestBody ProductRequest request) {
    	System.out.println("ProductController.saveProduct()");
        return productService.saveProduct(request);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
    	System.out.println("ProductController.getAllProducts()");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
    	 System.out.println("ProductController.getProductById()");
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,@Valid @RequestBody ProductUpdateRequest request) {
    	 System.out.println("ProductController.updateProduct()");
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
    	System.out.println("ProductController.deleteProduct()");
        productService.deleteProduct(id);
        return "Product Deleted Successfully";
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProducts( @RequestParam String keyword) {
    	System.out.println("ProductController.searchProducts()");
        return productService.searchProducts(keyword);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponse> getProductsByCategory(@PathVariable Long id) {
    	System.out.println("ProductController.getProductsByCategory()");
        return productService.getProductsByCategory(id);
    }

}
