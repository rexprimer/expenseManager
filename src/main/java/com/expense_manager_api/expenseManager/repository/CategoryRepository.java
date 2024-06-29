package com.expense_manager_api.expenseManager.repository;

import com.expense_manager_api.expenseManager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByUserId(Long userId);

    Optional<CategoryEntity> findByUserIdAndCategoryId(Long id, String categoryId);

    boolean existsByNameAndUserId(String name, Long userId);

    Optional<CategoryEntity> findByNameAndUserId(String name, Long userId);
}
