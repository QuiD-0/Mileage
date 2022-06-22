package mileage.mileage_be.user.repository;

import mileage.mileage_be.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String userid);
    User pointUp(User user);
    User pointDown(User user);
}
