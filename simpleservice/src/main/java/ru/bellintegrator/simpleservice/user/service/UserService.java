package ru.bellintegrator.simpleservice.user.service;

import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;

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
}
