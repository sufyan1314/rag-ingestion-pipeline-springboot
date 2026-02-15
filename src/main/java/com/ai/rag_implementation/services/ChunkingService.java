package com.ai.rag_implementation.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingService {

    private static final int CHUNK_SIZE = 800;
    private static final int OVERLAP = 100;

    public List<String> chunk(String text) {

        List<String> chunks = new ArrayList<>();
        int start = 0;

        while (start < text.length()) {
            int end = Math.min(start + CHUNK_SIZE, text.length());
            chunks.add(text.substring(start, end));
            start += CHUNK_SIZE - OVERLAP;
        }

        return chunks;
    }
}

