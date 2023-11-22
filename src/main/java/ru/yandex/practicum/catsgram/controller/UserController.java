package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Set<User> users = new HashSet<>();

    @GetMapping
    public Set<User> getListOfUsers() {
        log.debug("Количество постов {}", users.size());
        return users;
    }

    @PostMapping
    public User addNewUser (@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {
        String email = user.getEmail();
        if (email == null || email.isEmpty() || email.isBlank()) {
            throw new InvalidEmailException();
        } else if (!users.add(user)) {
            throw new UserAlreadyExistException();
        } else {
            log.debug("Добавлен пользователь {}", user);
            return user;
        }
    }

    @PutMapping
    public User addOrUpdateUser(@RequestBody User user) throws InvalidEmailException {
        String email = user.getEmail();
        if (email == null || email.isEmpty() || email.isBlank()) {
            throw new InvalidEmailException();
        } else {
            users.remove(user);
            users.add(user);
            return user;
        }
    }

    private static class UserAlreadyExistException extends Throwable {
    }

    private static class InvalidEmailException extends Throwable {
    }
}

