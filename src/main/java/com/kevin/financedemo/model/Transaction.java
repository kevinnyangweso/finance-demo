package com.kevin.financedemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private UUID transactionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.00", message = "Amount cannot be negative")
    @Digits(integer = 13, fraction = 2, message = "Amount can have up to 2 decimal places")
    private BigDecimal amount;

    @NotNull(message = "Transaction type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", length = 10)
    private TransactionType transactionType;

    private String description;

    @NotNull
    @Column(name = "transaction_date", columnDefinition = "Date of transaction")
    private LocalDate transactionDate = LocalDate.now();

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    //automatically update timestamp before saving
    @PreUpdate
    public void preUpdate(){
        updatedAt = OffsetDateTime.now();
    }

    // Default Constructor
    public Transaction(){}

    // Customized Constructor
    public Transaction(Account account, BigDecimal amount,
                       TransactionType transactionType, LocalDate transactionDate){
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters
    // transactionId
    public UUID getTransactionId(){return this.transactionId;}

    // account
    public Account getAccount(){return this.account;}

    // category
    public Category getCategory(){return this.category;}

    // amount
    public BigDecimal getAmount(){return this.amount;}
    public void setAmount(BigDecimal amount){this.amount = amount;}

    // transactionType
    public TransactionType getTransactionType(){return this.transactionType;}
    public void setTransactionType(TransactionType transactionType){this.transactionType = transactionType;}

    // description
    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}

    // Transaction date
    public LocalDate getTransactionDate(){return this.transactionDate;}
    public void setTransactionDate(LocalDate transactionDate){this.transactionDate = transactionDate;}

    // created time
    public OffsetDateTime getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}

    // updated time
    public OffsetDateTime getUpdatedAt(){return this.updatedAt;}
    public void setUpdatedAt(OffsetDateTime updatedAt){this.updatedAt = updatedAt;}

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + (account != null ? account.getAccountId() : "null") +
                ", categoryId=" + (category != null ? category.getCategoryId() : "null") +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}


