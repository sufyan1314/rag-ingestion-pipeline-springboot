package com.ai.rag_implementation.services;

import com.ai.rag_implementation.dto.RetrievedChunk;
import com.ai.rag_implementation.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrievalService {

    private final EmbeddingService embeddingService;
    private final DocumentRepository documentRepository;

    private static final int TOP_K = 5;
    private static final double MIN_SIMILARITY = 0.45;

    public List<RetrievedChunk> retrieve(String question) {

        float[] queryEmbedding = embeddingService.embed(question);
        String vectorString = toPgVector(queryEmbedding);
        List<Object[]> results = documentRepository.search(vectorString, TOP_K);

        List<RetrievedChunk> chunks = new ArrayList<>();

        for (Object[] row : results) {

            Long id = ((Number) row[0]).longValue();
            String content = (String) row[1];
            Double similarity = ((Number) row[2]).doubleValue();

            System.out.println(similarity);
            if (similarity >= MIN_SIMILARITY) {
                chunks.add(new RetrievedChunk(id, content, similarity));
            }
        }

        return chunks;
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

