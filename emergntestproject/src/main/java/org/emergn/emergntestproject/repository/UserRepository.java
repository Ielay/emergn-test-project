package org.emergn.emergntestproject.repository;

import org.emergn.emergntestproject.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    boolean addUser(User userToAdd);

    User findByLogin(String login);

    boolean updateUser(User updatedUser);
}
