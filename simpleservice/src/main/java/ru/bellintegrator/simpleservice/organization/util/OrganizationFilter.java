package ru.bellintegrator.simpleservice.organization.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.organization.repositories.specifications.OrganizationSpecification;
import ru.bellintegrator.simpleservice.organization.service.OrganizationService;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

/**
 * Собирает из спецификаций фильтр. Экземпляр класса создает {@link Specification} для {@link OrganizationEntity}.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
@Getter
public class OrganizationFilter {

    private Specification<OrganizationEntity> spec;

    /**
     * Создает экземпляр этого класса. Собирает спецификацию для <code>OrganizationEntity</code> на основе переданного view.
     *
     * @param organizationForHTTPMethodListView экземпляр {@link OrganizationForHTTPMethodListView},
     * значения полей которого будут установленны в фильтр.
     * */
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
