package com.truthlens.dto;

import java.time.LocalDateTime;

public record VerificationResponse(
        Long verificationId,
        String claim,
        String articleUrl,
        String status,
        LocalDateTime createdAt
) {
}