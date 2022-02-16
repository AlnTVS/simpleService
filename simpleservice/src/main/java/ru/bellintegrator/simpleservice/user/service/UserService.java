package ru.bellintegrator.simpleservice.user.service;

import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodSaveView;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodsExtendedView;

import java.util.List;

public interface UserService {
    /**
     * Получить список всех пользователей
     *
     * @return {@List<UserForHTTPMethodListView>}
     */
    List<UserForHTTPMethodListView> users();

    /**
     * Получить список всех пользователей
     *
     * @return {@List<UserForHTTPMethodListView>}
     */
    List<UserForHTTPMethodListView> users(UserForHTTPMethodListView user);

    /**
     * Получить юзера по id
     *
     * @param id
     *
     * @return {UserForHTTPMethodsExtendedView}
     */
    UserForHTTPMethodsExtendedView userById(Long id);

    /**
     * Обновить данные юзера по id
     *
     * @param user
     *
     */
    void updateUser(UserForHTTPMethodsExtendedView user);

    void saveUser(UserForHTTPMethodSaveView user);
}
