package com.truthlens.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VerificationRequest(

        @NotBlank(message = "Claim must not be blank")
        @Size(max = 2000, message = "Claim must be at most 2000 characters")
        String claim,

        @Size(max = 1000, message = "Article URL must be at most 1000 characters")
        String articleUrl
) {
}
