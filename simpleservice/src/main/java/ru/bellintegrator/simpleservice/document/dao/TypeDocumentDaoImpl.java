package ru.bellintegrator.simpleservice.document.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.document.entity.DocumentEntity;
import ru.bellintegrator.simpleservice.document.entity.TypeDocumentEntity;
import ru.bellintegrator.simpleservice.document.service.TypeDocumentService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TypeDocumentDaoImpl implements TypeDocumentDao{

    private final EntityManager em;

    @Autowired
    public TypeDocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public TypeDocumentEntity loadByType(String type) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TypeDocumentEntity> criteriaQuery = criteriaBuilder.createQuery(TypeDocumentEntity.class);
        Root<TypeDocumentEntity> typeDocumentEntityRoot = criteriaQuery.from(TypeDocumentEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(typeDocumentEntityRoot.get("type"),type));

        TypedQuery<TypeDocumentEntity> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public TypeDocumentEntity loadByCode(String code) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TypeDocumentEntity> criteriaQuery = criteriaBuilder.createQuery(TypeDocumentEntity.class);
        Root<TypeDocumentEntity> typeDocumentEntityRoot = criteriaQuery.from(TypeDocumentEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(typeDocumentEntityRoot.get("code"),code));

        TypedQuery<TypeDocumentEntity> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<TypeDocumentEntity> loadAllTypeDocuments() {
        TypedQuery<TypeDocumentEntity> query = em.createQuery("SELECT td FROM TypeDocumentEntity td", TypeDocumentEntity.class);
        return query.getResultList();
    }
}
