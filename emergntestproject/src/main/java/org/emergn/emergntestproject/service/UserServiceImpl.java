package org.emergn.emergntestproject.service;

import org.emergn.emergntestproject.domain.Role;
import org.emergn.emergntestproject.domain.User;
import org.emergn.emergntestproject.dto.RegistrationFormDTO;
import org.emergn.emergntestproject.dto.UsersTableDTO;
import org.emergn.emergntestproject.mapper.UserMapper;
import org.emergn.emergntestproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper = new UserMapper();

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean saveUser(RegistrationFormDTO registrationForm) {
        if (!registrationForm.password.equals(registrationForm.passwordConfirmation)) {
            return false;
        }

        User userWithSpecificLogin = userRepository.findByLogin(registrationForm.login);

        if (userWithSpecificLogin != null) {
            return false;
        }

        User userToSave = userMapper.registrationFormDTOToUser(registrationForm);
        userToSave.setPassword(encoder.encode(userToSave.getPassword()));
        userToSave.setRoles(Collections.singletonList(Role.USER));

        return userRepository.addUser(userToSave);
    }

    @Override
    public List<UsersTableDTO> getUsersForUsersTable() {
        List<User> allUsers = userRepository.getAll();

        return userMapper.usersToUsersTableDTOs(allUsers);
    }

    @Override
    public boolean updateUser(User updatedUser) {
        User userWithSpecificLogin = userRepository.findByLogin(updatedUser.getLogin());

        if (userWithSpecificLogin != null) {
            return false;
        }

        return userRepository.updateUser(updatedUser);
    }
}
