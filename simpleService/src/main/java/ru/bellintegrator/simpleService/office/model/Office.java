package ru.bellintegrator.simpleService.office.model;

import ru.bellintegrator.simpleService.address.model.Address;
import ru.bellintegrator.simpleService.organization.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_organization_id", nullable = false)
    private Long organizationId;

    @Column(name = "fl_name", length = 255)
    private String name;

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
            name = "tb_office_address",
            joinColumns = @JoinColumn(name = "fl_office_id"),
            inverseJoinColumns = @JoinColumn(name = "fl_address_id")
    )
    private Set<Address> addresses;

    @OneToMany(mappedBy = "office")
    private List<User> users;

}
