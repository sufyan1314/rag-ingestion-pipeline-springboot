package com.ai.rag_implementation.repositories;

public record SearchResult(
        Long id,
        String content,
        Double similarity
) {}
