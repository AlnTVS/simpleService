package ru.bellintegrator.simpleservice.office.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;

import java.util.List;


public interface OfficeDao {

    /**
     * Получить все объекты OfficeEntities
     *
     * @return
     */
    public List<OfficeEntity> offices();
}
