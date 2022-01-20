package ru.bellintegrator.simpleservice.office.service;

import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
import ru.bellintegrator.simpleservice.office.view.OfficeView;

import java.util.List;

public interface OfficeService {
    /**
     * Получить список всех офисов
     *
     * @return {@List<OfficeView>}
     */
    List<OfficeView> offices();

    /**
     * Получить список офисов по фильтру
     *
     * @param officeView
     *
     * @return {@List<OfficeView>}
     */
    List<OfficeView> offices(OfficeView officeView);

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
