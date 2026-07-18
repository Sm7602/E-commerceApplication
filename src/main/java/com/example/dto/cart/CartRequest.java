package com.example.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartRequest {
	 
	    @NotNull
	    private Long customerId;

	    @NotNull
	    private Long productId;

	    @NotNull
	    @Positive
	    private Integer quantity;

		
}
