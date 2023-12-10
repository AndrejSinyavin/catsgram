package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Set<User> users = new HashSet<>();

    public Set<User> getListOfUsers() {
        return users;
    }

    public User addNewUser (User user) {
        try {
            if (user.getEmail().isBlank()) {
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
            if (user.getEmail().isBlank()) {
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

    public User findUserByEmail(String email) {
        Optional<User> result = users.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
        return result.orElse(null);
    }

    private static class UserAlreadyExistException extends RuntimeException {
        public UserAlreadyExistException(String str) {
            super(str);
        }
    }

    private static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String wrongEmail) {
            super(wrongEmail);
        }
    }
}
