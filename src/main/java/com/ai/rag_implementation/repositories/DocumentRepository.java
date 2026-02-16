package com.ai.rag_implementation.repositories;

import com.ai.rag_implementation.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = """
    SELECT id,
           content,
           1 - (embedding <=> CAST(:embedding AS vector)) AS similarity
    FROM documents
    ORDER BY embedding <=> CAST(:embedding AS vector)
    LIMIT :limit
""", nativeQuery = true)
    List<Object[]> search(
            @Param("embedding") String embedding,
            @Param("limit") int limit
    );
}

