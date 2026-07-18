package com.example.dto.category;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryUpdateRequest {

	
	   @Size(min = 3, max = 50)
	    private String name;

	    @Size(max = 500)
	    private String description;

	    private String imageUrl;

	    private Boolean active;
}
