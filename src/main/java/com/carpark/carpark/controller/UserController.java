package com.carpark.carpark.controller;


import com.carpark.carpark.model.Car;
import com.carpark.carpark.model.User;
import com.carpark.carpark.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/name/{name}")
    List<User> findAllByType(@PathVariable String name) {
        return userRepository.findAllByName(name);
    }
    @PostMapping
    User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/id/{id}")
    User findById(@PathVariable long id) throws RescourceNotFoundException {
        return userRepository.findById(id).orElseThrow(RescourceNotFoundException::new);
    }

    @PutMapping("/{id}")
    User update(@PathVariable long id, @RequestBody User updatedUser) throws RescourceNotFoundException {
        Optional<User> existingUser = userRepository.findById(id);

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
