package ru.bellintegrator.simpleservice.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;

public interface AddressRepository extends JpaRepository<AddressEntity,Long>, JpaSpecificationExecutor<AddressEntity> {
}
