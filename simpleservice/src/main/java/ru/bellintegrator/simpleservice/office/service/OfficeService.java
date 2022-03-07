package ru.bellintegrator.simpleservice.office.service;

import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
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
    FullOfficeView officeById(Long id);

    /**
     * Обновить информацию офисе по fullOfficeView
     *
     * @param fullOfficeView
     *
     */
    void updateOfficeByFullView(FullOfficeView fullOfficeView);

    /**
     * Добавить новый офис
     *
     * @param fullOfficeView
     *
     */
    void addNewOffice(FullOfficeView fullOfficeView);
}
