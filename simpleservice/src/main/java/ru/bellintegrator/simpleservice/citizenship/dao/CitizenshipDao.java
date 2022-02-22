package ru.bellintegrator.simpleservice.citizenship.dao;

import ru.bellintegrator.simpleservice.citizenship.entity.CitizenshipEntity;

import java.util.List;

public interface CitizenshipDao {

    public CitizenshipEntity loadByCode(String code);

    public List<CitizenshipEntity> loadAll();
}
