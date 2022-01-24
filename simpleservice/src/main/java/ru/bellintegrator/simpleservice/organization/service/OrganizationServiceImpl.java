package ru.bellintegrator.simpleservice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.address.repository.AddressRepository;
import ru.bellintegrator.simpleservice.address.repository.specification.AddressSpecification;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.common.exception.NotFountRequiredParametersException;
import ru.bellintegrator.simpleservice.common.exception.NotUniqueDataException;
import ru.bellintegrator.simpleservice.organization.exceptions.OrganizationNotFoundException;
import ru.bellintegrator.simpleservice.organization.repositories.OrganizationRepository;
import ru.bellintegrator.simpleservice.organization.util.OrganizationFilter;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodsExtendedView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    private OrganizationRepository organizationRepository;
    private AddressRepository addressRepository;
    private MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, AddressRepository addressRepository, MapperFacade mapperFacade) {
        this.organizationRepository = organizationRepository;
        this.addressRepository = addressRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OrganizationForHTTPMethodListView> organizations() {
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll();
        return mapperFacade.mapAsList(organizationEntityList, OrganizationForHTTPMethodListView.class);
    }

    @Override
    public List<OrganizationForHTTPMethodListView> organizations(OrganizationForHTTPMethodListView organization) {
        if(organization.name == null) {
            throw new NotFountRequiredParametersException("Parameter \"name\" required, but it is null");
        }
        OrganizationFilter organizationFilter = new OrganizationFilter(organization);
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll(organizationFilter.getSpec());
        return mapperFacade.mapAsList(organizationEntityList, OrganizationForHTTPMethodListView.class);
    }

    @Override
    public OrganizationForHTTPMethodsExtendedView organisationById(Long id) {
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Can't found organization with id = " + id));
        OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView = mapperFacade.mapOrganizationEntityToUserView(organizationEntity, OrganizationForHTTPMethodsExtendedView.class);
        return organizationForHTTPMethodsExtendedView;
    }

    @Override
    public void updateOrganization(OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView) {
        if(organizationForHTTPMethodsExtendedView.id == null || organizationForHTTPMethodsExtendedView.name == null ||
            organizationForHTTPMethodsExtendedView.fullName == null || organizationForHTTPMethodsExtendedView.inn == null ||
            organizationForHTTPMethodsExtendedView.kpp == null || organizationForHTTPMethodsExtendedView.address == null) {
            throw new NotFountRequiredParametersException("Parameters \"id\", \"name\", \"fullName\", \"inn\", \"kpp\", \"address\" required, but one or more is null");
        }
        Long id = Long.valueOf(organizationForHTTPMethodsExtendedView.id);
        organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Can't found organization with id = " + id ));
        OrganizationEntity updatedOrganization = mapperFacade.mapOrganizationEntityToUserView(organizationForHTTPMethodsExtendedView,OrganizationEntity.class);
        Specification<AddressEntity> addressEntitySpecification = Specification.where(AddressSpecification.addressIs(organizationForHTTPMethodsExtendedView.address));
        updatedOrganization.setAddress(addressRepository.findOne(addressEntitySpecification).orElse(new AddressEntity(null, organizationForHTTPMethodsExtendedView.address)));
        try {
            organizationRepository.save(updatedOrganization);
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqueDataException("Some data not unique. " + e.getMessage());
        }
    }

    @Override
    public void addNewOrganization(OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView) {
        if( organizationForHTTPMethodsExtendedView.name == null ||
                organizationForHTTPMethodsExtendedView.fullName == null || organizationForHTTPMethodsExtendedView.inn == null ||
                organizationForHTTPMethodsExtendedView.kpp == null || organizationForHTTPMethodsExtendedView.address == null) {
            throw new NotFountRequiredParametersException("Parameters \"name\", \"fullName\", \"inn\", \"kpp\", \"address\" required, but one or more is null");
        }
        OrganizationEntity newOrganizationEntity = mapperFacade.mapOrganizationEntityToUserView(organizationForHTTPMethodsExtendedView,OrganizationEntity.class);
        Specification<AddressEntity> addressEntitySpecification = Specification.where(AddressSpecification.addressIs(organizationForHTTPMethodsExtendedView.address));
        newOrganizationEntity.setAddress(addressRepository.findOne(addressEntitySpecification).orElse(new AddressEntity(addressRepository.count(), organizationForHTTPMethodsExtendedView.address)));
        try {
            organizationRepository.save(newOrganizationEntity);
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqueDataException("Some data not unique. " + e.getMessage());
        }
    }
}
