package com.example.springsecurity.repository;

import com.example.springsecurity.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByCategoryName(String categoryName);
}
