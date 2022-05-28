package com.dattt.miniassignment.service;

import com.dattt.miniassignment.dto.Users;
import com.dattt.miniassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users login(String username, String password){

        Users users = userRepository.findByUsernameAndPassword(username, password);
        return users;
    }
}
