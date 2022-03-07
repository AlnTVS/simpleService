package ru.bellintegrator.simpleservice.office.dao;

import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;

import java.util.List;


public interface OfficeDao {

    /**
     * Получить все объекты OfficeEntities
     *
     * @return
     */
    public List<OfficeEntity> loadAllOffices();

    /**
     * Получить все объекты OfficeEntities по фильтру
     *
     * @param officeForHTTPMethodListView
     *
     * @return
     */
    public List<OfficeEntity> loadOfficesByFilter(OfficeForHTTPMethodListView officeForHTTPMethodListView);

    /**
     * Получить все объекты OfficeEntities по фильтру
     *
     * @param id
     *
     * @return
     */
    public OfficeEntity loadOfficeById(Long id);

    /**
     * Обновить данные объекта OfficeEntity
     *
     * @param office
     *
     * @return
     */
    public void updateOffice(OfficeEntity office);

    /**
     * Добавить новый офис
     *
     * @param office
     *
     * @return
     */
    public void addNewOffice(OfficeEntity office);
}
