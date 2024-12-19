package com.carrental.carrentalproject.controller;


import com.carrental.carrentalproject.entities.User;
import com.carrental.carrentalproject.service.CarService;
import com.carrental.carrentalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @GetMapping("/signin")
    private String Login(){
        return "login";
    }

    @PostMapping("/signin")
    public String processlogin(@RequestParam String email , @RequestParam String password){
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, password);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            // You can return user details or a JWT token here if you are using JWT for authentication
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            String role = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("No role found");
            if(role.equals("ADMIN"))
                return "redirect:/admin";
            else
                return "redirect:/";
        } catch (AuthenticationException e) {
            return "Authentication failed: " + e.getMessage();
        }
    }

    @GetMapping("/signup")
    private String Signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/admin")
    private String Admin(){
        return "redirect:/admin/car";
    }

    @GetMapping("/user")
    private String User(){
        return "redirect:/user/bookcar";
    }

    @GetMapping("/user/bookcar")
    private String BookCar(Model model){
        model.addAttribute("cars",carService.getAvailableCars());
        return "bookmanager";
    }
    @PostMapping("/user/searchUser")
    private String searchUser(@RequestParam String searchkey,Model model){
        model.addAttribute("cars",carService.getAvailableByName(searchkey));
        return "bookmanager";
    }


    @PostMapping("/process-register")
    public String processRegistration(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleName(User.RoleName.USER);
        userService.saveUser(user);
        return "redirect:/signin";
    }
    @GetMapping("/")
    public String Home(){
        return "home";
    }


}
