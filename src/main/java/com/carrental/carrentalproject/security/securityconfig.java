package com.carrental.carrentalproject.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class securityconfig extends WebSecurityConfigurerAdapter {




    @Bean
    public UserDetailsService userDetailsService(){
        return new customuserdetailsservices();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
     authenticationProvider.setUserDetailsService(userDetailsService());
     authenticationProvider.setPasswordEncoder(passwordEncoder());
     return authenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
         auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/signup","/signin","/process-register","/css/**", "/js/**", "/images/**", "/webjars/**","static/**").permitAll()
                .anyRequest().authenticated()
                .and()
//                    .authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
                    .formLogin()
                    .loginPage("/signin")
                .loginProcessingUrl("/login")
                .permitAll().and()
          .logout()
                .logoutUrl("/logout") // Specify the logout URL
                .logoutSuccessUrl("/signin") // Redirect to this URL after successful logout
                .permitAll();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
