package com.expense_manager_api.expenseManager.service;

import com.expense_manager_api.expenseManager.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    void deleteCategory(String categoryId);
}
