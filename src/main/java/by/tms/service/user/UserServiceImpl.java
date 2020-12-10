package by.tms.service.user;

import by.tms.dao.user.UserDao;
import by.tms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        if (userDao.existUserByLogin(login)) {
            return Optional.of(userDao.getUserByLogin(login));
        }
        return Optional.empty();
    }
}
