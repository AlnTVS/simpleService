package ru.bellintegrator.simpleservice.organization.service;


import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodsExtendedView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

import java.util.List;

public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param organizationForHTTPMethodListView
     */
    void add(OrganizationForHTTPMethodListView organizationForHTTPMethodListView);

    /**
     * Получить список организаций
     *
     * @return {@List<OrganizationView>}
     */
    List<OrganizationForHTTPMethodListView> organizations();

    /**
     * Получить список организаций по фильтру
     *
     * @param organizationForHTTPMethodListView
     *
     * @return {@List<OrganizationView>}
     */
    List<OrganizationForHTTPMethodListView> organizations(OrganizationForHTTPMethodListView organizationForHTTPMethodListView);

    /**
     * Получить организацию по id
     *
     * @param id
     *
     * @return {@FullOrganizationView}
     */
    OrganizationForHTTPMethodsExtendedView organisationById(Long id);

    /**
     * Обновить информацию организацию по id
     *
     * @param organizationForHTTPMethodsExtendedView
     *
     */
    void updateOrganization(OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView);

    /**
     * Обновить информацию организацию по id
     *
     * @param organizationForHTTPMethodsExtendedView
     *
     */
    void addNewOrganization(OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView);
}
