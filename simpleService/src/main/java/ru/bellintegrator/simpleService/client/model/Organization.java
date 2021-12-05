package ru.bellintegrator.simpleService.client.model;

import ru.bellintegrator.simpleService.address.model.Address;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_name", length = 127, nullable = false)
    private String name;

    @Column(name = "fl_full_name", length = 255, nullable = false)
    private String fullName;

    @Column(name = "fl_inn", length = 12, nullable = false)
    private String inn;

    @Column(name = "fl_kpp", length = 9, nullable = false)
    private String kpp;

    @Column(name = "fl_phone", length = 15)
    private String phone;

    @Column(name = "fl_is_active", nullable = false)
    private Boolean isActive;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "tb_organization_address",
            joinColumns = @JoinColumn(name = "fl_organization_id"),
            inverseJoinColumns = @JoinColumn(name = "fl_address_id")
    )
    private Set<Address> addresses;

}
