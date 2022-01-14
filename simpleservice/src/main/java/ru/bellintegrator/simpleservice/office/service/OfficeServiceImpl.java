package ru.bellintegrator.simpleservice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.office.dao.OfficeDao;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.office.view.OfficeView;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService{

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade) {
        this.officeDao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OfficeView> offices() {
        List<OfficeEntity> officeEntityList = officeDao.offices();
        return mapperFacade.mapAsList(officeEntityList,OfficeView.class);
    }
}
