package ru.bellintegrator.simpleservice.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.simpleservice.user.service.UserService;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodListView;
import ru.bellintegrator.simpleservice.user.view.UserForHTTPMethodsExtendedView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "UserController", description = "Управление информацией о пользователях")
@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Получить список всех пользователей", httpMethod = "POST")
    @PostMapping("/list")
    public List<UserForHTTPMethodListView> users(@RequestBody(required = false) UserForHTTPMethodListView userView) {
        if(userView == null) {
        return userService.users();
        }
        return userService.users(userView);
    }

    @ApiOperation(value = "Получить пользователя по id", httpMethod = "GET")
    @GetMapping("/{id}")
    public UserForHTTPMethodsExtendedView userById(@PathVariable Long id) {
        return userService.userById(id);
    }

    @ApiOperation(value = "Обновить информацию по пользователю", httpMethod = "POST")
    @PostMapping("/update")
    public void updateUserById(@RequestBody UserForHTTPMethodsExtendedView userView) {
        userService.updateUser(userView);
        return;
    }

}
