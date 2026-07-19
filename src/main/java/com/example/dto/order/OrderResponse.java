package com.example.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.OrderItem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

	    private Long id;

	    private String orderNumber;

	    private BigDecimal totalAmount;

	    private String shippingAddress;
	    
	    private String status;

	    private String paymentMethod;

	    private String paymentStatus;

	    private String deliveryStatus;

	    private Boolean active;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    private LocalDateTime deliveredAt;


	    private Long customerId;

	    private String customerFirstName;
	    
	    private String customerLastName;

	    private String phoneNumber;



	    private List<OrderItem> orderItems;
}
