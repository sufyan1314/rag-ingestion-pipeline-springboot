package com.ai.rag_implementation.services;

import com.ai.rag_implementation.repositories.SearchResultProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RagService {

    private final RetrievalService retrievalService;
    private final PromptBuilder promptBuilder;
    private final OllamaService ollamaService;

    public String ask(String question) {

        List<SearchResultProjection> chunks =
                retrievalService.retrieve(question);

        if (chunks.isEmpty()) {
            return "I couldn't find relevant information in the documents.";
        }

        String prompt = promptBuilder.buildPrompt(chunks, question);

        return ollamaService.generate(prompt);
    }
}
