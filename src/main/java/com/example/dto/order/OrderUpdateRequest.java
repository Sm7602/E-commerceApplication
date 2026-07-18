package com.example.dto.order;

import lombok.Data;

@Data
public class OrderUpdateRequest {

	    private String shippingAddress;

	    private String status;

	    private String paymentStatus;

	    private String deliveryStatus;

	    private Boolean active;
}
