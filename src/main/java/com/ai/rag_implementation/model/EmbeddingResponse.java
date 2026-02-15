package com.ai.rag_implementation.model;

import lombok.Data;
import java.util.List;

@Data
public class EmbeddingResponse {

    private List<Double> embedding;

}

