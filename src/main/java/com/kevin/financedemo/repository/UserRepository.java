package com.kevin.financedemo.repository;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kevin.financedemo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
