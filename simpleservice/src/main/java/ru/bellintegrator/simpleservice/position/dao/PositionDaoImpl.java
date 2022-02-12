package ru.bellintegrator.simpleservice.position.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.position.entity.PositionEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class PositionDaoImpl implements PositionDao{

    private final EntityManager em;

    @Autowired
    public PositionDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public PositionEntity findByName(String name) {
        TypedQuery<PositionEntity> query = em.createQuery("SELECT p FROM PositionEntity p WHERE name=:name", PositionEntity.class);
        query.setParameter("name",name);
        return query.getSingleResult();
    }
}
