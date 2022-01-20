package ru.bellintegrator.simpleservice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.address.repository.AddressRepository;
import ru.bellintegrator.simpleservice.address.repository.specification.AddressSpecification;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.organization.exeptions.OrganizationNotFoundException;
import ru.bellintegrator.simpleservice.organization.repositories.OrganizationRepository;
import ru.bellintegrator.simpleservice.organization.util.OrganizationFilter;
import ru.bellintegrator.simpleservice.organization.view.FullOrganizationView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationView;

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
    public void add(OrganizationView organizationView) {

    }

    @Override
    public List<OrganizationView> organizations() {
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll();
        return mapperFacade.mapAsList(organizationEntityList,OrganizationView.class);
    }

    @Override
    public List<OrganizationView> organizations(OrganizationView organization) {
        OrganizationFilter organizationFilter = new OrganizationFilter(organization);
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll(organizationFilter.getSpec());
        return mapperFacade.mapAsList(organizationEntityList,OrganizationView.class);
    }

    @Override
    public FullOrganizationView organisationById(Long id) {
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Can't found organization with id = " + id));
        FullOrganizationView fullOrganizationView = mapperFacade.mapOrganizationEntityToUserView(organizationEntity,FullOrganizationView.class);
        return fullOrganizationView;
    }

    @Override
    public void updateOrganizationByFullView(FullOrganizationView fullOrganizationView) {
        Long id = Long.valueOf(fullOrganizationView.id);
        organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Can't found organization with id = " + id));
        OrganizationEntity updatedOrganization = mapperFacade.mapOrganizationEntityToUserView(fullOrganizationView,OrganizationEntity.class);
        Specification<AddressEntity> addressEntitySpecification = Specification.where(AddressSpecification.addressIs(fullOrganizationView.address));
        updatedOrganization.setAddress(addressRepository.findOne(addressEntitySpecification).orElse(new AddressEntity(null, fullOrganizationView.address)));
        organizationRepository.save(updatedOrganization);
    }

    @Override
    public void addNewOrganization(FullOrganizationView fullOrganizationView) {
        OrganizationEntity newOrganizationEntity = mapperFacade.mapOrganizationEntityToUserView(fullOrganizationView,OrganizationEntity.class);
        organizationRepository.save(newOrganizationEntity);
    }
}
