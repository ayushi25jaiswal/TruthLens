package com.truthlens.service;

import com.truthlens.dto.VerificationRequest;
import com.truthlens.dto.VerificationResponse;
import com.truthlens.entity.Verification;
import com.truthlens.repository.VerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VerificationService {

    private final VerificationRepository verificationRepository;

    public VerificationService(
            VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Transactional
    public VerificationResponse createVerification(
            VerificationRequest request) {

        Verification verification = new Verification(
                request.claim().trim(),
                request.articleUrl()
        );

        Verification saved =
                verificationRepository.save(verification);

        return new VerificationResponse(
                saved.getId(),
                saved.getClaimText(),
                saved.getArticleUrl(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }
}
