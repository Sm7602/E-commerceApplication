package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem,Long>{

}
