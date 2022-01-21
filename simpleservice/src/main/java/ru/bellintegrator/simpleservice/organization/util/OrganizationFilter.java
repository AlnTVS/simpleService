package ru.bellintegrator.simpleservice.organization.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.organization.repositories.specifications.OrganizationSpecification;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

@Getter
public class OrganizationFilter {
    private Specification<OrganizationEntity> spec;

    public OrganizationFilter(OrganizationForHTTPMethodListView organizationForHTTPMethodListView) {
        this.spec = Specification.where(null);
        spec = spec.and(OrganizationSpecification.nameLike(organizationForHTTPMethodListView.name));
        if (organizationForHTTPMethodListView.inn != null) {
            spec = spec.and(OrganizationSpecification.innLike(organizationForHTTPMethodListView.inn));
        }
        if (organizationForHTTPMethodListView.isActive != null) {
            spec = spec.and(OrganizationSpecification.isActiveEqual(organizationForHTTPMethodListView.isActive));
        }
    }
}
