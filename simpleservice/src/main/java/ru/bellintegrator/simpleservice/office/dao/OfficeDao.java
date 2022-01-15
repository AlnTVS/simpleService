package ru.bellintegrator.simpleservice.office.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
import ru.bellintegrator.simpleservice.office.view.OfficeView;

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
     * @param officeView
     *
     * @return
     */
    public List<OfficeEntity> loadOfficesByFilter(OfficeView officeView);

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
