package com.kevin.financedemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.websocket.ClientEndpoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue
    @Column(name = "goal_id")
    private UUID goalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "target_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal targetAmount;

    @Column(name = "current_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal currentAmount = BigDecimal.ZERO;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "description")
    private String description;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @Column(name = "progress_percent", precision = 5, scale = 2)
    private BigDecimal progressPercent;

    @Column(name = "created_at", updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
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

    public Goal(){}

    public Goal(User user, Account account, Category category, String name,
                BigDecimal targetAmount, BigDecimal currentAmount, LocalDate deadline,
                String description, Boolean isCompleted){
        this.user = user;
        this.account = account;
        this.category = category;
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline = deadline;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public UUID getGoalId(){return this.goalId;}

    public User getUser(){return this.user;}
    public void setUser(User user){this.user = user;}

    public Account getAccount(){return this.account;}
    public void setAccount(Account account){this.account = account;}

    public Category getCategory(){return this.category;}
    public void setCategory(Category category){this.category = category;}

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public BigDecimal getTargetAmount(){return this.targetAmount;}
    public void setTargetAmount(BigDecimal targetAmount){this.targetAmount = targetAmount;}

    public BigDecimal getCurrentAmount(){return this.currentAmount;}
    public void setCurrentAmount(BigDecimal currentAmount){this.currentAmount = currentAmount;}

    public LocalDate getDeadline(){return this.deadline;}
    public void setDeadline(LocalDate deadline){this.deadline = deadline;}

    public Boolean getIsCompleted(){return this.isCompleted;}
    public void setIsCompleted(Boolean isCompleted){this.isCompleted = isCompleted;}

    //Helper method for progressive calculation
    public BigDecimal getProgressPercent(){
        if (targetAmount == null || targetAmount.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return currentAmount
                .divide(targetAmount, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }

    public OffsetDateTime getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}

    public OffsetDateTime getUpdatedAt(){return this.updatedAt;}
    public void setUpdatedAt(OffsetDateTime updatedAt){this.updatedAt = updatedAt;}

    @Override
    public String toString(){
        return "Goal{" +
                ", GoalId=" + goalId +
                ", userId=" + (user != null ? user.getUserId() : null) +
                ", accountId=" + (account != null ? account.getAccountId() : null) +
                ", categoryId=" + (category != null ? category.getCategoryId() : null) +
                ", name=" + name +
                ", target_amount=" + targetAmount +
                ", currentAmount=" + currentAmount +
                ", deadline=" + deadline +
                ", description=" + description +
                ", isCompleted=" + isCompleted +
                ", createdAt=" + createdAt +
                ", updateAt=" + updatedAt +
                '}';
    }
}


