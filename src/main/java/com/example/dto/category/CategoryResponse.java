package com.example.dto.category;


import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
	     
	    private Long id;

	    private String name;

	    private String description;

	    private String imageUrl;

	    private Boolean active;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    private Integer totalProducts;

	    private List<Product> products;
}
