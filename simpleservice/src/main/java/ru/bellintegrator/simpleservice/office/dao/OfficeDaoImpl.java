package ru.bellintegrator.simpleservice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OfficeEntity> loadAllOffices() {
        TypedQuery<OfficeEntity> query = em.createQuery("SELECT o FROM OfficeEntity o", OfficeEntity.class);
        return query.getResultList();
    }

    @Override
    public List<OfficeEntity> loadOfficesByFilter(OfficeForHTTPMethodListView officeForHTTPMethodListView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<OfficeEntity> criteriaQuery = criteriaBuilder.createQuery(OfficeEntity.class);
        Root<OfficeEntity> officeRoot = criteriaQuery.from(OfficeEntity.class);
        criteriaQuery.select(officeRoot).distinct(true);

        Predicate predicate = criteriaBuilder.equal(officeRoot.get("orgId"),officeForHTTPMethodListView.orgId);
        if (officeForHTTPMethodListView.name != null) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(officeRoot.get("name"),officeForHTTPMethodListView.name));
        }
        if (officeForHTTPMethodListView.phone != null) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(officeRoot.get("phone"),officeForHTTPMethodListView.phone));
        }
        if (officeForHTTPMethodListView.isActive != null) {
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(officeRoot.get("isActive"),officeForHTTPMethodListView.isActive));
        }

        criteriaQuery.where(predicate);
        TypedQuery<OfficeEntity> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public OfficeEntity loadOfficeById(Long id) {
        TypedQuery<OfficeEntity> query = em.createQuery("SELECT o FROM OfficeEntity o JOIN FETCH o.address a WHERE o.id=:id ", OfficeEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public void updateOffice(OfficeEntity officeEntity) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<OfficeEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(OfficeEntity.class);
        Root<OfficeEntity> officeEntityRoot = criteriaUpdate.from(OfficeEntity.class);
        if (officeEntity.getName() != null) {
            criteriaUpdate.set("name",officeEntity.getName());
        } else {
            //throw error not all required parameters
        }
        if (officeEntity.getAddress() != null) {
            criteriaUpdate.set("address",officeEntity.getAddress());
        }
        if (officeEntity.getPhone() != null) {
            criteriaUpdate.set("phone",officeEntity.getPhone());
        }
        if (officeEntity.getIsActive() != null) {
            criteriaUpdate.set("isActive",officeEntity.getIsActive());
        }
        if(officeEntity.getId() != null) {
            criteriaUpdate.where(criteriaBuilder.equal(officeEntityRoot.get("id"),officeEntity.getId()));
        } else {
            //throw error not all required parameters
        }
        em.createQuery(criteriaUpdate).executeUpdate();
        return;
    }

    //Native query
    @Transactional
    @Override
    public void addNewOffice(OfficeEntity office) {
        em.createNativeQuery("INSERT INTO office (organization_id, name, address_id, phone, is_active) VALUES (?,?,?,?,?)").
                setParameter(1,office.getOrgId()).
                setParameter(2,office.getName()).
                setParameter(3,office.getAddress().getId()).
                setParameter(4,office.getPhone()).
                setParameter(5,office.getIsActive()).
                executeUpdate();
    }
}
