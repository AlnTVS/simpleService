package ru.bellintegrator.simpleService.address.model;

import ru.bellintegrator.simpleService.client.model.Organization;
import ru.bellintegrator.simpleService.office.model.Office;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_address", length = 255, nullable = false)
    private String address;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "tb_organization_address",
            joinColumns = @JoinColumn(name = "fl_address_id"),
            inverseJoinColumns = @JoinColumn(name = "fl_organization_id")
    )
    private Set<Organization> organizations;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "tb_office_address",
            joinColumns = @JoinColumn(name = "fl_address_id"),
            inverseJoinColumns = @JoinColumn(name = "fl_office_id")
    )
    private Set<Office> offices;
}
