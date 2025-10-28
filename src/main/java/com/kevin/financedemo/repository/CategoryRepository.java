package com.kevin.financedemo.repository;

import com.kevin.financedemo.model.Category;
import com.kevin.financedemo.model.CategoryType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    //avoid duplicate category names
    Boolean existsByName(String name);

    //list all categories belonging to a specific type e.g.EXPENSE: Rent, Food, Transport
    List<Category>findByType(CategoryType type);

    //list all categories created by a specific users
    @Query("SELECT c FROM Category c WHERE c.user.id = :userId")
    List<Category>findByUserId(@Param("userId") UUID userId);

    //check for duplicate category name withing the same user
    @Query("SELECT c FROM Category c WHERE c.user.id = :userId")
    Boolean existsByUserIdAndName(@Param("userId")UUID userId, String name);

    //find category by name(for linking transactions
    Optional<Category> findByName(String name);

    //find all categories ordered by name(useful for dropdowns)
    List<Category>findAllByOrderByNameAsc();
}
