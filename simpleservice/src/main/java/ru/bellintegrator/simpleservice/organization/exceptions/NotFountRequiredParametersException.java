package ru.bellintegrator.simpleservice.organization.exceptions;

import ru.bellintegrator.simpleservice.common.exception.SimpleServiceException;

public class NotFountRequiredParametersException extends SimpleServiceException {
    public NotFountRequiredParametersException(String message) {
        super(message);
    }
}
