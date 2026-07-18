package com.example.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
   
	    private String token;

	    private String tokenType;

	    private Long userId;

	    private String email;

	    private String role;

	    private String message;
}
