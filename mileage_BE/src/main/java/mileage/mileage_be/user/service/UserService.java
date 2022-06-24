package mileage.mileage_be.user.service;

import mileage.mileage_be.advice.exceptions.NotExistActionException;
import mileage.mileage_be.advice.exceptions.UserNotFoundException;
import mileage.mileage_be.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User join(User user);

    Optional<User> findOne(String userId) throws NotExistActionException, UserNotFoundException;

    User incPoint(String userId) throws NotExistActionException, UserNotFoundException;

    User decPoint(String userId) throws NotExistActionException, UserNotFoundException;

    List<User> findAll();
}
