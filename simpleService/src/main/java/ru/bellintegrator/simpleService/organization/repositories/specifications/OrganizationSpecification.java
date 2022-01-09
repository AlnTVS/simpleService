package ru.bellintegrator.simpleService.organization.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;

public class OrganizationSpecification {
    public static Specification<OrganizationEntity> nameLike(String name) {
        return (Specification<OrganizationEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<OrganizationEntity> innLike(String inn) {
        return (Specification<OrganizationEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("inn"), "%" + inn + "%");
    }

    public static Specification<OrganizationEntity> isActiveEqual(Boolean isActive) {
        return (Specification<OrganizationEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);
    }
}
