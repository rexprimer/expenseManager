package com.expense_manager_api.expenseManager.controller;

import com.expense_manager_api.expenseManager.dto.CategoryDTO;
import com.expense_manager_api.expenseManager.io.CategoryRequest;
import com.expense_manager_api.expenseManager.io.CategoryResponse;
import com.expense_manager_api.expenseManager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CatgoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO = mapToDTO(categoryRequest);
        categoryDTO = categoryService.saveCategory(categoryDTO);
        return mapToResponse(categoryDTO);
    }

    @GetMapping
    public List<CategoryResponse> readCategories() {
        List<CategoryDTO> listOfCategories = categoryService.getAllCategories();
        return listOfCategories.stream().map(categoryDTO -> mapToResponse(categoryDTO)).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    private CategoryResponse mapToResponse(CategoryDTO categoryDTO) {
        return CategoryResponse.builder()
                .categoryId(categoryDTO.getCategoryId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .categoryIcon(categoryDTO.getCategoryIcon())
                .createdAt(categoryDTO.getCreatedAt())
                .updatedAt(categoryDTO.getUpdatedAt())
                .build();
    }

    private CategoryDTO mapToDTO(CategoryRequest categoryRequest) {
        return CategoryDTO.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .categoryIcon(categoryRequest.getIcon())
                .build();
    }
}
