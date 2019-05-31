package org.emergn.emergntestproject.mapper;

import org.emergn.emergntestproject.domain.User;
import org.emergn.emergntestproject.dto.RegistrationFormDTO;
import org.emergn.emergntestproject.dto.UsersTableDTO;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public User registrationFormDTOToUser(RegistrationFormDTO registrationForm) {
        User user = new User();

        user.setLogin(registrationForm.login);
        user.setName(registrationForm.name);
        user.setEmail(registrationForm.email);
        user.setPassword(registrationForm.password);

        return user;
    }

    public List<UsersTableDTO> usersToUsersTableDTOs(List<User> users) {
        List<UsersTableDTO> tableDatas = new ArrayList<>(users.size());

        for (User user : users) {
            UsersTableDTO tableData = new UsersTableDTO();

            tableData.login = user.getLogin();
            tableData.name = user.getName();
            tableData.email = user.getEmail();

            tableDatas.add(tableData);
        }

        return tableDatas;
    }
}
