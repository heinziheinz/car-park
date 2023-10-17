package com.carpark.carpark.population;

import com.carpark.carpark.model.User;
import com.carpark.carpark.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Populator {
    @Bean
    public ApplicationRunner initializeDatabase(UserRepository userRepository) {
        return args -> {
            createUsers(userRepository);

        };
    }

    private void createUsers(UserRepository userRepository){
        User user = new User("Karl",  LocalDate.of(1999, 12, 1), "Daheim" );
        user = userRepository.save(user);

    }
}
