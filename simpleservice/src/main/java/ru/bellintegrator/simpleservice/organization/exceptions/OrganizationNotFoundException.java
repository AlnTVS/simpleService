package ru.bellintegrator.simpleservice.organization.exceptions;

import ru.bellintegrator.simpleservice.common.exception.SimpleServiceException;

/**
 * Расширяет класс {@link SimpleServiceException}. Используется для генерации ошибок связанных с бизнес-логикой сервиса в случае,
 * когда организация с указанным id не была найдена в БД.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
public class OrganizationNotFoundException extends SimpleServiceException {
    /**
     * @param message детальное описание ошибки
     */
    public OrganizationNotFoundException(String message) {
        super(message);
    }
}
