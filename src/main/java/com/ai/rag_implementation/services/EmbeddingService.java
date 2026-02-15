package com.ai.rag_implementation.services;

import com.ai.rag_implementation.model.EmbeddingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class EmbeddingService {

    private final WebClient webClient;

    public EmbeddingService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:11434")
                .build();
    }

    public float[] embed(String text) {

        Map<String, Object> request = Map.of(
                "model", "nomic-embed-text",
                "prompt", text
        );


        EmbeddingResponse response =  webClient.post()
                .uri("/api/embeddings")
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(EmbeddingResponse.class)
                .block();


        List<Double> vector = response.getEmbedding();

        float[] embedding = new float[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            embedding[i] = vector.get(i).floatValue();
        }

        return embedding;
    }
}


