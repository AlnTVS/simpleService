package ru.bellintegrator.simpleservice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.organization.entity.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.organization.exeptions.OrganizationNotFoundException;
import ru.bellintegrator.simpleservice.organization.repositories.OrganizationRepository;
import ru.bellintegrator.simpleservice.organization.view.FullOrganizationView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationView;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    private OrganizationRepository organizationRepository;
    private MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, MapperFacade mapperFacade){
        this.organizationRepository = organizationRepository;
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
    public List<OrganizationView> organizations(Specification<OrganizationEntity> spec) {
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll(spec);
        return mapperFacade.mapAsList(organizationEntityList,OrganizationView.class);
    }

    @Override
    public FullOrganizationView organisationById(Long id) {
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Can't found organization with id = " + id));
        return mapperFacade.map(organizationEntity,FullOrganizationView.class);
    }
}
