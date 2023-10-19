package com.carpark.carpark.service;

import com.carpark.carpark.controller.RescourceNotFoundException;
import com.carpark.carpark.model.Reservation;
import com.carpark.carpark.model.User;
import com.carpark.carpark.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    public User updateExistingUser(User updatedUser, Optional<User> existingUser, UserRepository userRepository) throws RescourceNotFoundException {
        return getUser(updatedUser, existingUser, userRepository);
    }

    private static User getUser(User updatedUser, Optional<User> existingUser, UserRepository userRepository) throws RescourceNotFoundException {
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setBirthdate(updatedUser.getBirthdate());
            user.setAddress(updatedUser.getAddress());

            return userRepository.save(user);
        } else {
            throw new RescourceNotFoundException();
        }
    }
}
