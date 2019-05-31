package org.emergn.emergntestproject.controller;

import org.emergn.emergntestproject.domain.User;
import org.emergn.emergntestproject.dto.LoginFormDTO;
import org.emergn.emergntestproject.dto.RegistrationFormDTO;
import org.emergn.emergntestproject.dto.UsersTableDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    ResponseEntity<List<UsersTableDTO>> usersTable();

    ResponseEntity register(RegistrationFormDTO registrationForm);

    ResponseEntity editUser(User editedUser);

    ResponseEntity authenticateUser(LoginFormDTO loginForm);
}
