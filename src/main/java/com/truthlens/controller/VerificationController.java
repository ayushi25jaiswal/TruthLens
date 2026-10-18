package com.truthlens.controller;

import com.truthlens.dto.VerificationRequest;
import com.truthlens.dto.VerificationResponse;
import com.truthlens.service.VerificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/verifications")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(
            VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping
    public ResponseEntity<VerificationResponse> createVerification(
            @Valid @RequestBody VerificationRequest request) {

        VerificationResponse response =
                verificationService.createVerification(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}