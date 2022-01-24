package ru.bellintegrator.simpleservice.organization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
/**
 * <code>OrganizationRepository</code> расширяет {@link JpaRepository} и {@link JpaSpecificationExecutor} для <code>OrganizationEntity</code>.
 * Позволяет реализовать доступ к данным в БД.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Long>, JpaSpecificationExecutor<OrganizationEntity> {
}
