package ru.bellintegrator.simpleService.organization.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleService.organization.repositories.specifications.OrganizationSpecification;
import ru.bellintegrator.simpleService.organization.view.OrganizationView;

import java.util.Map;

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
