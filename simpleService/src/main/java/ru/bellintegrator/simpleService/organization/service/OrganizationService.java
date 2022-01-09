package ru.bellintegrator.simpleService.organization.service;


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
     * Получить список людей
     *
     * @return {@Person}
     */
    List<OrganizationView> organizations();
}
