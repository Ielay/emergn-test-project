package org.emergn.emergntestproject.repository;

import org.emergn.emergntestproject.domain.Role;
import org.emergn.emergntestproject.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private List<User> storage;

    public InMemoryUserRepository() {
        createData();
    }

    private void createData() {
        List<User> storageData = new ArrayList<>();

        User user1 = new User();
        user1.setLogin("petrov.valentin");
        user1.setName("Valentin");
        user1.setEmail("petrov.valentin@yandex.ru");
        user1.setPassword("qwerty123");
        user1.setRoles(Collections.singletonList(Role.USER));

        User user2 = new User();
        user2.setLogin("ivan1997");
        user2.setName("Ivan");
        user2.setEmail("ivan_1997@mail.ru");
        user2.setPassword("qwezxc");
        user2.setRoles(Collections.singletonList(Role.USER));

        User user3 = new User();
        user3.setLogin("nagibator322");
        user3.setName("Pavel");
        user3.setEmail("pavel322@gmail.com");
        user3.setPassword("PavelTopCool");
        user3.setRoles(Collections.singletonList(Role.USER));

        storageData.add(user1);
        storageData.add(user2);
        storageData.add(user3);

        this.storage = storageData;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public boolean addUser(User userToAdd) {
        return storage.add(userToAdd);
    }

    @Override
    public User findByLogin(String login) {
        return storage.stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
    }

    @Override
    public boolean updateUser(User updatedUser) {
        User oldUser = storage.stream().filter(user -> user.getLogin().equals(updatedUser.getLogin())).findFirst().orElse(null);

        oldUser = updatedUser;

        return true;
    }
}
