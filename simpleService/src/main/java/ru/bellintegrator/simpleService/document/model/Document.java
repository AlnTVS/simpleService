package ru.bellintegrator.simpleService.document.model;

import org.springframework.format.annotation.DateTimeFormat;
import ru.bellintegrator.simpleService.organization.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @OneToOne(mappedBy = "document")
    private User user;

    @Column(name = "fl_number", length = 63, nullable = false)
    private String name;

    @Column(name = "fl_date", nullable = false)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "fl_type_document_id", nullable = false)
    private TypeDocument typeDocument;

}
