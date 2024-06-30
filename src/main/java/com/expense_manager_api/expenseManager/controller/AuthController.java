package com.expense_manager_api.expenseManager.controller;

import com.expense_manager_api.expenseManager.entity.AuthModel;
import com.expense_manager_api.expenseManager.entity.JwtResponse;
import com.expense_manager_api.expenseManager.entity.User;
import com.expense_manager_api.expenseManager.entity.UserModel;
import com.expense_manager_api.expenseManager.security.CustomUserDetailsService;
import com.expense_manager_api.expenseManager.service.UserService;
import com.expense_manager_api.expenseManager.util.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AuthController {

	//logger
	private static final Logger logger = Logger.getLogger(AuthController.class.getName());

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {

		authenticate(authModel.getEmail(), authModel.getPassword());

		// Generate the JWT token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		logger.info("User " + userDetails.getUsername() + " logged in successfully."); // Log successful login

		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}

	private void authenticate(String email, String password) throws Exception {
//checks
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			logger.warning("Login attempt with disabled user: " + email);
			throw new Exception("User disabled");
		} catch (BadCredentialsException e) {
			logger.warning("Login attempt with bad credentials for user: " + email);
			throw new Exception("Bad Credentials");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
}
