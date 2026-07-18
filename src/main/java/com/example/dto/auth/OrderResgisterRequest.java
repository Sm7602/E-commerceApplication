package com.example.dto.auth;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResgisterRequest {
	    
	    private String orderNumber;

	    private BigDecimal totalAmount;

	    private String shippingAddress;

	    private String status;

}
