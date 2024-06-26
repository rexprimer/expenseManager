package com.expense_manager_api.expenseManager.service;

import com.expense_manager_api.expenseManager.entity.User;
import com.expense_manager_api.expenseManager.entity.UserModel;


public interface UserService {
	
	User createUser(UserModel user);
	
	User readUser();
	
	User updateUser(UserModel user);
	
	void deleteUser();
	
	User getLoggedInUser();
}
