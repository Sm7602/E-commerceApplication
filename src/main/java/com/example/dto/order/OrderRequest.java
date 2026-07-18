package com.example.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

	    @NotNull(message = "Customer Id is required.")
	    private Long customerId;

	    @NotBlank(message = "Shipping Address is required.")
	    private String shippingAddress;

	    @NotBlank(message = "Payment Method is required.")
	    private String paymentMethod;
}
