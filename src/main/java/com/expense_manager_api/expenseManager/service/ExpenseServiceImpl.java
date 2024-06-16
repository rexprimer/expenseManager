package com.expense_manager_api.expenseManager.service;

import com.expense_manager_api.expenseManager.model.Expense;
import com.expense_manager_api.expenseManager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

//    @Override
//    public List<Expense> getAllExpenses() {
//        return List.of((Expense) expenseRepository.findAll());
//    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll().stream().toList();
    }


    @Override
    public Expense getExpenseById(long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpenseByID(long id) {
    expenseRepository.deleteById(id);
    }

    @Override
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category).stream().toList();
    }

    @Override
    public List<Expense> getExpensesByName(String expenseName) {
        return expenseRepository.findByName(expenseName).stream().toList();
    }

    @Override
    public List<Expense> getExpensedByDateBetween(Date startDate, Date endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate).stream().toList();
    }

}
