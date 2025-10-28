package com.kevin.financedemo.repository;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kevin.financedemo.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail (String email);
    Optional <User> findByUsername (String username);
    Boolean existsByEmail (String email);
    Boolean existsByUsername (String username);
}
