package com.expense_manager_api.expenseManager.service;

import com.expense_manager_api.expenseManager.dto.UserModel;
import com.expense_manager_api.expenseManager.model.User;

    public interface UserService {

        User createUser(UserModel user);

        User readUser();

        User updateUser(UserModel user);

        void deleteUser();

        User getLoggedInUser();
}
