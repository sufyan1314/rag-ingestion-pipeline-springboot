package com.ai.rag_implementation.services;

import com.ai.rag_implementation.repositories.DocumentRepository;
import com.ai.rag_implementation.repositories.SearchResultProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrievalService {

    private final EmbeddingService embeddingService;
    private final DocumentRepository documentRepository;

    private static final int TOP_K = 5;
    private static final double MIN_SIMILARITY = 0.45;

    public List<SearchResultProjection> retrieve(String question) {

        float[] queryEmbedding = embeddingService.embed(question);
        String vectorString = toPgVector(queryEmbedding);

        List<SearchResultProjection> results =
                documentRepository.search(vectorString, TOP_K);

        return results.stream()
                .filter(r -> r.getSimilarity() >= MIN_SIMILARITY)
                .toList();
    }

    private String toPgVector(float[] vector) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            if (i < vector.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}

