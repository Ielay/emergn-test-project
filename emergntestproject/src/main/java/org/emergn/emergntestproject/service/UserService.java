package org.emergn.emergntestproject.service;

import org.emergn.emergntestproject.domain.User;
import org.emergn.emergntestproject.dto.RegistrationFormDTO;
import org.emergn.emergntestproject.dto.UsersTableDTO;

import java.util.List;

public interface UserService {

    boolean saveUser(RegistrationFormDTO registrationForm);

    List<UsersTableDTO> getUsersForUsersTable();

    boolean updateUser(User updatedUser);
}
