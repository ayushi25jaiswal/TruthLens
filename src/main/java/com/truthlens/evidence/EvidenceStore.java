package com.truthlens.evidence;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EvidenceStore {

    private final List<EvidenceDocument> documents = List.of(
            new EvidenceDocument(
                    "Eiffel Tower",
                    "Encyclopaedia Britannica",
                    "https://www.britannica.com/topic/Eiffel-Tower-Paris-France",
                    "The Eiffel Tower is an iron tower in Paris, France, " +
                            "built for the 1889 World's Fair.",
                    LocalDate.of(2024, 1, 1)
            ),
            new EvidenceDocument(
                    "Capital of France",
                    "Encyclopaedia Britannica",
                    "https://www.britannica.com/place/France",
                    "Paris is the capital and largest city of France.",
                    LocalDate.of(2024, 1, 1)
            ),
            new EvidenceDocument(
                    "Capital of Italy",
                    "Encyclopaedia Britannica",
                    "https://www.britannica.com/place/Rome",
                    "Rome is the capital city of Italy.",
                    LocalDate.of(2024, 1, 1)
            )
    );

    public List<EvidenceDocument> getAllDocuments() {
        return documents;
    }
}
