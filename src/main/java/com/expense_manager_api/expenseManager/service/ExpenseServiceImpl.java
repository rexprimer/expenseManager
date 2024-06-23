package com.expense_manager_api.expenseManager.service;

import com.expense_manager_api.expenseManager.exceptions.ResourceNotFoundException;
import com.expense_manager_api.expenseManager.model.Expense;
import com.expense_manager_api.expenseManager.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepo;
    private final UserService userService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepo, UserService userService) {
        this.expenseRepo = expenseRepo;
        this.userService = userService;
    }

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepo.findByUserId(userService.getLoggedInUser().getId(), page);
    }

    @Override
    public Expense getExpenseById(Long id){
        Optional<Expense> expense = expenseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if (expense.isPresent()) {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the id "+id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepo.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense){
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        return expenseRepo.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        return expenseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page);
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
        return expenseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page);
    }

    @Override
    public List<Expense> readByDate(java.util.Date startDate, java.util.Date endDate, Pageable page) {
        return List.of();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {

        if (startDate == null) {
            startDate = new Date(0);
        }

        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }

        return expenseRepo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate, endDate, page);
    }

}