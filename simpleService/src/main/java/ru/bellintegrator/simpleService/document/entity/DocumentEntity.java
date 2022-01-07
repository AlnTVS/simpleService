package ru.bellintegrator.simpleService.document.entity;

import org.springframework.format.annotation.DateTimeFormat;
import ru.bellintegrator.simpleService.user.entity.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
public class DocumentEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;

    @Column(name = "number", length = 63, nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "type_document_id", nullable = false)
    private TypeDocumentEntity typeDocument;

}
