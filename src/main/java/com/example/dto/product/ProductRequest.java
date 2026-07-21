package com.example.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductRequest {

	
	    @NotBlank(message = "Product name is required.")
	    private String name;

	    @NotBlank(message = "Description is required.")
	    private String description;

	    @NotNull(message = "Price is required.")
	    @DecimalMin(value = "1.0",
	            message = "Price must be greater than zero.")
	    private BigDecimal price;

	    @NotNull(message = "Stock Quantity is required.")
	    @Positive(message = "Stock Quantity must be greater than zero.")
	    private Integer stockQuantity;

	    @NotBlank(message = "Brand is required.")
	    private String brand;
	    
	    @PositiveOrZero
	    private Integer discountPercentage;

	    private Boolean featured = false;

	    private String imageUrl;

	    @NotNull(message = "Category Id is required.")
	    private Long categoryId;
}
