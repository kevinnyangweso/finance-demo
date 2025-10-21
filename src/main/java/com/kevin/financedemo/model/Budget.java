package com.kevin.financedemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue
    @Column(name = "budget_id")
    private UUID budgetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "amount_limit", precision = 15, scale = 2, nullable = false)
    @DecimalMin(value = "0.00")
    private BigDecimal amountLimit;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "currency", length = 10, nullable = false)
    private String currency = "USD";

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = OffsetDateTime.now();
    }

    //Default constructor
    public Budget(){}

    //Customized constructor
    public Budget(Category category, User user, BigDecimal amountLimit, LocalDate startDate,
                  LocalDate endDate, String currency) {
        this.category = category;
        this.user = user;
        this.amountLimit = amountLimit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currency = currency;
    }

    //getters and setters

    // budgetId
    public UUID getBudgetId(){return this.budgetId;}

    // user
    public User getUser(){return this.user;}

    // category
    public Category getCategory() {return this.category;}

    //amountLimit
    public BigDecimal getAmountLimit(){return this.amountLimit;}
    public void setAmountLimit(BigDecimal amountLimit){this.amountLimit = amountLimit;}

    //startDate
    public LocalDate getStartDate(){return this.startDate;}
    public void setStartDate(LocalDate startDate){this.startDate = startDate;}

    //endDate
    public LocalDate getEndDate(){return this.endDate;}
    public void setEndDate(LocalDate endDate){this.endDate = endDate;}

    //currency
    public String getCurrency(){return this.currency;}
    public void setCurrency(String currency){this.currency = currency;}

    //createdAt
    public OffsetDateTime getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}

    //updatedAt
    public OffsetDateTime getUpdatedAt(){return this.updatedAt;}
    public void setUpdatedAt(OffsetDateTime updatedAt){this.updatedAt = updatedAt;}

    // toString method for debugging
    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", userId=" + (user != null ? user.getUserId() : "null") +
                ", categoryId=" + (category != null ? category.getCategoryId() : "null") +
                ", amountLimit=" + amountLimit +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currency='" + currency + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
