package com.truthlens.evidence;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidence")
public class EvidenceController {

    private final EvidenceSearchService evidenceSearchService;

    public EvidenceController(
            EvidenceSearchService evidenceSearchService) {
        this.evidenceSearchService = evidenceSearchService;
    }

    @GetMapping("/search")
    public List<EvidenceDocument> searchEvidence(
            @RequestParam String claim) {

        return evidenceSearchService.search(claim);
    }
}
