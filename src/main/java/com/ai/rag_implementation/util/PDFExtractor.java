package com.ai.rag_implementation.util;

import org.springframework.stereotype.Service;

@Service
public class PDFExtractor {

    public String cleanText(String text) {
        return text.replaceAll("[^\\x00-\\x7F]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
