package ru.bellintegrator.simpleService.citizenship.model;

import ru.bellintegrator.simpleService.client.model.Organization;
import ru.bellintegrator.simpleService.office.model.Office;
import ru.bellintegrator.simpleService.organization.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_citizenship")
public class Citizenship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_code", length = 3, nullable = false)
    private String code;

    @Column(name = "fl_name", length = 127, nullable = false)
    private String name;

    @OneToMany(mappedBy = "citizenship")
    private List<User> users;

}
