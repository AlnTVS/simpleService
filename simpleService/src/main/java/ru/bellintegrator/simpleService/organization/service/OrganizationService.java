package ru.bellintegrator.simpleService.organization.service;


import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleService.organization.view.FullOrganizationView;
import ru.bellintegrator.simpleService.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param organizationView
     */
    void add(OrganizationView organizationView);

    /**
     * Получить список организаций
     *
     * @return {@Person}
     */
    List<OrganizationView> organizations();

    /**
     * Получить список организаций по фильтру
     *
     * @param spec
     *
     * @return {@Person}
     */
    List<OrganizationView> organizations(Specification<OrganizationEntity> spec);

    FullOrganizationView organisationById(Long id);
}
