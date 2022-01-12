package ru.bellintegrator.simpleservice.organization.entity;

import lombok.*;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "organization")
public class OrganizationEntity {

    public OrganizationEntity(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 127, nullable = false)
    private String name;

    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

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
            name = "organization_address",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<AddressEntity> addresses;

    public void addAddress(AddressEntity address) {
        addresses.add(address);
        address.getOrganizations().add(this);
    }
    public void removeAddress(AddressEntity address) {
        addresses.remove(address);
        address.getOrganizations().remove(this);
    }

    public Set<AddressEntity> getAddress() {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        return addresses;
    }

}
