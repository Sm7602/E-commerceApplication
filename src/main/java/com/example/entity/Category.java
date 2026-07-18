package com.example.entity;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Category {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    private String name;

	    private String description;

	    private String imageUrl;
	    
	    private LocalDateTime createdAt;

		private LocalDateTime updatedAt;

		private Boolean active;
		
		private Integer totalProducts;
	    
	    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	    private List<Product> products;

}
