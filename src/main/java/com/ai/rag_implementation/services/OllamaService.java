package com.ai.rag_implementation.services;

import com.ai.rag_implementation.dto.OllamaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:11434")
                .build();
    }

    public String generate(String prompt) {

        return webClient.post()
                .uri("/api/generate")
                .bodyValue(Map.of(
                        "model", "llama3",
                        "prompt", prompt,
                        "stream", false
                ))
                .retrieve()
                .bodyToMono(OllamaResponse.class)
                .block() // blocking because JPA is blocking
                .getResponse();
    }
}
