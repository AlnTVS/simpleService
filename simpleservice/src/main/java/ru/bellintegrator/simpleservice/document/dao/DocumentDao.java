package ru.bellintegrator.simpleservice.document.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.document.entity.DocumentEntity;

public interface DocumentDao {

    public DocumentEntity findDocumentById(Long id);

    public DocumentEntity findDocumentByName(String name);

    public DocumentEntity findDocumentByNumber(String number);

    public void updateDocument(DocumentEntity document);

    public void saveDocument(DocumentEntity document);
}
