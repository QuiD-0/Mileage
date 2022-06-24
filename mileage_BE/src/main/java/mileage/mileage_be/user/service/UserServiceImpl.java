package mileage.mileage_be.user.service;

import lombok.RequiredArgsConstructor;
import mileage.mileage_be.user.domain.User;
import mileage.mileage_be.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User join(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOne(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    @Override
    public User incPoint(String userId) {
        Optional<User> user = findOne(userId);
        return userRepository.pointUp(user.get());
    }

    @Override
    public User decPoint(String userId) {
        Optional<User> user = findOne(userId);
        return userRepository.pointDown(user.get());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
