package com.kevin.financedemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "recurring_transactions")
public class RecurringTransaction {

    @Id
    @GeneratedValue
    @Column(name = "recurring_id")
    private UUID recurringId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    @DecimalMin(value = "0.00")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency", nullable = false)
    private Frequency frequency;

    @Column(name = "next_run_date", nullable = false)
    private LocalDate nextRunDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    public RecurringTransaction(){}

    public RecurringTransaction(User user, Account account, Category category, BigDecimal amount,
                                String description, TransactionType transactionType, Frequency frequency,
                                LocalDate nextRunDate, LocalDate endDate, Boolean isActive){
        this.user = user;
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
        this.frequency = frequency;
        this.nextRunDate = nextRunDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public UUID getRecurringId(){return this.recurringId;}

    public User getUser(){return this.user;}
    public void setUser(User user){this.user = user;}

    public Account getAccount(){return this.account;}
    public void setAccount(Account account){this.account = account;}

    public Category getCategory(){return this.category;}
    public void setCategory(Category category){this.category = category;}

    public BigDecimal getAmount(){return this.amount;}
    public void setAmount(BigDecimal amount){this.amount = amount;}

    public TransactionType getTransactionType(){return this.transactionType;}
    public void setTransactionType(TransactionType transactionType){this.transactionType = transactionType;}

    public Frequency getFrequency(){return this.frequency;}
    public void setFrequency(Frequency frequency){this.frequency = frequency;}

    public LocalDate getNextRunDate(){return this.nextRunDate;}
    public void setNextRunDate(LocalDate nextRunDate){this.nextRunDate =  nextRunDate;}

    public LocalDate getEndDate(){return this.endDate;}
    public void setEndDate(LocalDate endDate){this.endDate = endDate;}

    public Boolean getIsActive(){return this.isActive;}
    public void setIsActive(Boolean isActive){this.isActive = isActive;}

    public OffsetDateTime getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}

    public OffsetDateTime getUpdatedAt(){return this.updatedAt;}
    public void setUpdatedAt(OffsetDateTime updatedAt){this.updatedAt = updatedAt;}

    public String toString(){
        return "RecurringTransaction{" +
                ", recurringId=" + recurringId +
                ", userId=" + (user != null ? user.getUserId() : null) +
                ", accountId=" + (account != null ? account.getAccountId() : null) +
                ", categoryId=" + (category != null ? category.getCategoryId() : null) +
                ", amount=" + amount +
                ", description=" + description +
                ", transactionType=" + transactionType +
                ", frequency=" + frequency +
                ", nextRunDate=" + nextRunDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

