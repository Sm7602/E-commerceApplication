package com.example.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Cart;
import com.example.entity.CartItem;
import com.example.entity.Product;


public interface CartItemRepository extends JpaRepository<CartItem,Long>{

	 Optional<CartItem>findByCartAndProduct(Cart cart,Product product);
}
