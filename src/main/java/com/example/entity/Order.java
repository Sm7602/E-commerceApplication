package com.example.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    private String orderNumber;

	    private BigDecimal totalAmount;

	    private String shippingAddress;

	    private String paymentMethod;

	    private String paymentStatus;

	    private String deliveryStatus;

	    private LocalDateTime deliveredAt;
	    
	    private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		private Boolean active;
	    
	    @ManyToOne
	    @JoinColumn(name = "Customer_id")
	    @JsonIgnore
	    private Customer Customer;
	    
	    @OneToMany(mappedBy = "order")
	    private List<OrderItem> orderItems;
	    
}
