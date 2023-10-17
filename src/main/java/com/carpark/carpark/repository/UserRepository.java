package com.carpark.carpark.repository;

import com.carpark.carpark.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
