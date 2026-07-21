package com.example.dto.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductUpdateRequest {

	    private String name;

	    private String description;

	    private BigDecimal price;

	    private Integer stockQuantity;

	    private String brand;

	    private Integer discountPercentage;

	    private Boolean featured;

	    private String imageUrl;

	    private Boolean active;
}
