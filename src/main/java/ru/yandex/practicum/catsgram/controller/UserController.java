package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Set;


@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Set<User> getListOfUsers() {
        log.debug("Количество постов {}", userService.getListOfUsers().size());
        return userService.getListOfUsers();
    }

    @PostMapping
    public User addNewUser (@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @PutMapping
    public User addOrUpdateUser(@RequestBody User user) {
        return userService.addOrUpdateUser(user);
    }
}

