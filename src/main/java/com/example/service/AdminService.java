package com.example.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AdminRepository;
import com.example.dto.admin.AdminRequest;
import com.example.dto.admin.AdminResponse;
import com.example.dto.admin.AdminUpdateRequest;
import com.example.entity.Admin;


@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    private AdminResponse convertToResponse(Admin admin) {

        return AdminResponse.builder()
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .phoneNumber(admin.getPhoneNumber())
                .active(admin.getActive())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .userId(admin.getUser().getId())
                .email(admin.getUser().getEmail())
                .build();
    }

    public AdminResponse createAdmin(AdminRequest request) {
        System.out.println("AdminService.createAdmin()");
        Admin admin = Admin.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .build();

        admin = adminRepository.save(admin);

        return convertToResponse(admin);
    }

    public List<AdminResponse> getAllAdmins(){
        System.out.println("AdminService.getAllAdmins()");
        return adminRepository.findAll()
                .stream()
	            .map(this::convertToResponse)
	            .toList();
    }

    public AdminResponse getAdminById(Long id) {
        System.out.println("AdminService.getAdminById()");
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));
        return convertToResponse(admin);
    }

    public AdminResponse updateAdmin(Long id, AdminUpdateRequest request) {
        System.out.println("AdminService.updateAdmin()");
        Admin admin = adminRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Admin not found"));
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setUpdatedAt(LocalDateTime.now());

        admin = adminRepository.save(admin);

        return convertToResponse(admin);
    }

    public void deleteAdmin(Long id) {
        System.out.println("AdminService.deleteAdmin()");
        Admin admin = adminRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Admin not found"));
        admin.setActive(false);
        admin.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(admin);
    }
    
}