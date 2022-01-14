package ru.bellintegrator.simpleservice.office.service;

import ru.bellintegrator.simpleservice.office.view.OfficeView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationView;

import java.util.List;

public interface OfficeService {
    /**
     * Получить список организаций
     *
     * @return {@List<OrganizationView>}
     */
    List<OfficeView> offices();
}
