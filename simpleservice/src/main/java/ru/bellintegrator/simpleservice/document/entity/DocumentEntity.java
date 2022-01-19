package ru.bellintegrator.simpleservice.document.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "document")
public class DocumentEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @MapsId
    private UserEntity user;

    @Column(name = "number", length = 63, nullable = false)
    private String number;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_document_id", nullable = false)
    private TypeDocumentEntity typeDocument;

}
