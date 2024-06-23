package com.expense_manager_api.expenseManager.repository;

import com.expense_manager_api.expenseManager.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
