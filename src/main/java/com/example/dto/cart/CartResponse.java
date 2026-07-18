package com.example.dto.cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.example.entity.CartItem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {
	    
	    private Long id;

	    private BigDecimal totalAmount;

	    private Boolean active;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    private Long customerId;

	    private String customerName;

	    private Integer totalItems;

	    private List<CartItem> items;
}
