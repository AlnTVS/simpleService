package ru.bellintegrator.simpleservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;
import ru.bellintegrator.simpleservice.user.dao.UserDao;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;

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
//        HashMap<String, Object> map = new HashMap<>();
//        if (user.officeId != null) {
//            map.put("office", Long.valueOf(user.officeId));
//        } else {
//            //throw error not all required parameters
//        }
//        if (user.firstName != null) {
//            map.put("firstName", user.firstName);
//        }
//        if (user.middleName != null) {
//            map.put("middleName", user.middleName);
//        }
//        if (user.lastName != null) {
//            map.put("lastName", user.lastName);
//        }
//        if (user.positions != null) {
//            map.put("positions", user.positions);
//        }
//        if (user.docCode != null) {
//            map.put("docCode", user.docCode);
//        }
//        if (user.citizenshipCode != null) {
//            map.put("citizenshipCode", user.citizenshipCode);
//        }
//        List<UserEntity> userEntityList = userDao.loadAllUsersByFilter(map);
        List<UserEntity> userEntityList = userDao.loadAllUsersByFilter(user);
        List<UserForHTTPMethodListView> userForHTTPMethodListViews = mapperFacade.mapUserEntityToUserViewAsList(userEntityList, UserForHTTPMethodListView.class);
        return userForHTTPMethodListViews;
    }
}
