package ru.bellintegrator.simpleservice.common.exception;

/**
 * Расширяет класс {@link RuntimeException}. Используется как родительский класс-exception
 * для генерации ошибок связанных с бизнес-логикой сервиса.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
public class SimpleServiceException extends RuntimeException{
    /**
     * @param message детальное описание ошибки
     */
    public SimpleServiceException(String message) {
        super(message);
    }
}
