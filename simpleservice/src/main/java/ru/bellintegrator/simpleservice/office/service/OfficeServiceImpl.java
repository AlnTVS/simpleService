package ru.bellintegrator.simpleservice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.common.exception.NotFoundEntityByReceivedParametersException;
import ru.bellintegrator.simpleservice.common.exception.NotFountRequiredParametersException;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.office.dao.OfficeDao;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade) {
        this.officeDao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OfficeForHTTPMethodListView> offices() {

        List<OfficeEntity> officeEntityList = officeDao.loadAllOffices();
        return mapperFacade.mapAsList(officeEntityList, OfficeForHTTPMethodListView.class);
    }

    @Override
    public List<OfficeForHTTPMethodListView> offices(OfficeForHTTPMethodListView officeForHTTPMethodListView) {
        if(officeForHTTPMethodListView.orgId == null) {
            throw new NotFountRequiredParametersException("Parameter orgId is required. But it's null.");
        }
        List<OfficeEntity> officeEntityList = officeDao.loadOfficesByFilter(officeForHTTPMethodListView);
        if (officeEntityList.isEmpty()) {
            throw new NotFoundEntityByReceivedParametersException("DB doesn't contains entity that meets the conditions of filter.");
        }
        return mapperFacade.mapAsList(officeEntityList, OfficeForHTTPMethodListView.class);
    }

    @Override
    public FullOfficeView officeById(Long id) {
        try {
            OfficeEntity officeEntity = officeDao.loadOfficeById(id);
            return mapperFacade.map(officeEntity, FullOfficeView.class);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFountRequiredParametersException("Office with id = " + id + " doesh't exist.");
        }
    }

    @Override
    public void updateOfficeByFullView(FullOfficeView fullOfficeView) {
        officeDao.updateOffice(mapperFacade.map(fullOfficeView, OfficeEntity.class));
    }

    @Override
    public void addNewOffice(FullOfficeView fullOfficeView) {
        officeDao.addNewOffice(mapperFacade.map(fullOfficeView,OfficeEntity.class));
    }
}
