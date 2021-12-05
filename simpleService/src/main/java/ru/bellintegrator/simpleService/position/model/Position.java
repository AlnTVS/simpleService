package ru.bellintegrator.simpleService.position.model;

import ru.bellintegrator.simpleService.organization.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_position_name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "position")
    private List<User> users;

}
