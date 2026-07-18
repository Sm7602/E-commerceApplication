package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Admin;



public interface AdminRepository extends JpaRepository<Admin,Long> {

}
