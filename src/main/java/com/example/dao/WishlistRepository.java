package com.example.dao;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Customer;
import com.example.entity.Product;

import com.example.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,Long>{


	    

		Optional<Customer> findByCustomer(Customer customer);

		Optional<Customer> findByCustomerAndProduct(Customer customer, Product product);

		


}
