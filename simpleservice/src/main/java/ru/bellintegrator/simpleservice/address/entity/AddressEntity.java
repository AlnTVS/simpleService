package ru.bellintegrator.simpleservice.address.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @ManyToMany(mappedBy = "addresses")
    private List<OrganizationEntity> organizations;

    @ManyToMany(mappedBy = "addresses")
    private List<OfficeEntity> offices;

    public List<OrganizationEntity> getOrganizations() {
        if (organizations == null) {
            organizations = new ArrayList<>();
        }
        return organizations;
    }

    public List<OfficeEntity> getOffices() {
        if (offices == null) {
            offices = new ArrayList<>();
        }
        return offices;
    }

    @Override
    public String toString() {
        return address;
    }
}
