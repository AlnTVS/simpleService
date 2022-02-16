package ru.bellintegrator.simpleservice.document.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.simpleservice.document.entity.DocumentEntity;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class DocumentDaoImpl implements DocumentDao{

    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public DocumentEntity findDocumentById(Long id) {
        return em.find(DocumentEntity.class, id);
    }

    @Override
    public DocumentEntity findDocumentByName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocumentEntity> criteriaQuery = criteriaBuilder.createQuery(DocumentEntity.class);
        Root<DocumentEntity> documentEntityRoot = criteriaQuery.from(DocumentEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(documentEntityRoot.join("typeDocument").get("type"),name));

        TypedQuery<DocumentEntity> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public DocumentEntity findDocumentByNumber(String number) {
        return null;
    }

    @Transactional
    @Override
    public void updateDocument(DocumentEntity document) {
        DocumentEntity documentEntity = em.find(DocumentEntity.class,document.getId());
        documentEntity.setDate(document.getDate());
        documentEntity.setNumber(document.getNumber());
        documentEntity.setTypeDocument(document.getTypeDocument());
    }

    @Override
    public void saveDocument(DocumentEntity document) {
        em.persist(document);
    }
}
