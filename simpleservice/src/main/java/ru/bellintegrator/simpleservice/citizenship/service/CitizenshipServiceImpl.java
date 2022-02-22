package ru.bellintegrator.simpleservice.citizenship.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.citizenship.dao.CitizenshipDao;
import ru.bellintegrator.simpleservice.citizenship.view.CitizenshipView;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;

import java.util.List;

@Service
public class CitizenshipServiceImpl implements CitizenshipService{

    private CitizenshipDao citizenshipDao;
    private MapperFacade mapperFacade;

    public CitizenshipServiceImpl(CitizenshipDao citizenshipDao, MapperFacade mapperFacade) {
        this.citizenshipDao = citizenshipDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<CitizenshipView> getAllCountries() {
        return mapperFacade.mapAsList(citizenshipDao.loadAll(),CitizenshipView.class);
    }
}
