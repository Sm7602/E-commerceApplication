package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer>{

}
