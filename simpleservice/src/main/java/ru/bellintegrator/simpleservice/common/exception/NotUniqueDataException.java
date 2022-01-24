package ru.bellintegrator.simpleservice.common.exception;

import ru.bellintegrator.simpleservice.common.exception.SimpleServiceException;

/**
 * Расширяет класс {@link SimpleServiceException}. Используется для генерации ошибок связанных с бизнес-логикой сервиса в случае,
 * когда были переданы не уникальные данные для сохранения в БД для полей с указанием уникальности <code>unique</code>.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
public class NotUniqueDataException extends SimpleServiceException {
    /**
     * @param message детальное описание ошибки
     */
    public NotUniqueDataException(String message) {
        super(message);
    }
}
