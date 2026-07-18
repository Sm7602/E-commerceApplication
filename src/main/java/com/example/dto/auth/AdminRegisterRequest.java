package com.example.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegisterRequest extends BaseRegisterRequest{

	    @NotBlank
	    private String firstName;

	    @NotBlank
	    private String lastName;  
	
}
