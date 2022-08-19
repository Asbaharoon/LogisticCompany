package com.logisticcompany.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logisticcompany.team4.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

}
