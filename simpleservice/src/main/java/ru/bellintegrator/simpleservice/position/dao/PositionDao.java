package ru.bellintegrator.simpleservice.position.dao;

import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.position.entity.PositionEntity;

public interface PositionDao {
    public PositionEntity findByName(String name);
}
