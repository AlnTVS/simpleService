package ru.bellintegrator.simpleService.document.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_type_document")
public class TypeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_code", length = 3, nullable = false)
    private String code;

    @Column(name = "fl_type", length = 127, nullable = false)
    private String type;

    @OneToMany(mappedBy = "typeDocument")
    private List<Document> documents;
}
