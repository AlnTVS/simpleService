package ru.bellintegrator.simpleservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
import ru.bellintegrator.simpleservice.user.dao.UserDao;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodsExtendedView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(UserDao dao, MapperFacade mapperFacade) {
        this.userDao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<UserForHTTPMethodListView> users() {
        List<UserEntity> userEntityList = userDao.loadAllUsers();
        return mapperFacade.mapUserEntityToUserViewAsList(userEntityList, UserForHTTPMethodListView.class);
    }

    @Override
    public List<UserForHTTPMethodListView> users(UserForHTTPMethodListView user) {
        List<UserEntity> userEntityList = userDao.loadAllUsersByFilter(user);
        return mapperFacade.mapUserEntityToUserViewAsList(userEntityList, UserForHTTPMethodListView.class);
    }

    @Override
    public UserForHTTPMethodsExtendedView userById(Long id) {
        return mapperFacade.mapUserEntityToUserExtendedView(userDao.loadUserById(id),UserForHTTPMethodsExtendedView.class);
    }
}
