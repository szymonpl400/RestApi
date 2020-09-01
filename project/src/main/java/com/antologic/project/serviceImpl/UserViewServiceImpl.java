package com.antologic.project.serviceImpl;

import com.antologic.project.repository.UserViewRepository;
import com.antologic.project.services.UserViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserViewServiceImpl implements UserViewService {

    private final UserViewRepository userViewRepository;

    @Autowired
    public UserViewServiceImpl(UserViewRepository userViewRepository) {
        this.userViewRepository = userViewRepository;
    }

}
