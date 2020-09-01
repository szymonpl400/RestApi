package com.antologic.project.services;

import com.antologic.project.dtos.UserDto;
import com.antologic.project.entity.User;
import com.antologic.project.forms.CreateUserForm;
import com.antologic.project.forms.FilterUserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findByLogin(String login);

    UserDto saveUser(CreateUserForm createUserForm);

    void updateUser(CreateUserForm createUserForm, long id);

    void deleteUser(Long userId);

    List<User> findAll();

    Page<FilterUserForm> findAll(FilterUserForm search, Pageable pageable);
}
