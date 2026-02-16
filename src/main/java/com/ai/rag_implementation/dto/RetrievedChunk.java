package com.ai.rag_implementation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RetrievedChunk {

    private Long id;
    private String content;
    private Double similarity;
}

