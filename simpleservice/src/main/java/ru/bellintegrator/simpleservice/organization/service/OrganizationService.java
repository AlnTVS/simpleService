package ru.bellintegrator.simpleservice.organization.service;


import org.springframework.data.jpa.domain.Specification;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.organization.view.FullOrganizationView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationView;

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
