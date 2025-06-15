package com.cleartask.api.service;

import com.cleartask.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cleartask.api.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUserName(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    public User createUser(User user){
        userRepository.save(user);
        return user;
    }
}
