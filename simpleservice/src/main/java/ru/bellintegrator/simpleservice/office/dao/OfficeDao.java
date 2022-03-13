package ru.bellintegrator.simpleservice.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

import java.util.List;

/**
 * <code>OfficeDao</code> предоставляет доступ к данным по офисам в БД.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.1
 * @since 13.03.2022
 */
public interface OfficeDao {

    /**
     * Возвращает список всех сущностей находящихся в БД.
     *
     * @return <code>{@link List}</code> <code>{@link OfficeEntity}</code>, сущности находящиеся в БД.
     */
    public List<OfficeEntity> loadAllOffices();

    /**
     * Получить все объекты OfficeEntities по фильтру
     *
     * @param officeForHTTPMethodListView является для метода набором параметров/фильтром, по которому будет происходить отбор сущностей.
     *
     * @return <code>{@link List}</code> <code>{@link OfficeEntity}</code>, сущности находящиеся в БД, удовлетворяющих переданному фильтру.
     */
    public List<OfficeEntity> loadOfficesByFilter(OfficeForHTTPMethodListView officeForHTTPMethodListView);

    /**
     * Получить сущность с переданным <code>id</code>
     *
     * @param id сущности которую необходимо выгрузить.
     *
     * @return <code>{@link OfficeEntity}</code>
     */
    public OfficeEntity loadOfficeById(Long id);

    /**
     * Обновить данные существующего в БД объекта {@link OfficeEntity}.
     *
     * @param office итоговое состояние офиса управляемого persistence context для сохранения изменений в БД.
     */
    public void updateOffice(OfficeEntity office);

    /**
     * Добавить новый офис
     *
     * @param office сущность в состояние new, которую необходимо сохранить в бд.
     */
    public void addNewOffice(OfficeEntity office);

    /**
     * Проверяет наличие в БД офиса с указанным именем.
     *
     * @param officeName используется для поиска офиса в БД, значения поля name которого будет соответствовать данному параметру.
     *
     * @return <b>true</b> если офис с таким именем найден. <b>false</b> во всех остальных случаях.
     */
    public boolean isExistOfficeWithName(String officeName);
}
