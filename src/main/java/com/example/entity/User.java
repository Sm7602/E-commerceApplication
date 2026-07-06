package com.example.entity;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements UserDetails{
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    private String firstname;

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

	    @Enumerated(EnumType.STRING)
	     private Role role;

	     @Override
	     public Collection<? extends GrantedAuthority> getAuthorities() {
	         return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	     }

	     
	     public String getUsername() {
	         return email;
	     }

	    
	     @Override
	     public boolean isAccountNonExpired() {
	         return true;
	     }

	     @Override
	     public boolean isAccountNonLocked() {
	         return true;
	     }

	     @Override
	     public boolean isCredentialsNonExpired() {
	         return true;
	     }

	     @Override
	     public boolean isEnabled() {
	         return true;
	     }


		 @Override
		 public String getPassword() {
			return password;
		 }
}
