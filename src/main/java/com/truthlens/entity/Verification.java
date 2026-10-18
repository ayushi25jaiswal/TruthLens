package com.truthlens.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "verifications")
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String claimText;

    @Column(length = 1000)
    private String articleUrl;

    @Column(nullable = false, length = 30)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected Verification() {
    }

    public Verification(String claimText, String articleUrl) {
        this.claimText = claimText;
        this.articleUrl = articleUrl;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }


}
