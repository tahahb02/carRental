package com.carrental.carrentalproject.service;


import com.carrental.carrentalproject.entities.User;
import com.carrental.carrentalproject.manager.UserManager;
import com.carrental.carrentalproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserManager {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
