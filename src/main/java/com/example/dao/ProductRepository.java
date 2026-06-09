package com.example.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;
import com.example.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

	List<Product> findByNameContainingIgnoreCase(String keyword);

	List<Product> findByCategory(Category category);

}
