package com.ai.rag_implementation.repositories;

public interface SearchResultProjection {

    Long getId();

    String getContent();

    Double getSimilarity();
}
