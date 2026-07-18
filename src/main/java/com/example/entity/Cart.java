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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 
	 private LocalDateTime createdAt;

	 private LocalDateTime updatedAt;

	 private Boolean active;
	 
	 private BigDecimal totalAmount;
	 
	 @OneToOne(mappedBy = "cart")
	 @JsonIgnore
	 private Customer customer;
	 
	 @OneToMany(mappedBy = "cart")
	 @JsonIgnore
	 private List<CartItem> items;
	 
	 
	 
	    

}
