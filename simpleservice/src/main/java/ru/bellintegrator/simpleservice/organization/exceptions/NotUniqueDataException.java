package ru.bellintegrator.simpleservice.organization.exceptions;

import ru.bellintegrator.simpleservice.common.exception.SimpleServiceException;

public class NotUniqueDataException extends SimpleServiceException {
    public NotUniqueDataException(String message) {
        super(message);
    }
}
