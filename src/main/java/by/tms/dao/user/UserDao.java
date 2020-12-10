package by.tms.dao.user;

import by.tms.entity.User;
import java.util.Optional;

public interface UserDao {
    void createUser(User user);
    Optional<User> getUserById(long id);
    User getUserByLogin(String login);
    boolean existUserByLogin(String login);
}
