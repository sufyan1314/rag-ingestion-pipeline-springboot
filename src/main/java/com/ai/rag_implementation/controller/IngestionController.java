package com.ai.rag_implementation.controller;

import com.ai.rag_implementation.services.IngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IngestionController {

    private final IngestionService ingestionService;

    @PostMapping("/ingest")
    public ResponseEntity<String> ingest(@RequestParam("file") MultipartFile file) {

        try {
            String text = new String(file.getBytes());

            ingestionService.ingest(file.getOriginalFilename(), text);

            return ResponseEntity.ok("Document ingested successfully.");

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error during ingestion: " + e.getMessage());
        }
    }
}

