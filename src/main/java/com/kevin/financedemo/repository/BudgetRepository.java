package com.kevin.financedemo.repository;

import com.kevin.financedemo.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    // Return a list of budgets attached to a particular user
    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId")
    List<Budget> findByUserId(@Param("userId") UUID userId);

    // Budgets belonging to a specific category
    @Query("SELECT b FROM Budget b WHERE b.category.id = :categoryId")
    List<Budget> findByCategoryId(@Param("categoryId") UUID categoryId);

    // Budgets that meet a certain threshold
    List<Budget> findByAmountLimitGreaterThan(BigDecimal amount);

    List<Budget> findByAmountLimitLessThan(BigDecimal amount);

    // Budgets by start and end date
    List<Budget> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Budget> findByEndDateBetween(LocalDate startDate, LocalDate endDate);

    // Find budgets active on a given date
    @Query("SELECT b FROM Budget b WHERE :date BETWEEN b.startDate AND b.endDate")
    List<Budget>findByActiveBudgets(@Param("date") LocalDate date);

    // Combined look up
    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.category.id = :categoryId")
    List<Budget>findByUserIdAndCategoryId(@Param("userId") UUID userId, @Param("categoryId") UUID categoryId);

    // Check if user has a budget of a specific category already
    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.category.id = :categoryId")
    Boolean existsByUserIdAndCategoryId(@Param("userId") UUID userId, @Param("categoryId") UUID categoryId);

    // List of all budgets from the first one to be created to the last one to be created
    List<Budget>findAllByOrderByStartDateAsc();
}
