package com.expense_manager_api.expenseManager.repository;

import com.expense_manager_api.expenseManager.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByUserId(Long id, Pageable page);

    Optional<Expense> findByUserIdAndId(Long id, Long id1);

    List<Expense> findByUserIdAndCategory(Long id, String category, Pageable page);

    List<Expense> findByUserIdAndNameContaining(Long id, String keyword, Pageable page);

    List<Expense> findByUserIdAndDateBetween(Long id, Date startDate, Date endDate, Pageable page);
}

