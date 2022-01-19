package ru.bellintegrator.simpleservice.user.dao;

import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * Получить все объекты UserEntities
     *
     * @return
     */
    public List<UserEntity> loadAllUsers();


    /**
     * Получить все объекты UserEntities по фильтру
     *
     * @return
     */
    public List<UserEntity> loadAllUsersByFilter(UserForHTTPMethodListView user);
}
