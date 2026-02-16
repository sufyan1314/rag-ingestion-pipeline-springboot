package com.ai.rag_implementation.controller;

import com.ai.rag_implementation.dto.RetrievedChunk;
import com.ai.rag_implementation.services.RetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RetrievalController {

    private final RetrievalService retrievalService;

    @GetMapping("/search")
    public List<RetrievedChunk> search(
            @RequestParam String question) {

        return retrievalService.retrieve(question);
    }
}

