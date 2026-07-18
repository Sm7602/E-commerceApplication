package com.example.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

	
	    @NotBlank(message = "Category name is required.")
	    @Size(min = 3, max = 50,
	            message = "Category name must be between 3 and 50 characters.")
	    private String name;

	    @NotBlank(message = "Description is required.")
	    @Size(max = 500,
	            message = "Description cannot exceed 500 characters.")
	    private String description;

	    private String imageUrl;
}
