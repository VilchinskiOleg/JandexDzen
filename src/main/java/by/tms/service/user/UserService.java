package by.tms.service.user;

import by.tms.entity.User;
import java.util.Optional;

public interface UserService {
    void createUser(User user);
    Optional<User> getUserById(long userId);
    Optional<User> getUserByLogin(String login);
}
