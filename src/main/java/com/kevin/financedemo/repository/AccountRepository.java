package com.kevin.financedemo.repository;

import com.kevin.financedemo.model.Account;
import com.kevin.financedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository <Account, UUID> {

    //Avoid duplicates(check if account already exists)
    Boolean existsByAccountName(String accountName);

    //List all accounts belonging to the user
    List<Account> findByUser(User user);

    //Retrieve an account by its name
    Optional<Account> findByAccountName(String accountName);

    //Check if a user already owns an account with that same name
    @Query("SELECT a FROM Account a WHERE a.user.id = :userId")
    Boolean existsByUserIdAndAccountName(@Param("userId")UUID userId, String accountName);
}
