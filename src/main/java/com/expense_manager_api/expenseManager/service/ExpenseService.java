package com.expense_manager_api.expenseManager.service;

import com.expense_manager_api.expenseManager.model.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpenses();

    Expense getExpenseById(long id);

    Expense saveExpense(Expense expense);
    Expense updateExpense(Expense expense);
    void deleteExpenseByID(long id);

    List<Expense> getExpensesByCategory(String category);
    List<Expense> getExpensesByName(String expenseName);
    List<Expense> getExpensedByDateBetween(Date startDate, Date endDate);

}
