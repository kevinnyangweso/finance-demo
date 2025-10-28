package com.kevin.financedemo.repository;

import com.kevin.financedemo.model.Transaction;
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
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    // find transactions linked to a specific account
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
    List<Transaction> findByAccountId(@Param("accountId") UUID accountId);

    // Find transactions belonging to a particular category
    @Query("SELECT t FROM Transaction t WHERE t.category.id = :categoryId")
    List<Transaction> findByCategoryId(@Param("categoryId") UUID categoryId);

    // find transactions by type e.g. INCOME, EXPENSE
    @Query("SELECT t FROM Transaction t WHERE t.transactionType = :transactionType")
    List<Transaction> findByType(@Param("transactionType") TransactionType type);

    // find transactions by date or date range
    List<Transaction> findByTransactionDate(LocalDate date);
    List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

    // find transactions that meet the threshold
    List<Transaction> findByAmountGreaterThan(BigDecimal amount);
    List<Transaction> findByAmountLessThan(BigDecimal amount);

    // find the 10 most recent transaction
    List<Transaction> findTop10ByOrderByTransactionDateDesc();
}
