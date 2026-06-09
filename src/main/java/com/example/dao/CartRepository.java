package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{

}
