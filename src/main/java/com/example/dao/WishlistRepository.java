package com.example.dao;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,Long>{

	  List<Wishlist> findByUser(User user);

	    Optional<Wishlist> findByUserAndProduct(
	            User user,
	            Product product);


}
