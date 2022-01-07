package ru.bellintegrator.simpleService.document.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_document")
public class TypeDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @Column(name = "type", length = 127, nullable = false)
    private String type;

    @OneToMany(mappedBy = "typeDocument")
    private List<DocumentEntity> documents;
}
