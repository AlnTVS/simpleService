package ru.bellintegrator.simpleservice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.position.entity.PositionEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<UserEntity> loadAllUsers() {
        TypedQuery<UserEntity> query = em.createQuery("SELECT DISTINCT u FROM UserEntity u JOIN FETCH u.document d JOIN FETCH d.typeDocument JOIN FETCH u.citizenship c JOIN FETCH u.positions p", UserEntity.class);
        return query.getResultList();
    }

    @Override
    public List<UserEntity> loadAllUsersByFilter(UserForHTTPMethodListView user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(userEntityRoot).distinct(true);

        Predicate predicate = criteriaBuilder.equal(userEntityRoot.get("office").get("id"),user.officeId);
        if (user.firstName != null) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(userEntityRoot.get("firstName"),user.firstName));
        }
        if (user.middleName != null) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(userEntityRoot.get("middleName"),user.middleName));
        }
        if (user.lastName != null) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(userEntityRoot.get("lastName"),user.lastName));
        }
        if (user.positions != null) {
            userEntityRoot.fetch("positions");
            predicate = criteriaBuilder.and(predicate,userEntityRoot.join("positions",JoinType.LEFT).get("name").in(user.positions));
        }
        if (user.docCode != null) {
            userEntityRoot.fetch("document").fetch("typeDocument");
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(userEntityRoot.get("document").get("typeDocument").get("code"),user.docCode));
        }
        if (user.citizenshipCode != null) {
            userEntityRoot.fetch("citizenship");
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(userEntityRoot.get("citizenship").get("code"),user.citizenshipCode));
        }

        criteriaQuery.where(predicate);
        TypedQuery<UserEntity> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
