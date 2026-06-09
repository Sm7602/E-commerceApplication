package com.example.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Product;
import com.example.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

	List<Review> findByProduct(Product product);

}
