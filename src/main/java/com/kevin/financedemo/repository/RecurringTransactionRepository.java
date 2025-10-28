package com.kevin.financedemo.repository;

import com.kevin.financedemo.model.Frequency;
import com.kevin.financedemo.model.RecurringTransaction;
import com.kevin.financedemo.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RecurringTransactionRepository extends JpaRepository <RecurringTransaction, UUID> {

    // Find recurring transactions attached to a specific user
    @Query("SELECT rt FROM RecurringTransaction rt WHERE rt.user.id = :userId")
    List<RecurringTransaction> findByUserId(@Param("userId") UUID userId);

    // Find recurring transactions attached to a specific account
    @Query("SELECT rt FROM RecurringTransaction rt WHERE rt.account.id = :accountId")
    List<RecurringTransaction>findByAccountId(@Param("accountId") UUID accountId);

    // Find recurring transactions belonging to a particular category
    @Query("SELECT rt FROM RecurringTransaction rt WHERE rt.category.id = :categoryId")
    List<RecurringTransaction>findByCategoryId(@Param("categoryId") UUID categoryId);

    // Find recurring transactions that meet a particular threshold
    List<RecurringTransaction>findByAmountGreaterThan(BigDecimal amount);
    List<RecurringTransaction>findByAmountLessThan(BigDecimal amount);

    // Group recurring transactions by transaction type
    @Query("SELECT rt FROM RecurringTransaction rt WHERE rt.transactionType = :transactionType")
    List<RecurringTransaction>findByTransactionType(@Param("transactionType") TransactionType transactionType);

    // Group recurring transactions by frequency
    @Query("SELECT rt FROM RecurringTransaction rt WHERE rt.frequency = :frequency")
    List<RecurringTransaction>findByFrequency(@Param("frequency")Frequency frequency);

    // Find recurring transactions whose next run date are within a given range
    List<RecurringTransaction>findByNextRunDateBetween(LocalDate startDate, LocalDate endDate);

    // Find recurring transactions based on their status
    List<RecurringTransaction>findByIsActive(Boolean isActive);

    List<RecurringTransaction>findByNextRunDateBefore(LocalDate date);

    List<RecurringTransaction>findByIsActiveTrueAndNextRunDateBefore(LocalDate date);

    @Query("SELECT rt FROM RecurringTransaction rt WHERE rt.user.id = :userId")
    List<RecurringTransaction>findByUserIdAndIsActive(@Param("userId") UUID userId, boolean isActive);

    List<RecurringTransaction>findByEndDateBetween(LocalDate startDate, LocalDate endDate);
}
