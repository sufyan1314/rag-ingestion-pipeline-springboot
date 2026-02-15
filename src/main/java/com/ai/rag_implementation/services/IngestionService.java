package com.ai.rag_implementation.services;

import com.ai.rag_implementation.model.Document;
import com.ai.rag_implementation.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestionService {

    private final EmbeddingService embeddingService;
    private final ChunkingService chunkingService;
    private final DocumentRepository documentRepository;

    public void ingest(String docName, String text) {

        List<String> chunks = chunkingService.chunk(text);

        for (int i = 0; i < chunks.size(); i++) {

            float[] embedding = embeddingService.embed(chunks.get(i));

            Document document = Document.builder()
                    .docName(docName)
                    .chunkIndex(i)
                    .content(chunks.get(i).replaceAll("\\x00", ""))
                    .embedding(embedding)
                    .build();

            documentRepository.save(document);
        }
    }
}

