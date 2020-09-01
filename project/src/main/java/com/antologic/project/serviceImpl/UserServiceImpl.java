package com.antologic.project.serviceImpl;

import com.antologic.project.dtos.UserDto;
import com.antologic.project.entity.User;
import com.antologic.project.exceptions.NotFoundException;
import com.antologic.project.exceptions.ObjectExistException;
import com.antologic.project.forms.CreateUserForm;
import com.antologic.project.forms.FilterUserForm;
import com.antologic.project.mappers.UserMapper;
import com.antologic.project.repository.UserRepository;
import com.antologic.project.services.UserService;
import com.antologic.project.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException());
    }

    public UserDto saveUser(CreateUserForm createUserForm) {
        try {
            return userMapper.userToUserDto(userRepository.save(userMapper.createUserFormToUser(createUserForm)));
        } catch (Exception e) {
            throw new ObjectExistException();
        }
    }

    public void updateUser(CreateUserForm createUserForm, long id) {
        userRepository.save(userMapper.createUserFormToUser(createUserForm));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<FilterUserForm> findAll(FilterUserForm search, Pageable pageable) {
        Specification<FilterUserForm> spec = new UserSpecification(search);
        return userRepository.findAll(spec, pageable);
    }

}