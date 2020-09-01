package com.antologic.project.controllers;

import com.antologic.project.dtos.UserDto;
import com.antologic.project.entity.User;
import com.antologic.project.forms.CreateUserForm;
import com.antologic.project.forms.FilterUserForm;
import com.antologic.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@Valid @RequestBody CreateUserForm createUserForm) {
        return userService.saveUser(createUserForm);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public void editUser(@Valid @PathVariable Long id, @RequestBody CreateUserForm createUserForm) {
        userService.updateUser(createUserForm, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search")
    public Page<FilterUserForm> search(@RequestBody FilterUserForm search, Pageable pageable) {
        return userService.findAll(search, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{login}")
    public User getUser(@PathVariable String login) {
        return userService.findByLogin(login);
    }
}
