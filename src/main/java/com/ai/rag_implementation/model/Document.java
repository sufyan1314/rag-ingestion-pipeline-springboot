package com.ai.rag_implementation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String docName;

    private Integer chunkIndex;

    @Column(columnDefinition = "text")
    private String content;

    @Column(columnDefinition = "vector(768)")
    private float[] embedding;
}


