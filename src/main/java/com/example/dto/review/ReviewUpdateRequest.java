package com.example.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ReviewUpdateRequest {

	@Min(value = 1)
    @Max(value = 5)
    private Integer rating;

    private String comment;

    private Boolean active;
}
