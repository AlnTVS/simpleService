package ru.bellintegrator.simpleservice.organization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Long>, JpaSpecificationExecutor<OrganizationEntity> {
}
