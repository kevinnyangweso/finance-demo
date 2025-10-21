package com.kevin.financedemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "account_name", nullable = false, length = 100)
    @NotBlank(message = "Account cannot be blank.")
    private String accountName;

    @Column(name = "account_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false, precision = 15, scale = 2)
    @DecimalMin(value = "0.00", inclusive = true, message = "Balance cannot be negative")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false, length = 3)
    @Pattern(regexp = "^[A-Z]{3}$", message = "currency must be a 3-letter ISO code")
    private String currency;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public Account(){}

    public Account (User user,
                    String accountName,
                    AccountType accountType,
                    BigDecimal balance,
                    String currency) {
        this.user = user;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        // createdAt and updatedAt are not included here bcs they will be updated by JPA automatically
    }

    //Getters and Setters

    // account_id
    public UUID getAccountId(){return this.accountId;}

    // user
    public User getUser(){return this.user;}

    // account_name
    public String getAccountName(){return this.accountName;}
    public void setAccountName(String accountName){this.accountName = accountName;}

    // account_type
    public AccountType getAccountType(){return this.accountType;}
    public void setAccountType(AccountType accountType){this.accountType = accountType;}

    // balance
    public BigDecimal getBalance(){return this.balance;}
    public void setBalance(BigDecimal balance){this.balance = balance;}

    // currency
    public String getCurrency(){return this.currency;}
    public void setCurrency(String currency){this.currency = currency;}

    // created_at
    public OffsetDateTime getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}

    // updated_at
    public OffsetDateTime getUpdatedAt(){return this.updatedAt;}
    public void setUpdatedAt(OffsetDateTime updatedAt){this.updatedAt = updatedAt;}

    // for debugging
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}

