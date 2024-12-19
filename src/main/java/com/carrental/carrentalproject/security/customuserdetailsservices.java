package com.carrental.carrentalproject.security;


import com.carrental.carrentalproject.entities.User;
import com.carrental.carrentalproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customuserdetailsservices implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user =repo.findByEmail(email);
        if (user==null){
        throw new UsernameNotFoundException("user not found");
        }
          return new customuserdetails(user);
    }
}
