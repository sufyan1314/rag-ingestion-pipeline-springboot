package com.ai.rag_implementation.services;

import com.ai.rag_implementation.repositories.SearchResultProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptBuilder {

    public String buildPrompt(List<SearchResultProjection> chunks, String question) {

        if (chunks.isEmpty()) {
            return """
                You are a helpful assistant.
                The system could not find relevant documents.
                Tell the user you don't have enough information.
                """;
        }

        String context = chunks.stream()
                .map(SearchResultProjection::getContent)
                .reduce("", (a, b) -> a + "\n\n" + b);

        return """
        You are a helpful assistant.
        Answer the question ONLY using the context below.
        If the answer is not present, say "I don't know".

        Context:
        ----------------
        %s
        ----------------

        Question:
        %s

        Answer:
        """.formatted(context, question);
    }
}
