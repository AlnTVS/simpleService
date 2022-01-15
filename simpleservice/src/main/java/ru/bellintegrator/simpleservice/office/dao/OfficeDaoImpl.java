package ru.bellintegrator.simpleservice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
import ru.bellintegrator.simpleservice.office.view.OfficeView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
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
    public List<OfficeEntity> loadOfficesByFilter(OfficeView officeView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<OfficeEntity> criteriaQuery = criteriaBuilder.createQuery(OfficeEntity.class);
        Root<OfficeEntity> officeRoot = criteriaQuery.from(OfficeEntity.class);
        criteriaQuery.select(officeRoot);
        criteriaQuery.where(officeRoot.get("orgId").in(officeView.orgId));
        if (officeView.name != null) {
            criteriaQuery.where(officeRoot.get("name").in(officeView.name));
        }
        if (officeView.phone != null) {
            criteriaQuery.where(officeRoot.get("phone").in(officeView.phone));
        }
        if (officeView.isActive != null) {
            criteriaQuery.where(officeRoot.get("isActive").in(officeView.isActive));
        }
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
        } else {
            //throw error not all required parameters
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
}
