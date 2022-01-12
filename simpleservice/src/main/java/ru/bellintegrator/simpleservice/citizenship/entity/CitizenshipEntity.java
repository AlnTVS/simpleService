package ru.bellintegrator.simpleservice.citizenship.entity;

import javax.persistence.*;

@Entity
@Table(name = "citizenship")
public class CitizenshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @Column(name = "name", length = 127, nullable = false)
    private String name;

}
