package mileage.mileage_be.user.controller;

import lombok.RequiredArgsConstructor;
import mileage.mileage_be.advice.exceptions.NotExistActionException;
import mileage.mileage_be.advice.exceptions.UserNotFoundException;
import mileage.mileage_be.user.domain.User;
import mileage.mileage_be.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final UserService userService;

    @GetMapping
    public List<User> allUsersPoint() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User allUsersPoint(@PathVariable(name = "userId") String userId) throws UserNotFoundException, NotExistActionException {
        return userService.findOne(userId).orElseThrow(() -> new UserNotFoundException());
    }
}
