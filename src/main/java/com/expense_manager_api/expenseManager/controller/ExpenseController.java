package com.expense_manager_api.expenseManager.controller;

import com.expense_manager_api.expenseManager.model.Expense;
import com.expense_manager_api.expenseManager.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expenceManger")
public class ExpenseController {

    public final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public List<Expense> getExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping("/expenses")
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @PostMapping("/expenses/update")
    public Expense updateExpense(@RequestBody Expense expense) {
        return expenseService.updateExpense(expense);
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@RequestParam("id") long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam("id") Long id) {
        expenseService.deleteExpenseByID(id);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam("category") String category) {
        return expenseService.getExpensesByCategory(category);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam("name") String name) {
        return expenseService.getExpensesByName(name);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpenseByDateBetween (@RequestParam Date from, @RequestParam Date to) {
        return expenseService.getExpensedByDateBetween(from, to);
    }

}
