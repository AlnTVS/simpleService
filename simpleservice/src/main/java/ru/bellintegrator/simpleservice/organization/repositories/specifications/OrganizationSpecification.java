package ru.bellintegrator.simpleservice.organization.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;

/**
 * Спецификации для {@link OrganizationEntity}.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
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
