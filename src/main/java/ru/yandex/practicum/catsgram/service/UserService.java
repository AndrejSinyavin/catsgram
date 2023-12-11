package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Set<User> users = new HashSet<>();

    public Set<User> getListOfUsers() {
        return users;
    }

    public User addNewUser (User user) {
        try {
            String email = user.getEmail();
            if (email == null || email.isEmpty() || email.isBlank()) {
                throw new InvalidEmailException("Неверный email");
            } else if (!users.add(user)) {
                throw new UserAlreadyExistException("Такой пользователь уже существует");
            } else {
                log.debug("Добавлен пользователь {}", user);
                return user;
            }
        } catch (UserAlreadyExistException | InvalidEmailException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public User addOrUpdateUser(User user) {
        try {
            String email = user.getEmail();
            if (email == null || email.isEmpty() || email.isBlank()) {
                throw new InvalidEmailException("Неверный email");
            } else {
                users.remove(user);
                users.add(user);
                return user;
            }
        } catch (InvalidEmailException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static class UserAlreadyExistException extends Throwable {
        public UserAlreadyExistException(String str) {
        }
    }

    private static class InvalidEmailException extends Throwable {
        public InvalidEmailException(String wrongEmail) {
        }
    }
}
