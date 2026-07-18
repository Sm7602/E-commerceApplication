package com.example.dto.auth;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterRequest {

    @NotBlank(message = "Product name is required.")
    private String name;

    private String description;

    @NotNull(message = "Price is required.")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "Price must be greater than zero."
    )
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required.")
    @Min(
            value = 0,
            message = "Stock quantity cannot be negative."
    )
    private Integer stockQuantity;

    @NotBlank(message = "Brand name is required.")
    private String brand;

    private String imageUrl;

}