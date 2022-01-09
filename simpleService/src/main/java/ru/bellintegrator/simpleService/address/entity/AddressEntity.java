package ru.bellintegrator.simpleService.address.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleService.office.entity.OfficeEntity;
import ru.bellintegrator.simpleService.user.entity.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
