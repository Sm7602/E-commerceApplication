package com.example.dto.wishlist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WishlistRequest {

	    @NotNull(message = "Customer Id is required.")
	    private Long customerId;

	    @NotNull(message = "Product Id is required.")
	    private Long productId;
}
