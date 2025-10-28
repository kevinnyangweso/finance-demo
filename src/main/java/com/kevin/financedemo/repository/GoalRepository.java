package com.kevin.financedemo.repository;

import com.kevin.financedemo.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    // Find goals attached to a specific user
    @Query("SELECT g FROM Goal g WHERE g.user.id = :userId")
    List<Goal> findByUserID(@Param("userId") UUID userId);

    // Find goals attached to a certain account
    @Query("SELECT g FROM Goal g WHERE g.account.id = :accountId")
    List<Goal>findByAccountId(@Param("accountId") UUID accountId);

    // Find goals attached to a particular category
    @Query("SELECT g FROM Goal g WHERE g.category.id = :categoryId")
    List<Goal>findByCategoryId(@Param("categoryId") UUID categoryId);

    // Find goals that meet a particular threshold - both targetAmount and currentAmount
    List<Goal>findByTargetAmountGreaterThan(BigDecimal targetAmount);
    List<Goal>findByTargetAmountLessThan(BigDecimal targetAmount);

    List<Goal>findByCurrentAmountGreaterThan(BigDecimal currentAmount);
    List<Goal>findByCurrentAmountLessThan(BigDecimal currentAmount);

    // List of all the goals by name
    List<Goal>findByNameContainingIgnoreCase(String name);

    // Find goals whose deadline are within a given range
    List<Goal>findByDeadlineBetween(LocalDate startDate, LocalDate endDate);

    // Find goals based on their completion status
    List<Goal>findByIsCompleted(boolean isComplete);

    // Find goals by UserId and Completion status
    @Query("SELECT g FROM Goal g WHERE g.user.id = :userId")
    List<Goal>findByUserIdAndIsCompleted(@Param("userId") UUID userId, boolean isCompleted);

    // Find goals by userId and deadline
    @Query("SELECT g FROM Goal g WHERE g.user.id = :userId")
    List<Goal>findByUserIdAndDeadlineBefore(@Param("userId") UUID userId, LocalDate date);
}
