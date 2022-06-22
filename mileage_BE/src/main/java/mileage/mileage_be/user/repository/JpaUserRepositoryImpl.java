package mileage.mileage_be.user.repository;

import mileage.mileage_be.user.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaUserRepositoryImpl implements UserRepository {
    private final EntityManager em;

    public JpaUserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(String userid) {
        User user = em.find(User.class, userid);
        return Optional.ofNullable(user);
    }

    @Override
    public User pointUp(User user) {
        User UpdatedUser = em.find(User.class, user.getUserId());
        UpdatedUser.setPoint(user.getPoint() + 1);
        return UpdatedUser;
    }

    @Override
    public User pointDown(User user) {
        User UpdatedUser = em.find(User.class, user.getUserId());
        UpdatedUser.setPoint(user.getPoint() - 1);
        return UpdatedUser;
    }
}
