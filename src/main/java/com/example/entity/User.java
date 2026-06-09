package com.example.entity;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    private String firstName;

	    private String lastName;

	    private String email;

	    private String password;

	    private String phoneNumber;

	    private String profileImage;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "cart_id")
	    private Cart cart;
	    
	    @OneToMany(mappedBy = "user")
	    private List<Order> orders;
	    
	    @OneToMany(mappedBy = "user")
	    private List<Review> reviews;
	    
	    @OneToMany(mappedBy = "user")
	    private List<Wishlist> wishlistItems;
}
