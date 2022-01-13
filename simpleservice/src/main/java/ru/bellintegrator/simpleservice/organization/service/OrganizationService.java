package ru.bellintegrator.simpleservice.organization.service;


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
     * @return {@List<OrganizationView>}
     */
    List<OrganizationView> organizations();

    /**
     * Получить список организаций по фильтру
     *
     * @param organizationView
     *
     * @return {@List<OrganizationView>}
     */
    List<OrganizationView> organizations(OrganizationView organizationView);

    /**
     * Получить организацию по id
     *
     * @param id
     *
     * @return {@FullOrganizationView}
     */
    FullOrganizationView organisationById(Long id);

    /**
     * Обновить информацию организацию по id
     *
     * @param fullOrganizationView
     *
     */
    void updateOrganizationByFullView(FullOrganizationView fullOrganizationView);

    /**
     * Обновить информацию организацию по id
     *
     * @param fullOrganizationView
     *
     */
    void addNewOrganization(FullOrganizationView fullOrganizationView);
}
