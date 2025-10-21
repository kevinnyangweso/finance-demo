package com.kevin.financedemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id", columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID categoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_category_user"))
    private User user;

    @NotNull(message = "Category name cannot be null")
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;

    @Column(name = "color_code", length = 7)
    private String colorCode;

    @Column(name = "icon_name", length = 50)
    private String iconName; // e.g. fa-utensils, mdi-wallet

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = createdAt;

        if (colorCode == null || colorCode.isBlank()) {
            colorCode = generateRandomColor();
        }

        //Assign a default icon if none is provided
        if (iconName == null || iconName.isBlank()) {
            iconName = getDefaultIcon(type);
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    private String generateRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    private String getDefaultIcon(CategoryType type) {
        return type == CategoryType.INCOME ? "fa-wallet" : "fa money-bill";
    }

    // Getter and Setter methods
    public UUID getCategoryId(){return this.categoryId;}

    public User getUser(){return this.user;}

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public CategoryType getType(){return this.type;}
    public void setCategoryType(CategoryType type){this.type = type;}

    public String getColorCode(){return this.colorCode;}
    public void setColorCode(String colorCode){this.colorCode = colorCode;}

    public String getIconName(){return this.iconName;}
    public void setIconName(String iconName){this.iconName = iconName;}

    public OffsetDateTime getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}

    public OffsetDateTime getUpdatedAt(){return this.updatedAt;}
    public void setUpdatedAt(OffsetDateTime updatedAt){this.updatedAt = updatedAt;}

    //the toString method is important for debugging
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", userId=" + (user != null ? user.getUserId() : "null") +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", colorCode='" + colorCode + '\'' +
                ", iconName='" + iconName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}

