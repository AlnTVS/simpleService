package ru.bellintegrator.simpleservice.organization.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.organization.repositories.specifications.OrganizationSpecification;
import ru.bellintegrator.simpleservice.organization.view.OrganizationView;

@Getter
public class OrganizationFilter {
    private Specification<OrganizationEntity> spec;

    public OrganizationFilter(OrganizationView organizationView) {
        this.spec = Specification.where(null);
        spec = spec.and(OrganizationSpecification.nameLike(organizationView.name));
        if (organizationView.inn != null) {
            spec = spec.and(OrganizationSpecification.innLike(organizationView.inn));
        }
        if (organizationView.isActive != null) {
            spec = spec.and(OrganizationSpecification.isActiveEqual(organizationView.isActive));
        }
    }
}
