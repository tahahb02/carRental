package com.carrental.carrentalproject.manager;

import com.carrental.carrentalproject.entities.User;

public interface UserManager {

    User saveUser(User user);

    User getUserByEmail(String email);
}
