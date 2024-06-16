package com.expense_manager_api.expenseManager.repository;

import com.expense_manager_api.expenseManager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);

    List<Expense> findByName(String Name);

    List<Expense> findByDateBetween(Date date, Date date2);
}

