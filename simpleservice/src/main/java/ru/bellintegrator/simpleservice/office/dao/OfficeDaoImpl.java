package ru.bellintegrator.simpleservice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao{

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OfficeEntity> offices() {
        TypedQuery<OfficeEntity> query = em.createQuery("SELECT o FROM OfficeEntity o",OfficeEntity.class);
        return query.getResultList();
    }
}
