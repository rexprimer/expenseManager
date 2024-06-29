package com.expense_manager_api.expenseManager.service;

import com.expense_manager_api.expenseManager.dto.CategoryDTO;
import com.expense_manager_api.expenseManager.dto.UserDTO;
import com.expense_manager_api.expenseManager.entity.CategoryEntity;
import com.expense_manager_api.expenseManager.entity.User;
import com.expense_manager_api.expenseManager.exceptions.ItemExistsException;
import com.expense_manager_api.expenseManager.exceptions.ResourceNotFoundException;
import com.expense_manager_api.expenseManager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final UserService userService;


    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryEntity> list = categoryRepository.findByUserId(userService.getLoggedInUser().getId());
        return list.stream().map(categoryEntity -> mapToDTO(categoryEntity)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        boolean isCategoryPresent = categoryRepository.existsByNameAndUserId(categoryDTO.getName(), userService.getLoggedInUser().getId());
        if (isCategoryPresent) {
            throw new ItemExistsException("Category is already present for the name "+categoryDTO.getName());
        }
        CategoryEntity newCategory = mapToEntity(categoryDTO);
        newCategory = categoryRepository.save(newCategory);
        return mapToDTO((CategoryEntity) newCategory);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByUserIdAndCategoryId(userService.getLoggedInUser().getId(), categoryId);
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Category not found for the id "+categoryId);
        }
        categoryRepository.delete(optionalCategory.get());
    }

    private CategoryEntity mapToEntity(CategoryDTO categoryDTO) {
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .categoryIcon(categoryDTO.getCategoryIcon())
                .categoryId(UUID.randomUUID().toString())
                .user(userService.getLoggedInUser())
                .build();
    }

    /**
     * Mapper method to convert Category entity to Category DTO
     * @param categoryEntity
     * @return CategoryDTO
     * */
    private CategoryDTO mapToDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .categoryId(categoryEntity.getCategoryId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .categoryIcon(categoryEntity.getCategoryIcon())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .user(mapToUserDTO(categoryEntity.getUser()))
                .build();
    }

    /**
     * Mapper method to convert User entity to User DTO
     * @param user
     * @return UserDTO
     * */
    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
