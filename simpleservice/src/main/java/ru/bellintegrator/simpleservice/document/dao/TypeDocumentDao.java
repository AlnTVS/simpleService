package ru.bellintegrator.simpleservice.document.dao;

import ru.bellintegrator.simpleservice.document.entity.TypeDocumentEntity;

import java.util.List;

public interface TypeDocumentDao {
    public TypeDocumentEntity loadByType(String type);

    public TypeDocumentEntity loadByCode(String code);

    public List<TypeDocumentEntity> loadAllTypeDocuments();
}
