package ru.bellintegrator.simpleservice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Реализует интерфейс <code>{@link OfficeDao}</code>
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.1
 * @since 13.03.2022
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    /**
     * Используется для управления сущностями
     */
    private final EntityManager em;

    /**
     * Создает экземпляр этого класса.
     * Используется аннотация <i>@Autowired</i>, для автозаполнения инъекцией.
     *
     * @param em менеджер для управления состояниями сущностей.
     */
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

        Predicate predicate = criteriaBuilder.equal(officeRoot.get("organization").get("id"), officeForHTTPMethodListView.orgId);
        if (officeForHTTPMethodListView.name != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("name"), officeForHTTPMethodListView.name));
        }
        if (officeForHTTPMethodListView.phone != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("phone"), officeForHTTPMethodListView.phone));
        }
        if (officeForHTTPMethodListView.isActive != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("isActive"), officeForHTTPMethodListView.isActive));
        }

        criteriaQuery.where(predicate);
        TypedQuery<OfficeEntity> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public OfficeEntity loadOfficeById(Long id) {
        TypedQuery<OfficeEntity> query = em.createQuery("SELECT o FROM OfficeEntity o LEFT JOIN FETCH o.address a WHERE o.id=:id ", OfficeEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void updateOffice(OfficeEntity officeEntity) {
        em.merge(officeEntity);
        return;
    }

    @Override
    public void addNewOffice(OfficeEntity office) {
        em.persist(office);
        return;
    }

    @Override
    public boolean isExistOfficeWithName(String officeName) {
        TypedQuery<Boolean> query = em.createQuery("SELECT COUNT(o) > 0 FROM OfficeEntity o WHERE o.name=:name ", Boolean.class);
        query.setParameter("name", officeName);
        return query.getSingleResult();
    }
}
