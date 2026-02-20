package com.ai.rag_implementation.controller;

import com.ai.rag_implementation.services.RagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final RagService ragService;

    @GetMapping("/ask")
    public String chat(@RequestParam String question) {
        return ragService.ask(question);
    }
}
