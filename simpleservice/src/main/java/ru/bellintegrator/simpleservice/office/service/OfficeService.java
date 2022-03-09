package ru.bellintegrator.simpleservice.office.service;

import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodsView;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;

import java.util.List;

public interface OfficeService {
    /**
     * Получить список всех офисов
     *
     * @return {@List<OfficeView>}
     */
    List<OfficeForHTTPMethodListView> offices();

    /**
     * Получить список офисов по фильтру
     *
     * @param officeForHTTPMethodListView
     *
     * @return {@List<OfficeView>}
     */
    List<OfficeForHTTPMethodListView> offices(OfficeForHTTPMethodListView officeForHTTPMethodListView);

    /**
     * Получить офис по id
     *
     * @param id
     *
     * @return {FullOfficeView}
     */
    OfficeForHTTPMethodsView officeById(Long id);

    /**
     * Обновить информацию офисе по fullOfficeView
     *
     * @param officeForHTTPMethodsView
     *
     */
    void updateOffice(OfficeForHTTPMethodsView officeForHTTPMethodsView);

    /**
     * Добавить новый офис
     *
     * @param officeForHTTPMethodsView
     *
     */
    void addNewOffice(OfficeForHTTPMethodsView officeForHTTPMethodsView);
}
