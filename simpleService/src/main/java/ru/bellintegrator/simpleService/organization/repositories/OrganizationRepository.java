package ru.bellintegrator.simpleService.organization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Long>, JpaSpecificationExecutor<OrganizationEntity> {
}
