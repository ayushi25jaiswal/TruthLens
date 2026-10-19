package com.truthlens.evidence;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EvidenceSearchService {

    private static final Set<String> STOP_WORDS = Set.of(
            "the", "is", "in", "of", "and", "a", "an", "to",
            "for", "on", "at", "was", "are", "be", "it",
            "this", "that", "as", "by", "with", "from"
    );

    private final EvidenceStore evidenceStore;

    public EvidenceSearchService(EvidenceStore evidenceStore) {
        this.evidenceStore = evidenceStore;
    }

    public List<EvidenceDocument> search(String claim) {

        Set<String> claimWords = tokenize(claim);

        return evidenceStore.getAllDocuments()
                .stream()
                .map(document -> Map.entry(
                        document,
                        calculateScore(claimWords, document)
                ))
                .filter(entry -> entry.getValue() >= 2)
                .sorted(
                        Map.Entry.<EvidenceDocument, Integer>
                                        comparingByValue()
                                .reversed()
                )
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }

    private int calculateScore(
            Set<String> claimWords,
            EvidenceDocument document) {

        Set<String> evidenceWords = tokenize(
                document.getTitle() + " " + document.getContent()
        );

        int score = 0;

        for (String word : claimWords) {
            if (evidenceWords.contains(word)) {
                score++;
            }
        }

        return score;
    }

    private Set<String> tokenize(String text) {
        return Arrays.stream(
                        text.toLowerCase().split("\\W+"))
                .filter(word -> word.length() > 2)
                .filter(word -> !STOP_WORDS.contains(word))
                .collect(Collectors.toSet());
    }
}
