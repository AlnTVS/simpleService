package ru.bellintegrator.simpleservice.citizenship.dao;

import ru.bellintegrator.simpleservice.citizenship.entity.CitizenshipEntity;

public interface CitizenshipDao {

    public CitizenshipEntity loadByCode(String code);
}
