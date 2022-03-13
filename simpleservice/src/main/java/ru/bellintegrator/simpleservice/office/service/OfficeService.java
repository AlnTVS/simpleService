package ru.bellintegrator.simpleservice.office.service;

import ru.bellintegrator.simpleservice.common.exception.NotFoundEntityByReceivedParametersException;
import ru.bellintegrator.simpleservice.common.exception.NotFountRequiredParametersException;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodsView;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodsExtendedView;

import java.util.List;
/**
 * Отвечает за бизнес логику связанную с офисами.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.1
 * @since 13.03.2022
 */
public interface OfficeService {
    /**
     * Возвращаем список всех офисов.
     *
     * @return <code>List</code> {@link OfficeForHTTPMethodListView}, содержащих общую информацию об объектах.
     */
    List<OfficeForHTTPMethodListView> offices();

    /**
     * Возвращаем список всех офисов удовлетворяющих виду переданному в этот метод.
     *
     * @param officeForHTTPMethodListView является фильтром, экземпляром {@link OfficeForHTTPMethodListView},
     * значения которого указывают на критерии отбора объектов
     *
     * @return <code>List</code> <code>OfficeForHTTPMethodListView</code>, содержащих общую информацию об объектах,
     * значения совпадают со значениями переданного в метод параметра.
     *
     * @throws NotFoundEntityByReceivedParametersException если объекта с таким id не существует.
     * @throws NotFountRequiredParametersException если не были переданы все необходимые параметры.
     */
    List<OfficeForHTTPMethodListView> offices(OfficeForHTTPMethodListView officeForHTTPMethodListView);

    /**
     * Возвращаем офис по <code>id</code>.
     *
     * @param id указывает на значения <code>id</code> офиса в БД, который хотим получить.
     *
     * @return {@link OfficeForHTTPMethodsView} расширенная информация об объекте организация с соответствующим <code>id</code>.
     *
     * @throws NotFoundEntityByReceivedParametersException если объекта с таким id не существует.
     */
    OfficeForHTTPMethodsView officeById(Long id);

    /**
     * Обновляем информацию об уже существующем офисе в БД.
     *
     * @param officeForHTTPMethodsView является экземпляром {@link OfficeForHTTPMethodsView},
     * значения полей должны соответствовать конечному виду к которому мы хотим привести запись в БД.
     *
     * @throws NotFountRequiredParametersException если не были переданы все необходимые параметры.
     * @throws ru.bellintegrator.simpleservice.common.exception.NotUniqueDataException если переданные параметры, значение которых должны быть уникальны, совпадают со значениями в других сущностях.
     */
    void updateOffice(OfficeForHTTPMethodsView officeForHTTPMethodsView);

    /**
     * Добавляем новую организацию в БД. Уникальные поля не должны повторять значения уже существующих объектов в БД.
     * <code>id</code> будет сгенерирован автоматически.
     *
     * @param officeForHTTPMethodsView является экземпляром {@link OfficeForHTTPMethodsView},
     * значения полей должны соответствовать виду в котором мы хотим сохранить новую сущность в БД.
     *
     *
     * @throws NotFountRequiredParametersException если не были переданы все необходимые параметры.
     * @throws ru.bellintegrator.simpleservice.common.exception.NotUniqueDataException если переданные параметры, значение которых должны быть уникальны, совпадают со значениями в других сущностях.
     */
    void addNewOffice(OfficeForHTTPMethodsView officeForHTTPMethodsView);
}
