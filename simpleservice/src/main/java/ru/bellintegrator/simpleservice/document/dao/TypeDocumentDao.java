package ru.bellintegrator.simpleservice.document.dao;

import ru.bellintegrator.simpleservice.document.entity.TypeDocumentEntity;

public interface TypeDocumentDao {
    public TypeDocumentEntity loadByType(String type);
}
