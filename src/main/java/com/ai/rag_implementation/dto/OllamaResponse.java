package com.ai.rag_implementation.dto;

import lombok.Getter;

public class OllamaResponse {

    private String model;
    @Getter
    private String response;
    private boolean done;

}