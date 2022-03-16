package ru.bellintegrator.simpleservice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.address.repository.AddressRepository;
import ru.bellintegrator.simpleservice.address.repository.specification.AddressSpecification;
import ru.bellintegrator.simpleservice.common.exception.NotFoundEntityByReceivedParametersException;
import ru.bellintegrator.simpleservice.common.exception.NotFountRequiredParametersException;
import ru.bellintegrator.simpleservice.common.exception.NotUniqueDataException;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.office.dao.OfficeDao;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodsView;
import ru.bellintegrator.simpleservice.organization.repositories.OrganizationRepository;

import java.util.List;

/**
 * Реализует интерфейс {@link OfficeService}
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.1
 * @since 13.03.2022
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    /**
     * Используется для доступа сервиса к данным об офисах находящихся в БД
     */
    private final OfficeDao officeDao;
    /**
     * Используется для маппинга между {@link OfficeEntity} и {@link OfficeForHTTPMethodListView} или/и {@link OfficeForHTTPMethodsView}
     */
    private final MapperFacade mapperFacade;
    /**
     * Используется для доступа сервиса к данным об адресах находящихся в БД
     */
    private final AddressRepository addressRepository;

    private final OrganizationRepository organizationRepository;

    /**
     * Создает экземпляр этого класса.
     * Используется аннотация <i>@Autowired</i>, для автозаполнения инъекцией.
     *
     * @param officeDao репозиторий организаций для доступа сервиса к данным по офисам в БД
     * @param mapperFacade используется для маппинга между <code>View</code> и <code>Entity</code>
     * @param addressRepository репозиторий адресов для доступа сервиса к данным по адресам в БД.
     * @param organizationRepository репозиторий организаций для доступа сервиса к данным по организациям в БД.
     */
    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFacade mapperFacade, AddressRepository addressRepository, OrganizationRepository organizationRepository) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.addressRepository = addressRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<OfficeForHTTPMethodListView> offices() {
        List<OfficeEntity> officeEntityList = officeDao.loadAllOffices();
        return mapperFacade.mapAsList(officeEntityList, OfficeForHTTPMethodListView.class);
    }

    @Override
    public List<OfficeForHTTPMethodListView> offices(OfficeForHTTPMethodListView officeForHTTPMethodListView) {
        if (officeForHTTPMethodListView.orgId == null) {
            throw new NotFountRequiredParametersException("Parameter orgId is required. But it's null.");
        }
        List<OfficeEntity> officeEntityList = officeDao.loadOfficesByFilter(officeForHTTPMethodListView);
        if (officeEntityList.isEmpty()) {
            throw new NotFoundEntityByReceivedParametersException("DB doesn't contains entity that meets the conditions of filter.");
        }
        return mapperFacade.mapAsList(officeEntityList, OfficeForHTTPMethodListView.class);
    }

    @Override
        public OfficeForHTTPMethodsView officeById(Long id) {
            try {
                OfficeEntity officeEntity = officeDao.loadOfficeById(id);
                return mapperFacade.map(officeEntity, OfficeForHTTPMethodsView.class);
            } catch (EmptyResultDataAccessException e) {
                throw new NotFoundEntityByReceivedParametersException("Office with id = " + id + " doesn't exist.");
            }
    }

    @Override
    @Transactional
    public void updateOffice(OfficeForHTTPMethodsView officeForHTTPMethodsView) {
        if (officeForHTTPMethodsView.id == null ||
                officeForHTTPMethodsView.name == null ||
                officeForHTTPMethodsView.address == null) {
            throw new NotFountRequiredParametersException(
                    "Office id = " + officeForHTTPMethodsView.id
                            + ", office name = " + officeForHTTPMethodsView.name
                            + ", office address = " + officeForHTTPMethodsView.address
                            + ". This parameters must be filled!");
        }

        OfficeEntity officeEntity;
        try {
            officeEntity = officeDao.loadOfficeById(Long.valueOf(officeForHTTPMethodsView.id));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFountRequiredParametersException("Office with id = " + officeForHTTPMethodsView.id + " doesn't exist.");
        }
        if (!officeEntity.getName().equals(officeForHTTPMethodsView.name)) {
            if (officeDao.isExistOfficeWithName(officeForHTTPMethodsView.name)) {
                throw new NotUniqueDataException("Office with name '" + officeForHTTPMethodsView.name + "' is already in the database.");
            }
            officeEntity.setName(officeForHTTPMethodsView.name);
        }
        Specification<AddressEntity> addressEntitySpecification = Specification.where(AddressSpecification.addressIs(officeForHTTPMethodsView.address));
        officeEntity.setAddress(addressRepository.findOne(addressEntitySpecification).orElse(new AddressEntity(null, officeForHTTPMethodsView.address)));
        if (officeForHTTPMethodsView.phone != null) {
            officeEntity.setPhone(officeForHTTPMethodsView.phone);
        }
        if (officeForHTTPMethodsView.isActive != null) {
            officeEntity.setIsActive(officeForHTTPMethodsView.isActive);
        }

        officeDao.updateOffice(officeEntity);
    }

    @Override
    @Transactional
    public void addNewOffice(OfficeForHTTPMethodsView officeForHTTPMethodsView) {
        if(officeForHTTPMethodsView.orgId == null) {
            throw new NotFountRequiredParametersException("Parameters 'orgId' and 'isActive' must be not null!");
        }
        OfficeEntity officeEntity = new OfficeEntity();
        officeEntity.setOrganization(organizationRepository.findById(Long.valueOf(officeForHTTPMethodsView.orgId))
                .orElseThrow(() -> new NotFoundEntityByReceivedParametersException("Can not create new office for not exist organization. Organization with id = " + officeForHTTPMethodsView.orgId + " not found.")));
        officeEntity.setIsActive(officeForHTTPMethodsView.isActive);
        if(officeForHTTPMethodsView.name != null) {
            if(officeDao.isExistOfficeWithName(officeForHTTPMethodsView.name)){
                throw new NotUniqueDataException("Office with name '" + officeForHTTPMethodsView.name + "' is already in the database.");
            }
            officeEntity.setName(officeForHTTPMethodsView.name);
        }
        if(officeForHTTPMethodsView.address != null) {
            Specification<AddressEntity> addressEntitySpecification = Specification.where(AddressSpecification.addressIs(officeForHTTPMethodsView.address));
            AddressEntity addressEntity = addressRepository.findOne(addressEntitySpecification).orElse(new AddressEntity(null, officeForHTTPMethodsView.address));
            addressRepository.save(addressEntity);
            officeEntity.setAddress(addressEntity);
        }
        if(officeForHTTPMethodsView.phone != null) {
            officeEntity.setPhone(officeForHTTPMethodsView.phone);
        }
        officeDao.addNewOffice(officeEntity);
    }
}
