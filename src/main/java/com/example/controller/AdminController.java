package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.admin.AdminRequest;
import com.example.dto.admin.AdminResponse;
import com.example.dto.admin.AdminUpdateRequest;
import com.example.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    
    @PostMapping
    public AdminResponse createAdmin(
            @Valid @RequestBody AdminRequest request) {
        System.out.println("AdminController.createAdmin()");
        return adminService.createAdmin(request);
    }

    @GetMapping
    public List<AdminResponse> getAllAdmins() {
        System.out.println("AdminController.getAllAdmins()");
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminResponse getAdminById(@PathVariable Long id) {
        System.out.println("AdminController.getAdminById()");
        return adminService.getAdminById(id);
    }

    
    @PutMapping("/{id}")
    public AdminResponse updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminUpdateRequest request) {
        System.out.println("AdminController.updateAdmin()");
        return adminService.updateAdmin(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        System.out.println("AdminController.deleteAdmin()");
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }
}
