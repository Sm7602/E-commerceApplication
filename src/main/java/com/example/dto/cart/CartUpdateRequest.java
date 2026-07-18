package com.example.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartUpdateRequest {

	
	    @NotNull(message = "Status is required.")
	    private Boolean active;
	    
	    @NotNull
	    @Positive
	    private Integer quantity;
}
