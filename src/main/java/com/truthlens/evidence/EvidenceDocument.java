package com.truthlens.evidence;

import java.time.LocalDate;

public class EvidenceDocument {

    private final String title;
    private final String sourceName;
    private final String sourceUrl;
    private final String content;
    private final LocalDate publishedDate;

    public EvidenceDocument(
            String title,
            String sourceName,
            String sourceUrl,
            String content,
            LocalDate publishedDate) {

        this.title = title;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.content = content;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }
}
