package ru.bellintegrator.simpleservice.organization.exceptions;

import ru.bellintegrator.simpleservice.common.exception.SimpleServiceException;

public class OrganizationNotFoundException extends SimpleServiceException {
    public OrganizationNotFoundException(String message) {
        super(message);
    }
}
