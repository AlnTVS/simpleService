package ru.bellintegrator.simpleservice.office.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

}
