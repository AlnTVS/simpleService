package ru.bellintegrator.simpleService.office.entity;

import ru.bellintegrator.simpleService.address.entity.AddressEntity;
import ru.bellintegrator.simpleService.user.entity.UserEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "office")
public class OfficeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "office_address",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<AddressEntity> addresses;

    @OneToMany(mappedBy = "office")
    private List<UserEntity> users;

    public void addOffice(AddressEntity address) {
        addresses.add(address);
        address.getOffices().add(this);
    }
    public void removeOffice(AddressEntity address) {
        addresses.remove(address);
        address.getOffices().remove(this);
    }
}
