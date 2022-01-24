package ru.bellintegrator.simpleservice.common.exception;

import ru.bellintegrator.simpleservice.common.exception.SimpleServiceException;

/**
 * Расширяет класс {@link SimpleServiceException}. Используется для генерации ошибок связанных с бизнес-логикой сервиса в случае,
 * когда обязательные параметры не были переданы в метод сервиса.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
public class NotFountRequiredParametersException extends SimpleServiceException {
    public NotFountRequiredParametersException(String message) {
        super(message);
    }
}
