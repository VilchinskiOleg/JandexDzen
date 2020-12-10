package by.tms.dao.user;

import by.tms.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class UserDbDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null!");
        }
        entityManager.persist(user);
    }

    @Override
    public Optional<User> getUserById(long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("UserId is not correct!");
        }
        User user = entityManager.find(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public User getUserByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("Login is null!");
        }
        return entityManager.createNamedQuery("User.findByLogin", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public boolean existUserByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("Login is null!");
        }
        long result = (Long) entityManager.createNamedQuery("User.existByLogin")
                .setParameter("login", login)
                .getSingleResult();
        return result > 0;
    }
}
