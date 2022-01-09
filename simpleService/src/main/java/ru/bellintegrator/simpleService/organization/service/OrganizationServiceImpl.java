package ru.bellintegrator.simpleService.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleService.organization.entity.mapper.MapperFacade;
import ru.bellintegrator.simpleService.organization.repositories.OrganizationRepository;
import ru.bellintegrator.simpleService.organization.view.OrganizationView;

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
}
