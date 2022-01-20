package ru.bellintegrator.simpleservice.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodsExtendedView;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MapperFacadeImpl implements MapperFacade {
    private final MapperFactory mapperFactory;

    @Autowired
    public MapperFacadeImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }

    public <S, D> List<D> mapUserEntityToUserViewAsList(Iterable<S> source, Class<D> destinationClass) {
        mapperFactory.classMap(UserForHTTPMethodListView.class, UserEntity.class)
                .field("id","id")
                .field("officeId","office.id")
                .field("firstName","firstName")
                .field("secondName","secondName")
                .field("lastName","lastName")
                .field("positions","positions")
                .field("docCode", "document.typeDocument.code")
                .field("citizenshipCode","citizenship.code")
                .register();
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }

    public <S, D> D mapUserEntityToUserView(S sourceObject, Class<D> destinationClass) {
        mapperFactory.classMap(UserForHTTPMethodListView.class, UserEntity.class)
                .field("id","id")
                .field("officeId","office.id")
                .field("firstName","firstName")
                .field("secondName","secondName")
                .field("lastName","lastName")
                .field("positions","positions")
                .field("docCode", "document.typeDocument.code")
                .field("citizenshipCode","citizenship.code")
                .register();
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    public <S, D> D mapOrganizationEntityToUserView(S sourceObject, Class<D> destinationClass) {
        mapperFactory.classMap(OrganizationForHTTPMethodsExtendedView.class, OrganizationEntity.class)
                .field("id","id")
                .field("name","name")
                .field("fullName","fullName")
                .field("inn","inn")
                .field("kpp","kpp")
                .field("phone","phone")
                .field("isActive", "isActive")
                .field("address","address.address")
                .register();
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }
}
