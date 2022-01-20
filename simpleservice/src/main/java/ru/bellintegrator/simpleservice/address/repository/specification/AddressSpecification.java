package ru.bellintegrator.simpleservice.address.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;

public class AddressSpecification {
    public static Specification<AddressEntity> addressIs(String name) {
        return (Specification<AddressEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("address"), name);
    }
}
