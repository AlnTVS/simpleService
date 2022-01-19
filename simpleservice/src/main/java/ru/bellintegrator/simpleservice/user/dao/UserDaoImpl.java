package ru.bellintegrator.simpleservice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
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
        criteriaQuery.select(userEntityRoot);
        if(user.officeId != null) {
            criteriaQuery.where(userEntityRoot.get("office").get("id").in(user.officeId));
            if(user.positions != null) {
//            criteriaQuery.where(userEntityRoot.join("positions").get("name").in(user.positions));
                Join positions = userEntityRoot.join("positions",JoinType.LEFT);
                criteriaQuery.where(positions.get("name").in(user.positions));
            }
            if(user.firstName != null) {
                criteriaQuery.where(criteriaBuilder.and(criteriaQuery.getGroupRestriction()),criteriaBuilder.and(userEntityRoot.get("firstName").in(user.firstName)));
            }
            if(user.secondName != null) {
                criteriaQuery.where(userEntityRoot.get("secondName").in(user.secondName));
            }
            if(user.middleName != null) {
                criteriaQuery.where(userEntityRoot.get("middleName").in(user.middleName));
            }
            if(user.lastName != null) {
                criteriaQuery.where(userEntityRoot.get("lastName").in(user.lastName));
            }
            if(user.docCode != null) {
                criteriaQuery.where(userEntityRoot.get("document").get("typeDocument").get("code").in(user.docCode));
            }
            if(user.citizenshipCode != null) {
                criteriaQuery.where(userEntityRoot.get("citizenship").get("code").in(user.citizenshipCode));
            }
        }

        TypedQuery<UserEntity> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
