package com.example.dto.admin;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    private String email;
}
