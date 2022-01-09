package ru.bellintegrator.simpleService.organization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Long> {
}
