package ru.bellintegrator.simpleservice.citizenship.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.citizenship.entity.CitizenshipEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CitizenshipDaoImpl implements CitizenshipDao{

    private final EntityManager em;

    @Autowired
    public CitizenshipDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public CitizenshipEntity loadByCode(String code) {
        TypedQuery<CitizenshipEntity> typedQuery = em.createQuery("SELECT c FROM CitizenshipEntity c WHERE c.code=:code",CitizenshipEntity.class);
        typedQuery.setParameter("code",code);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<CitizenshipEntity> loadAll() {
        TypedQuery<CitizenshipEntity> typedQuery = em.createQuery("SELECT c FROM CitizenshipEntity c",CitizenshipEntity.class);
        return typedQuery.getResultList();
    }
}
