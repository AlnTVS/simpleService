package ru.bellintegrator.simpleService.organization.model;

import ru.bellintegrator.simpleService.address.model.Address;
import ru.bellintegrator.simpleService.citizenship.model.Citizenship;
import ru.bellintegrator.simpleService.document.model.Document;
import ru.bellintegrator.simpleService.office.model.Office;
import ru.bellintegrator.simpleService.position.model.Position;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fl_office_id", nullable = false)
    private Office office;

    @Column(name = "fl_first_name", length = 63, nullable = false)
    private String firstName;

    @Column(name = "fl_second_name", length = 63)
    private String secondName;

    @Column(name = "fl_middle_name", length = 63)
    private String middleName;

    @Column(name = "fl_last_name", length = 63)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "fl_position_id", nullable = false)
    private Position position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fl_document_id", referencedColumnName = "fl_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "fl_citizenship_id")
    private Citizenship citizenship;

    @Column(name = "fl_phone", length = 15)
    private String phone;

    @Column(name = "fl_is_active", nullable = false)
    private Boolean isActive;

}
