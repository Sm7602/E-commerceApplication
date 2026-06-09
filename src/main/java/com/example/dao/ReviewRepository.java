package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer>{

}
