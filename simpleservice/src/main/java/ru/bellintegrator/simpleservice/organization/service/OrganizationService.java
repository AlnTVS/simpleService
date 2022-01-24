package ru.bellintegrator.simpleservice.organization.service;


import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodsExtendedView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

import java.util.List;
/**
 * Отвечает за бизнес логику связанную с организациями.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
public interface OrganizationService {

    /**
     * Возвращаем список всех организаций.
     *
     * @return <code>List</code> {@link OrganizationForHTTPMethodListView}, содержащих общую информацию об объектах.
     */
    List<OrganizationForHTTPMethodListView> organizations();

    /**
     * Возвращаем список всех организаций удовлетворяющих виду переданному в этот метод.
     *
     * @param organizationForHTTPMethodListView является фильтром, экземпляром {@link OrganizationForHTTPMethodListView},
     * значения которого указывают на критерии отбора объектов
     *
     * @return <code>List</code> <code>OrganizationForHTTPMethodListView</code>, содержащих общую информацию об объектах,
     * значения которых частично или полностью совпадают со значениями переданного в метод параметра.
     */
    List<OrganizationForHTTPMethodListView> organizations(OrganizationForHTTPMethodListView organizationForHTTPMethodListView);

    /**
     * Возвращаем организацию по <code>id</code>.
     *
     * @param id указывает на значения <code>id</code> организации в БД, которую хотим получить.
     *
     * @return {@link OrganizationForHTTPMethodsExtendedView} расширенная информация об объекте организация с соответствующим <code>id</code>.
     */
    OrganizationForHTTPMethodsExtendedView organisationById(Long id);

    /**
     * Обновляем информацию об уже существующей организации в БД.
     *
     * @param organizationForHTTPMethodsExtendedView является экземпляром {@link OrganizationForHTTPMethodsExtendedView},
     * значения полей должны соответствовать конечному виду к которому мы хотим привести запись в БД.
     */
    void updateOrganization(OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView);

    /**
     * Добавляем новую организацию в БД. Уникальные поля не должны повторять значения уже существующих объектов в БД.
     * <code>id</code> будет сгенерирован автоматически.
     *
     * @param organizationForHTTPMethodsExtendedView
     */
    void addNewOrganization(OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView);
}
