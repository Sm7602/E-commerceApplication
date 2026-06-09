package com.example.entity;
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "orders")
//@AllArgsConstructor
@Entity
public class Order {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    private String orderNumber;

	    private BigDecimal totalAmount;

	    private String shippingAddress;

	    private String status;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    @JsonIgnore
	    private User user;
	    
	    @OneToMany(mappedBy = "order")
	    private List<OrderItem> orderItems;

}
